package com.bank.appcuenta.appcuenta.models.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CuentaTest {
    private final Cuenta cuenta = new Cuenta();

    @Test
    void creationOfCuentaObject(){
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setNumeroCuenta("1111");
        Assertions.assertNotNull(cuenta);
        Assertions.assertEquals("Ahorros", cuenta.getTipoCuenta());
    }
}
