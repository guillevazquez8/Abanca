package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Account extends BasicAccount {

    private String secretKey;

    private Status status;

    public Account(AccountHolders primaryOwner, LocalDate creationDate, String secretKey, Status status) {
        super(primaryOwner, creationDate);
        this.secretKey = secretKey;
        this.status = status;
    }

    public Account(AccountHolders primaryOwner, LocalDate creationDate, String secretKey) {
        super(primaryOwner, creationDate);
        this.secretKey = secretKey;
    }
}
