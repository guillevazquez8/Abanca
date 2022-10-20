package com.proyecto.abanca.account;

import com.proyecto.abanca.account.BasicAccount;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private BasicAccount accountOrigen;
    @ManyToOne
    private BasicAccount accountDestino;
    @Embedded
    private Money amount;
    private LocalDateTime date;
}
