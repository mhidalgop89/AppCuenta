package com.bank.appcuenta.appcuenta.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BalanceNotAvailableException extends RuntimeException{

    private String code;
    private HttpStatus httpStatus;

    public BalanceNotAvailableException(String code, String message, HttpStatus httpStatus){
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
