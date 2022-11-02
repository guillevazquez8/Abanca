package com.proyecto.abanca.model.user;

import lombok.*;

import javax.persistence.Entity;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Admins extends User {

    public Admins(String name, String username, String password, Set<Role> roles) {
        super(name, username, password, roles);
    }

}
