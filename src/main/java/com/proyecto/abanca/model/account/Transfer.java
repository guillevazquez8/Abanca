package com.proyecto.abanca.model.account;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
