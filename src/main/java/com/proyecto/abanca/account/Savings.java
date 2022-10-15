package com.proyecto.abanca.account;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Savings extends Account {
    private Money minimumBalance;
    private LocalDate creationDate;
    private Status status;
    private BigDecimal interestRate;
}
