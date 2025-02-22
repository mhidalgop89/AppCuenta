package com.bank.appcuenta.appcuenta.models.service;


import com.bank.appcuenta.appcuenta.models.entity.Movimiento;
import com.bank.appcuenta.appcuenta.models.response.ReporteEstadoCuentaResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMovimientoService {

    public List<Movimiento> findAll();
    public Movimiento save(Movimiento movimiento);
    public Movimiento update(Movimiento movimiento);

    public Movimiento registro(String tipoMovimiento, Double valor, String numeroCuenta);
    public void delete(Long id);
    public Map<String, List<ReporteEstadoCuentaResponse>> reporteEstadoDeCuenta(Date fechaInicio, Date fechaFin, String identificacion);
}
