package com.proyecto.abanca.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
public class CheckingDto {

    private static Long balance;
    private static String primaryOwnerId;
    private static String secretKey;

    public CheckingDto(Long balance, String primaryOwnerId, String secretKey) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secretKey = secretKey;
    }

    public static Long getBalance() {
        return balance;
    }

    public static String getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public static String getSecretKey() {
        return secretKey;
    }
}