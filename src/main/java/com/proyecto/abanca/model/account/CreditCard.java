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
public class CreditCard extends Account {
    @Embedded
    @Max(value = 100000)
    private Money creditLimit = new Money(BigDecimal.valueOf(100), Currency.getInstance("USD"));
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);
}
