package com.proyecto.abanca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingDto {

    private Long balance;
    private String primaryOwnerId;
    private String secretKey;

}