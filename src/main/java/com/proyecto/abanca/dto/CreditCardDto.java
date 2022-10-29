package com.proyecto.abanca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CreditCardDto {

    private Long balance;
    private String primaryOwnerId;
    private String secretKey;
    private Long creditLimit;
    private Double interestRate;

    public CreditCardDto(Long balance, String primaryOwnerId, String secretKey, Long creditLimit, Double interestRate) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secretKey = secretKey;
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setPrimaryOwnerId(String primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setCreditLimit(Long creditLimit) {
        if (creditLimit > 100000) {
            this.creditLimit = 100000L;
        }
        this.creditLimit = creditLimit;
    }

    public void setInterestRate(Double interestRate) {
        if (interestRate < 0.1) {
            this.interestRate = 0.1;
        }
        this.interestRate = interestRate;
    }
}
