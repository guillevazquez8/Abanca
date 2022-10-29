package com.proyecto.abanca.model.user;

import lombok.*;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ThirdParty extends User {

    private String hashedKey;

    public ThirdParty(String name, String hashedKey) {
        super(name);
        setHashedKey(hashedKey);
    }

}
