package com.proyecto.abanca;

import com.proyecto.abanca.account.Account;
import org.javamoney.moneta.Money;

import java.time.LocalDateTime;

public class Transfer {
    private Account accountOrigen;
    private Account accountDestino;
    private Money amount;
    private LocalDateTime date;
}
