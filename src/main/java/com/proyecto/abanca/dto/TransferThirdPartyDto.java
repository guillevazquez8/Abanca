package com.proyecto.abanca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferThirdPartyDto {

    private Long amount;
    private String hashedKey;
    private String accountOrigenId;
    private String accountOrigenSecretKey;
    private String accountDestinoId;
    private String accountDestinoSecretKey;

}
