package com.proyecto.abanca.model.user;
import com.proyecto.abanca.model.account.BasicAccount;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountHolders extends User {
    private LocalDate dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "primary_address_id")
    private Address primaryAddress;
    @ManyToOne
    @JoinColumn(name = "mailing_address_id")
    private Address mailingAddress; //optional
    @OneToMany(mappedBy = "primaryOwner")
    private Set<BasicAccount> basicAccountsPrimary = new HashSet<>();
    @OneToMany(mappedBy = "secondaryOwner")
    private Set<BasicAccount> basicAccountsSecondary = new HashSet<>();

    public AccountHolders(LocalDate dateOfBirth, Address primaryAddress, Set<BasicAccount> basicAccountsPrimary, Set<BasicAccount> basicAccountsSecondary) {
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setBasicAccountsPrimary(basicAccountsPrimary);
        setBasicAccountsSecondary(basicAccountsSecondary);
    }

}
