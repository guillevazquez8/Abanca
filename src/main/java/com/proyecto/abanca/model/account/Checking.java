package com.proyecto.abanca.model.account;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Checking extends Account {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="amountMinimumBalance", insertable = false, updatable = false)),
            @AttributeOverride(name="currency", column = @Column(name="currencyMinimumBalance", insertable = false, updatable = false)),
    })
    private Money minimumBalance = new Money(BigDecimal.valueOf(250));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="monthlyMaintenanceFee", insertable = false, updatable = false)),
            @AttributeOverride(name="currency", column = @Column(name="currencyMinimumBalance", insertable = false, updatable = false)),
    })
    private Money monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));
}
