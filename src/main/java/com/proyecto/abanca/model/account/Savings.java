package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Savings extends BasicAccount {

    @Embedded
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));

    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);

    private LocalDate interestRateDateApplied;

    public Savings(AccountHolders primaryOwner, LocalDate creationDate, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, creationDate);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public void setMinimumBalance(Money minimumBalance) {
        if (minimumBalance.getAmount().compareTo(BigDecimal.valueOf(100)) < 0) {
            throw new RuntimeException("Minimum balance should be higher than 100");
        }
        this.minimumBalance = minimumBalance;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.5)) > 0) {
            throw new RuntimeException("Interest rate should be less than 0.5");
        }
        this.interestRate = interestRate;
    }

    public void setInterestRateDateApplied(LocalDate interestRateDateApplied) {
        this.interestRateDateApplied = interestRateDateApplied;
    }
}
