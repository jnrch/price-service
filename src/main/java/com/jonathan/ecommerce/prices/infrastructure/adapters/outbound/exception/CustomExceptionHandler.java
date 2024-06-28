package com.jonathan.ecommerce.prices.infrastructure.adapters.outbound.exception;

import com.jonathan.ecommerce.prices.domain.exceptions.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RestControllerAdvice
public class CustomExceptionHandler {
    private final LocalDateTime timestamp = LocalDateTime.now();

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleAllExceptions(Exception exception) {
        return ErrorResponse.builder()
                .code(ErrorCatalog.GENERIC_ERROR.getCode())
                .message(ErrorCatalog.GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(timestamp)
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PriceNotFoundException.class)
    public ErrorResponse handlePriceNotFoundException(PriceNotFoundException ex) {
        return ErrorResponse.builder()
                .code(ErrorCatalog.PRICE_NOT_FOUND.getCode())
                .message(ErrorCatalog.PRICE_NOT_FOUND.getMessage())
                .details(Collections.singletonList(ex.getMessage()))
                .timestamp(timestamp)
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDateTimeParseException(MethodArgumentTypeMismatchException ex) {
        String error = Optional.ofNullable(ex.getRequiredType())
                .map(reqType -> String.format("%s should be of type %s", ex.getName(), reqType.getName()))
                .orElse(String.format("%s has an invalid type", ex.getName()));

        return ErrorResponse.builder()
                .code(ErrorCatalog.TYPE_MISMATCH_ERROR.getCode())
                .message(ErrorCatalog.TYPE_MISMATCH_ERROR.getMessage())
                .details(Collections.singletonList(error))
                .timestamp(timestamp)
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDateTimeParseException(MissingServletRequestParameterException ex) {
        return ErrorResponse.builder()
                .code(ErrorCatalog.REQUIRED_PARAMETER_ERROR.getCode())
                .message(ErrorCatalog.REQUIRED_PARAMETER_ERROR.getMessage())
                .details(Collections.singletonList(ex.getMessage()))
                .timestamp(timestamp)
                .build();
    }
}
