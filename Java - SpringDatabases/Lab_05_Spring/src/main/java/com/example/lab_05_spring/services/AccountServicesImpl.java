package com.example.lab_05_spring.services;

import com.example.lab_05_spring.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class AccountServicesImpl implements AccountServices {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServicesImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {

    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {

    }
}
