package com.proyecto.abanca.model.account;

import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Currency;

import static org.aspectj.runtime.internal.Conversions.doubleValue;

@Entity
@Getter
public class CreditCard extends Account {
    @Embedded
    private Money creditLimit = new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"));
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);

    public void setCreditLimit(BigDecimal creditLimit) {
        if (doubleValue(creditLimit) > 100000) {
            //throw EXCEPTION
        }
        this.creditLimit = new Money(creditLimit, Currency.getInstance("USD"));
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (doubleValue(interestRate) < 0.1) {
            //throw EXCEPTION
        }
        this.interestRate = interestRate;
    }
}
