package com.proyecto.abanca.account;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class CreditCard extends Account {
    private Money creditLimit;
    private BigDecimal interestRate;
    private Money penaltyFee;

}
