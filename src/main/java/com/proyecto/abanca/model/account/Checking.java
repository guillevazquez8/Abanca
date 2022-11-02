package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
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
    private Money minimumBalance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="monthlyMaintenanceFee", insertable = false, updatable = false)),
            @AttributeOverride(name="currency", column = @Column(name="currencyMaintenanceFee", insertable = false, updatable = false)),
    })
    private Money monthlyMaintenanceFee;

    public Checking(AccountHolders primaryOwner, LocalDate creationDate, String secretKey, Status status) {
        super(primaryOwner, creationDate, secretKey, status);
    }

    public Checking(AccountHolders primaryOwner, LocalDate creationDate, String secretKey) {
        super(primaryOwner, creationDate, secretKey);
        this.minimumBalance = new Money(BigDecimal.valueOf(250));
        this.monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));
    }

}
