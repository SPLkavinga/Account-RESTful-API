package com.example.projectrest.repository;
import com.example.projectrest.model.Account;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();

    public AccountRepository() {
        // Initialize some accounts for testing
        accounts.put("123456", new Account("123456", new BigDecimal("1000.00")));
        accounts.put("789012", new Account("789012", new BigDecimal("500.00")));
    }

    public Account findByAccountNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void save(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public boolean accountExists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }
}