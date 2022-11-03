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
            @AttributeOverride(name="amount", column = @Column(name="amountMinimumBalance")),
            @AttributeOverride(name="currency", column = @Column(name="currencyMinimumBalance")),
    })
    private Money minimumBalance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="monthlyMaintenanceFee")),
            @AttributeOverride(name="currency", column = @Column(name="currencyMaintenanceFee")),
    })
    private Money monthlyMaintenanceFee;

    public Checking(AccountHolders primaryOwner, LocalDate creationDate, String secretKey, Status status, Money minimumBalance, Money monthlyMaintenanceFee) {
        super(primaryOwner, creationDate, secretKey, status);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Checking(AccountHolders primaryOwner, LocalDate creationDate, String secretKey, Status status) {
        this(primaryOwner, creationDate, secretKey, status, new Money(BigDecimal.valueOf(250)), new Money(BigDecimal.valueOf(12)));
    }

}
