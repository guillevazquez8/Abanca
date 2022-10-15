package com.proyecto.abanca.user;

import java.util.Optional;

public class Address {
    private String street;
    private Optional<Integer> number;
    private Optional<Integer> floor;
    private Optional<String> door;
    private Long postcode;
    private String city;
    private String country;
}
