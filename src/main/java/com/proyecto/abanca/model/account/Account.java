package com.proyecto.abanca.model.account;

import javax.persistence.Entity;

@Entity
public abstract class Account extends BasicAccount {
    private String secretKey;
    private Status status;
}
