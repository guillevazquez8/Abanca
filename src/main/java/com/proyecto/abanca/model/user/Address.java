package com.proyecto.abanca.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private Integer number; //optional
    private Integer floor; //optional
    private String door; //optional
    private Long postcode;
    private String city;
    private String country;
}
