package com.proyecto.abanca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private Long amount;
    private String accountOrigenId;
    private String accountDestinoId;
    private String accountDestinoName;

    public TransferDto(Long amount, String accountOrigenId, String accountDestinoId) {
        this.amount = amount;
        this.accountOrigenId = accountOrigenId;
        this.accountDestinoId = accountDestinoId;
    }

}
