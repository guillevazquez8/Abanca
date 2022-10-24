package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.AccountHolders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Checking extends Account {
    @Embedded
    private Money minimumBalance = new Money(BigDecimal.valueOf(250));
    @Embedded
    private Money monthlyMaintenanceFee = new Money(BigDecimal.valueOf(12));

    public void setPrimaryOwner(AccountHolders primaryOwner) {
        if (primaryOwner.getDateOfBirth().isBefore(LocalDate.of((LocalDate.now().getYear()-24),
                LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()))) {
            //throw EXCEPTION
        }
        //Account.setPrimaryOwner() = primaryOwner;
    }
}
