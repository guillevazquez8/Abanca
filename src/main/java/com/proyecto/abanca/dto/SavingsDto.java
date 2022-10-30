package com.proyecto.abanca.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Setter
public class SavingsDto {

    private static Long balance;
    private static String primaryOwnerId;
    @Min(value = 100)
    private Long minimumBalance;
    @DecimalMax(value = "0.5")
    private Double interestRate;

    public SavingsDto(String primaryOwnerId, Long minimumBalance, Double interestRate) {
        this.primaryOwnerId = primaryOwnerId;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    public Long getBalance() {return balance;}

    public String getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public Long getMinimumBalance() {
        return minimumBalance;
    }

    public Double getInterestRate() {return interestRate;}

}
