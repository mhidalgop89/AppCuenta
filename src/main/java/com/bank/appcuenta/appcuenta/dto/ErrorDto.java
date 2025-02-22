package com.bank.appcuenta.appcuenta.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ErrorDto {
    private String code;
    private Map<String, String> messages;
}
