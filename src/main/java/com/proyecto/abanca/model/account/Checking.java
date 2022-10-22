package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Checking extends Account {
    @Embedded
    private Money minimumBalance;
    @Embedded
    private Money monthlyMaintenanceFee;

    public void setPrimaryOwner(AccountHolders primaryOwner) {
        if (primaryOwner.getDateOfBirth().isBefore(LocalDate.of((LocalDate.now().getYear()-24),
                LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))) {
            //throw EXCEPTION
        }
        //Account.setPrimaryOwner() = primaryOwner;
    }
}
