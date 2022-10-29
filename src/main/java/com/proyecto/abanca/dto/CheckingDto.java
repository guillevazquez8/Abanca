package com.proyecto.abanca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingDto {

    private static Long balance;
    private static String primaryOwnerId;
    private static String secretKey;

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