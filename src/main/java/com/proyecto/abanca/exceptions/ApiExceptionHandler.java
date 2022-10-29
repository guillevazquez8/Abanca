package com.proyecto.abanca.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.RuntimeErrorException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_400 = "ERROR_400_BAD_REQUEST";
    private static final String ERROR_401 = "ERROR_401_UNAUTHORIZED";
    private static final String ERROR_403 = "ERROR_403_FORBIDDEN";
    private static final String ERROR_404 = "ERROR_404_NOT_FOUND";
    private static final String ERROR_500 = "ERROR_500_SERVER_ERROR";

    @ExceptionHandler(value = {BadRequestException.class, IllegalArgumentException.class})
    protected ResponseEntity<ResponseError> handleBadRequestException(RuntimeException ex, WebRequest request) {
        log.error(ERROR_400, ex);
        return getResponseEntity(BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    protected ResponseEntity<ResponseError> handleUnauthorizedException(RuntimeException ex, WebRequest request) {
        log.error(ERROR_401, ex);
        return getResponseEntity(UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    protected ResponseEntity<ResponseError> handleForbiddenException(RuntimeException ex, WebRequest request) {
        log.error(ERROR_403, ex);
        return getResponseEntity(FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ResponseError> handleNotFoundException(RuntimeException ex, WebRequest request) {
        log.error(ERROR_404, ex);
        return getResponseEntity(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(value = {ServerErrorException.class})
    protected ResponseEntity<ResponseError> handleServerErrorException(RuntimeException ex, WebRequest request) {
        log.error(ERROR_500, ex);
        return getResponseEntity(INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<ResponseError> getResponseEntity(HttpStatus httpStatus, String message) {
        ResponseError responseError = ResponseError.builder()
                .errorDescription(message)
                .status(httpStatus.name())
                .build();
        return ResponseEntity
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseError);
    }
}