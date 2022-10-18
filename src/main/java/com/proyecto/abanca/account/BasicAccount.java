package com.proyecto.abanca.account;

import com.proyecto.abanca.user.AccountHolders;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public abstract class BasicAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Money balance;
    private AccountHolders primaryOwner;
    private AccountHolders secondaryOwner; //optional
    @Embedded
    private Money penaltyFee;
    private LocalDate creationDate;
}
