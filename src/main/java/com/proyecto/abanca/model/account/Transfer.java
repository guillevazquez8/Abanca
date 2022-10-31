package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @NotNull
    private BasicAccount accountOrigen;

    @ManyToOne
    @NotNull
    private BasicAccount accountDestino;

    @Embedded
    @NotNull
    private Money amount;

    private LocalDateTime date;

    @ManyToOne
    private User orderedBy;

    public Transfer(BasicAccount accountOrigen, BasicAccount accountDestino, Money amount, LocalDateTime date, User orderedBy) {
        this.accountOrigen = accountOrigen;
        this.accountDestino = accountDestino;
        this.amount = amount;
        this.date = date;
        this.orderedBy = orderedBy;
    }

    public Transfer(BasicAccount accountOrigen, BasicAccount accountDestino, Money amount, LocalDateTime date) {
        this.accountOrigen = accountOrigen;
        this.accountDestino = accountDestino;
        this.amount = amount;
        this.date = date;
    }

}
