package com.proyecto.abanca.model.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Currency;
import javax.validation.constraints.*;

import static org.aspectj.runtime.internal.Conversions.doubleValue;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Savings extends Account {
    @Embedded
    @Min(value = 100)
    private Money minimumBalance = new Money(BigDecimal.valueOf(1000));
    @DecimalMax(value = "0.5")
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);
}
