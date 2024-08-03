package com.example.projectrest.controller;

import com.example.projectrest.model.Account;
import com.example.projectrest.model.Transaction;
import com.example.projectrest.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Account> transfer(@RequestBody Transaction transaction) {
        Account updatedAccount = transferService.transfer(
                transaction.getSourceAccountNumber(),
                transaction.getDestinationAccountNumber(),
                transaction.getAmount()
        );
        return ResponseEntity.ok(updatedAccount);
    }

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<Account> getBalance(@PathVariable String accountNumber) {
        Account account = transferService.getAccountBalance(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account newAccount = transferService.createAccount(account.getAccountNumber(), account.getBalance());
        return ResponseEntity.ok(newAccount);
    }
}