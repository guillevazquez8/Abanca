package com.proyecto.abanca.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;

@Getter
@Setter
@NoArgsConstructor
public class CreditCardDto {

    private Long balance;
    private String primaryOwnerId;
    private String secretKey;
    @Max(value = 100000)
    private Long creditLimit;
    @DecimalMin(value = "0.1")
    private Double interestRate;

    public CreditCardDto(String primaryOwnerId, String secretKey, Long creditLimit, Double interestRate) {
        this.balance = 0L;
        this.primaryOwnerId = primaryOwnerId;
        this.secretKey = secretKey;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

}
