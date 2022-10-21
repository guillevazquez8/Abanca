package com.proyecto.abanca.model.account;

import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Currency;

import static org.aspectj.runtime.internal.Conversions.doubleValue;

@Entity
@Getter
public class Savings extends Account {
    @Embedded
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000),Currency.getInstance("USD"));
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);

    public void setInterestRate(BigDecimal interestRate) {
        if (doubleValue(interestRate) > 0.5) {
            //throw new EXCEPTION
        }
        this.interestRate = interestRate;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if (doubleValue(minimumBalance) < 100) {
            //throw new EXCEPTION
        }
        this.minimumBalance = new Money(minimumBalance, Currency.getInstance("USD"));
    }
}
