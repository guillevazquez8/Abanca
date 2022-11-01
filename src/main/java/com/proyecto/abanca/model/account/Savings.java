package com.proyecto.abanca.model.account;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Savings extends BasicAccount {

    @Embedded
    @Min(value = 100)
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));

    @DecimalMax(value = "0.5")
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);

    private LocalDate interestRateDateApplied;

}
