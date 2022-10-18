package com.proyecto.abanca.user;

import javax.persistence.Entity;

@Entity
public class ThirdParty extends User {
    private String hashedKey;
}
