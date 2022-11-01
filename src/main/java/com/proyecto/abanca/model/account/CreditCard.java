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
@Setter
@ToString
public class CreditCard extends Account {

    @Embedded
    @Max(value = 100000)
    private Money creditLimit = new Money(BigDecimal.valueOf(100));

    @DecimalMin(value = "0.1")
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);

    private LocalDate interestRateDateApplied;

    public CreditCard(AccountHolders primaryOwner, LocalDate creationDate, String secretKey, Status status, Money creditLimit, BigDecimal interestRate) {
        super(primaryOwner, creationDate, secretKey, status);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

}
