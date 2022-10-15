package com.proyecto.abanca.account;

import org.javamoney.moneta.Money;

import java.util.Optional;

public class Account {
    private Long id;
    private Money balance;
    private String secretKey;
    private String primaryOwner;
    private Optional<String> secondaryOwner;
    private Money penaltyFee;
}
