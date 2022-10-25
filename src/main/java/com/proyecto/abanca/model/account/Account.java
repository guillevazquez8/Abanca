package com.proyecto.abanca.model.account;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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
}
