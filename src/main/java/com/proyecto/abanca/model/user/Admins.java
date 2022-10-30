package com.proyecto.abanca.model.user;

import com.proyecto.abanca.security.models.Role;
import lombok.*;

import javax.persistence.Entity;
import java.util.HashSet;
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

    public Admins(String name, String username, String password) {
        super(name, username, password);
    }



}
