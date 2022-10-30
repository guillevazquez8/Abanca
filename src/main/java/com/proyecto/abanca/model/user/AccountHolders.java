package com.proyecto.abanca.model.user;
import com.proyecto.abanca.model.account.BasicAccount;
import lombok.*;

import javax.persistence.*;
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

    public AccountHolders(LocalDate dateOfBirth, Address primaryAddress, Set<BasicAccount> basicAccountsPrimary, Set<BasicAccount> basicAccountsSecondary) {
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
    }

    public AccountHolders(LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress, Set<BasicAccount> basicAccountsPrimary) {
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

    public AccountHolders(String name, String username, String password, LocalDate dateOfBirth, Address primaryAddress) {
        super(name, username, password);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
    }

}
