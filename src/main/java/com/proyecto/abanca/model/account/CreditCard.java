package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreditCard extends Account {

    @Embedded
    private Money creditLimit = new Money(BigDecimal.valueOf(100));

    @DecimalMin(value = "0.1")
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);

    private LocalDate interestRateDateApplied;

    public CreditCard(AccountHolders primaryOwner, LocalDate creationDate, String secretKey, Status status,
                      Money creditLimit, BigDecimal interestRate) {
        super(primaryOwner, creationDate, secretKey, status);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public void setCreditLimit(Money creditLimit) {
        if (creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000)) > 0) {
            throw new RuntimeException("Credit limit should be less than 100_000");
        }
        this.creditLimit = creditLimit;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate.compareTo(BigDecimal.valueOf(0.1)) < 0) {
            throw new RuntimeException("Interest rate should be more than 0.1");
        }
        this.interestRate = interestRate;
    }

    public void setInterestRateDateApplied(LocalDate interestRateDateApplied) {
        this.interestRateDateApplied = interestRateDateApplied;
    }
}
