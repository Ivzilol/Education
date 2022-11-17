package com.example.lab_05_spring.services;

import java.math.BigDecimal;

public interface AccountServices {

    void withdrawMoney(BigDecimal amount, Long id);
    void transferMoney(BigDecimal amount, Long id);





}
