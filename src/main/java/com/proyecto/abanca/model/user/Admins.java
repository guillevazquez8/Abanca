package com.proyecto.abanca.model.user;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Admins extends User {

    public Admins(String name, String username, String password) {
        super(name, username, password);
    }

}
