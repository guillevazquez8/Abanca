package com.proyecto.abanca.model.account;

import com.proyecto.abanca.model.user.User;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    private BasicAccount accountOrigen;

    @ManyToOne
    private BasicAccount accountDestino;

    @Embedded
    private Money amount;

    private LocalDateTime date;

    @ManyToOne
    private User orderedBy;
}
