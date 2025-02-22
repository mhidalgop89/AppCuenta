package com.bank.appcuenta.appcuenta.controller;


import com.bank.appcuenta.appcuenta.dto.ErrorDto;
import com.bank.appcuenta.appcuenta.exception.BalanceNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler( value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> argumentValidation(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ErrorDto errorDto = ErrorDto.builder().code("err-001").messages(errors).build();
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BalanceNotAvailableException.class)
    public ResponseEntity<ErrorDto> balanceNotAvailable(BalanceNotAvailableException balanceNotAvailableException){
        Map<String, String> errors = new HashMap<>();
        errors.put("not_avail",balanceNotAvailableException.getMessage());
        ErrorDto errorDto = ErrorDto.builder()
                .code(balanceNotAvailableException.getCode())
                .messages(errors)
                .build();
        return new ResponseEntity<>(errorDto, balanceNotAvailableException.getHttpStatus());
    }
}
