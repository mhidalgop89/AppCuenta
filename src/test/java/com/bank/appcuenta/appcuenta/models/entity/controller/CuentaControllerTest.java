package com.bank.appcuenta.appcuenta.models.entity.controller;

import com.bank.appcuenta.appcuenta.controller.CuentaController;
import com.bank.appcuenta.appcuenta.models.entity.Cuenta;
import com.bank.appcuenta.appcuenta.models.service.ICuentaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CuentaControllerTest {

    @Mock
    private ICuentaService iCuentaService;
    @InjectMocks
    private CuentaController controller;

    @Test
    void findAll(){
        when(iCuentaService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Cuenta>> result = controller.findAll();
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(result.getStatusCode().value(), HttpStatus.OK.value());
    }

}
