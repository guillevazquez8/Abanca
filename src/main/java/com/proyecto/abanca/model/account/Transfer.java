package com.proyecto.abanca.model.account;

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
