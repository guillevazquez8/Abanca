package com.proyecto.abanca.account;

import org.javamoney.moneta.Money;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Savings extends Account {
    @Embedded
    private Money minimumBalance;
    private BigDecimal interestRate;
}
