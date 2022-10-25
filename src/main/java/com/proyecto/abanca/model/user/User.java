package com.proyecto.abanca.model.user;

import com.proyecto.abanca.model.account.Transfer;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "orderedBy")
    private Set<Transfer> transfersOrdered = new HashSet<>();
}
