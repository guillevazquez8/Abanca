package com.proyecto.abanca.user;

import java.time.LocalDate;
import java.util.Optional;

public class AccountHolders extends User {
    private LocalDate dateOfBirth;
    private Address primaryAddress;
    private Optional<Address> mailingAddress;
}
