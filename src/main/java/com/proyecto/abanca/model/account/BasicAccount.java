package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public abstract class BasicAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Money balance;
    @ManyToOne
    private AccountHolders primaryOwner;
    @ManyToOne
    private AccountHolders secondaryOwner; //optional
    @Embedded
    private Money penaltyFee;
    private LocalDate creationDate;
    @OneToMany(mappedBy = "accountOrigen")
    private Set<Transfer> transfersSent = new HashSet<>();
    @OneToMany(mappedBy = "accountDestino")
    private Set<Transfer> transfersReceived = new HashSet<>();
}
