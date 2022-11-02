package com.proyecto.abanca.model.user;

import lombok.*;

import javax.persistence.Entity;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ThirdParty extends User {

    private String hashedKey;

    public ThirdParty(String name, String username, String password, Set<Role> roles, String hashedKey) {
        super(name, username, password, roles);
        setHashedKey(hashedKey);
    }

}
