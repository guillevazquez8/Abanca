package com.proyecto.abanca.model.account;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Checking extends Account {
    @Embedded
    private Money minimumBalance;
    @Embedded
    private Money monthlyMaintenanceFee;
}
