package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class BasicAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="amountBalance")),
            @AttributeOverride(name="currency", column = @Column(name="currencyBalance"))
    })
    private Money balance;

    @ManyToOne
    @NotNull
    private AccountHolders primaryOwner;

    @ManyToOne
    private AccountHolders secondaryOwner; //optional

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="amountPenaltyFee")),
            @AttributeOverride(name="currency", column = @Column(name="currencyPenaltyFee")),
    })
    private Money penaltyFee;

    private LocalDate creationDate;

    public BasicAccount(Money balance, AccountHolders primaryOwner, Money penaltyFee, LocalDate creationDate) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
    }

    //constructor overload to set balance value to 0 when creating an account
    public BasicAccount(AccountHolders primaryOwner, Money penaltyFee, LocalDate creationDate) {
        this(new Money(BigDecimal.valueOf(0)), primaryOwner, penaltyFee, creationDate);
    }

    //constructor overload to set penalty fee value to 40 when creating an account
    public BasicAccount(Money balance, AccountHolders primaryOwner, LocalDate creationDate) {
        this(balance, primaryOwner, new Money(BigDecimal.valueOf(40)), creationDate);
    }

    public BasicAccount(AccountHolders primaryOwner, LocalDate creationDate) {
        this(new Money(BigDecimal.valueOf(0)), primaryOwner, new Money(BigDecimal.valueOf(40)), creationDate);
    }

}
