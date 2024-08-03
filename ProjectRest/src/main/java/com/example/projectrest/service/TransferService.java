package com.example.projectrest.service;

import com.example.projectrest.model.Account;

import java.math.BigDecimal;

public interface TransferService {
    Account transfer(String sourceAccountNumber, String destinationAccountNumber, BigDecimal amount);
    Account getAccountBalance(String accountNumber);
    Account createAccount(String accountNumber, BigDecimal initialBalance);
}