package com.proyecto.abanca.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseError {
    private String errorDescription;
    private String status;
}