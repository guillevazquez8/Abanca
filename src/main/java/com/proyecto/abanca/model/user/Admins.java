package com.proyecto.abanca.model.user;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Admins extends User {

    public Admins(String name) {
        super(name);
    }

}
