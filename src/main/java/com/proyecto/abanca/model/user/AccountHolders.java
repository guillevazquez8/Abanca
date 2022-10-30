package com.proyecto.abanca.model.user;
import com.proyecto.abanca.model.account.BasicAccount;
import com.proyecto.abanca.security.models.Role;
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

    public AccountHolders(String name, String username, String password, Set<Role> roles, LocalDate dateOfBirth, Address primaryAddress) {
        super(name, username, password, roles);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
    }

    public AccountHolders(String name, String username, String password, LocalDate dateOfBirth, Address primaryAddress) {
        super(name, username, password);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
    }

    public AccountHolders(String name, String username, String password, Set<Role> roles, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(name, username, password, roles);
        setDateOfBirth(dateOfBirth);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

}
