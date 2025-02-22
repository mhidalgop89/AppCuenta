package com.bank.appcuenta.appcuenta.controller;

import com.bank.appcuenta.appcuenta.models.entity.Movimiento;
import com.bank.appcuenta.appcuenta.models.response.ReporteEstadoCuentaResponse;
import com.bank.appcuenta.appcuenta.models.service.IMovimientoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class MovimientoController {

    private IMovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimiento>> findAll(){
        return ResponseEntity.ok()
                .body(movimientoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Movimiento> save(@RequestBody @Valid Movimiento movimiento){
        return ResponseEntity.ok()
                .body(movimientoService.save(movimiento));
    }

    @PutMapping("/{tipoMovimiento}/{valor}")
    public ResponseEntity<Movimiento> registro(@PathVariable String tipoMovimiento, @PathVariable Double valor,
                                               @RequestParam(name = "numero-cuenta", required = true) String numeroCuenta){
        return ResponseEntity.ok()
                .body(movimientoService.registro(tipoMovimiento, valor, numeroCuenta));
    }

    @GetMapping("/reportes")
    public ResponseEntity<Map<String, List<ReporteEstadoCuentaResponse>> > reporteEstadoDeCuenta(@RequestParam(name = "fecha-inicio") String fechaInicio,
                                                                                                 @RequestParam(name = "fecha-fin") String fechaFin,
                                                                                                 @RequestParam(name = "identificacion") String identificacion) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date fechaDesde = formatter.parse(fechaInicio);
        Date fechaHasta = formatter.parse(fechaFin);
        return ResponseEntity.ok()
                .body(movimientoService.reporteEstadoDeCuenta(fechaDesde, fechaHasta, identificacion));
    }

    @PutMapping
    public ResponseEntity<Movimiento> update(@RequestBody @Valid Movimiento movimiento){
        return ResponseEntity.ok()
                .body(movimientoService.update(movimiento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movimientoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
