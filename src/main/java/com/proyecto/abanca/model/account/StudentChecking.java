package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentChecking extends Account {

    public StudentChecking(AccountHolders primaryOwner, LocalDate creationDate, String secretKey, Status status) {
        super(primaryOwner, creationDate, secretKey, status);
    }
}
