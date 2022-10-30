package com.proyecto.abanca.model.user;

import com.proyecto.abanca.security.models.Role;
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

    public ThirdParty(String name, String username, String password, String hashedKey) {
        super(name, username, password);
        setHashedKey(hashedKey);
    }

}
