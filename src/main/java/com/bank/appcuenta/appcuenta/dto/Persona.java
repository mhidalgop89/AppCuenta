package com.bank.appcuenta.appcuenta.dto;

import lombok.Data;

@Data
public class Persona {
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
