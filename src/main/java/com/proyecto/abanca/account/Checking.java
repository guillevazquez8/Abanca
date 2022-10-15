package com.proyecto.abanca.account;

import org.javamoney.moneta.Money;

import java.time.LocalDate;

public class Checking extends Account {
    private Money minimumBalance;
    private Money monthlyMaintenanceFee;
    private LocalDate creationDate;
    private Status status;
}
