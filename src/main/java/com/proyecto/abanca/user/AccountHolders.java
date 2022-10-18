package com.proyecto.abanca.user;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Optional;

@Entity
public class AccountHolders extends User {
    private LocalDate dateOfBirth;
    private Address primaryAddress;
    private Optional<Address> mailingAddress;
}
