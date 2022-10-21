package com.proyecto.abanca.model.user;

import javax.persistence.Entity;

@Entity
public class ThirdParty extends User {
    private String hashedKey;
}
