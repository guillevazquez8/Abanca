package com.proyecto.abanca.model.user;
import com.proyecto.abanca.model.account.BasicAccount;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AccountHolders extends User {
    private LocalDate dateOfBirth;
    private Address primaryAddress;
    private Address mailingAddress; //optional
    @OneToMany(mappedBy = "primaryOwner")
    private Set<BasicAccount> basicAccountsPrimary = new HashSet<>();
    @OneToMany(mappedBy = "secondaryOwner")
    private Set<BasicAccount> basicAccountsSecondary = new HashSet<>();

}
