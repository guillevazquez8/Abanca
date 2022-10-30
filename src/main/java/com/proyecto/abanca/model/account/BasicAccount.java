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
            @AttributeOverride(name="amount", column = @Column(name="amountBalance", insertable = false, updatable = false)),
            @AttributeOverride(name="currency", column = @Column(name="currencyBalance", insertable = false, updatable = false)),
    })
    private Money balance;

    @ManyToOne
    @NotNull
    private AccountHolders primaryOwner;

    @ManyToOne
    private AccountHolders secondaryOwner; //optional

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="amountPenaltyFee", insertable = false, updatable = false)),
            @AttributeOverride(name="currency", column = @Column(name="currencyPenaltyFee", insertable = false, updatable = false)),
    })
    private Money penaltyFee = new Money(BigDecimal.valueOf(40));

    private LocalDate creationDate;

    @OneToMany(mappedBy = "accountOrigen")
    private Set<Transfer> transfersSent = new HashSet<>();

    @OneToMany(mappedBy = "accountDestino")
    private Set<Transfer> transfersReceived = new HashSet<>();

    public BasicAccount(AccountHolders primaryOwner, LocalDate creationDate) {
        this.primaryOwner = primaryOwner;
        this.creationDate = creationDate;
    }
}
