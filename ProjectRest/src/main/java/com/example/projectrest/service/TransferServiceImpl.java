package com.example.projectrest.service;

import com.example.projectrest.model.Account;
import com.example.projectrest.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;

    public TransferServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Account transfer(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount) {
        Account sourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber);
        Account destinationAccount = accountRepository.findByAccountNumber(destinationAccountNumber);

        if (sourceAccount == null) {
            throw new RuntimeException("Source account not found");
        }

        if (destinationAccount == null) {
            throw new RuntimeException("Destination account not found");
        }

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance in the source account");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        return sourceAccount;  // Return the updated source account
    }

    @Override
    public Account getAccountBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        return account;
    }

    @Override
    public Account createAccount(String accountNumber, BigDecimal initialBalance) {
        if (accountRepository.accountExists(accountNumber)) {
            throw new RuntimeException("Account already exists");
        }
        Account newAccount = new Account(accountNumber, initialBalance);
        accountRepository.save(newAccount);
        return newAccount;
    }
}