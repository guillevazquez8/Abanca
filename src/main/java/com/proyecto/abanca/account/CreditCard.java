package com.proyecto.abanca.account;

import org.javamoney.moneta.Money;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CreditCard extends Account {
    @Embedded
    private Money creditLimit;
    private BigDecimal interestRate;
}
