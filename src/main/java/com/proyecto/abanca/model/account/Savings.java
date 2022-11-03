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
    private Money minimumBalance;

    private BigDecimal interestRate;

    private LocalDate interestRateDateApplied;

    public Savings(AccountHolders primaryOwner, LocalDate creationDate, Money minimumBalance, BigDecimal interestRate) {
        super(primaryOwner, creationDate);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Savings(AccountHolders primaryOwner, LocalDate creationDate, Money minimumBalance) {
        this(primaryOwner, creationDate, minimumBalance, BigDecimal.valueOf(0.0025));
    }

    public Savings(AccountHolders primaryOwner, LocalDate creationDate, BigDecimal interestRate) {
        this(primaryOwner, creationDate, new Money(BigDecimal.valueOf(1000)), interestRate);
    }

    public Savings(AccountHolders primaryOwner, LocalDate creationDate) {
        this(primaryOwner, creationDate, new Money(BigDecimal.valueOf(1000)), BigDecimal.valueOf(0.0025));
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
