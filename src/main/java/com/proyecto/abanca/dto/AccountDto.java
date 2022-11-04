package com.proyecto.abanca.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AccountDto {

    private Long balance;
    private String primaryOwnerId;
    private String secretKey;

    public AccountDto(String primaryOwnerId, String secretKey) {
        this.primaryOwnerId = primaryOwnerId;
        this.secretKey = secretKey;
        this.balance = 0L;
    }

    public Long getBalance() {
        return balance;
    }

    public String getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public String getSecretKey() {
        return secretKey;
    }
}