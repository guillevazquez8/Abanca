package com.proyecto.abanca.model.user;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String street;
    private Integer number;
    private Long postcode;
    private String city;
    private String country;

    public Address(String street, Integer number, Long postcode, String city, String country) {
        setStreet(street);
        setNumber(number);
        setPostcode(postcode);
        setCity(city);
        setCountry(country);
    }
}
