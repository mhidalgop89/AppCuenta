package com.bank.appcuenta.appcuenta.dto;

import lombok.Data;

@Data
public class Cliente extends Persona{
    private Long clienteid;
    private String password;
    private String estado;
}
