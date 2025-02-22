package com.bank.appcuenta.appcuenta.controller;

import com.bank.appcuenta.appcuenta.models.entity.Cuenta;
import com.bank.appcuenta.appcuenta.models.service.ICuentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
@AllArgsConstructor
public class CuentaController {

    private ICuentaService iCuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> findAll(){
        return ResponseEntity.ok()
                .body(iCuentaService.findAll());
    }

    @PostMapping
    public ResponseEntity<Cuenta> save(@RequestBody @Valid Cuenta cuenta){
        return ResponseEntity.ok()
                .body(iCuentaService.save(cuenta));
    }
    @PutMapping
    public ResponseEntity<Cuenta> update(@RequestBody @Valid Cuenta cuenta){
        return ResponseEntity.ok()
                .body(iCuentaService.update(cuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iCuentaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
