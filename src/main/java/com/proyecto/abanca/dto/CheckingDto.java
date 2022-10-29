package com.proyecto.abanca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingDto {
    private BigDecimal balance;
    private String primaryOwnerId;
    private String secretKey;
}
