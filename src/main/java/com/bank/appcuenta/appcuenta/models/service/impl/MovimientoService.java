package com.bank.appcuenta.appcuenta.models.service.impl;

import com.bank.appcuenta.appcuenta.clients.ClienteFeign;
import com.bank.appcuenta.appcuenta.dto.Cliente;
import com.bank.appcuenta.appcuenta.exception.BalanceNotAvailableException;
import com.bank.appcuenta.appcuenta.models.dao.CuentaDao;
import com.bank.appcuenta.appcuenta.models.dao.MovimientoDao;
import com.bank.appcuenta.appcuenta.models.entity.Cuenta;
import com.bank.appcuenta.appcuenta.models.entity.Movimiento;
import com.bank.appcuenta.appcuenta.models.response.ReporteEstadoCuentaResponse;
import com.bank.appcuenta.appcuenta.models.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovimientoService implements IMovimientoService {

    @Autowired
    private MovimientoDao movimientoDao;

    @Autowired
    private CuentaDao cuentaDao;
    @Autowired
    private ClienteFeign clienteFeign;

    private static final Double ZERO = 0.0;

    private static final String BALANCE_ERROR_MESSAGE = "Saldo no disponible";
    private static final String BALANCE_ERROR_CODE = "balance-001";

    @Override
    public List<Movimiento> findAll() {
        return movimientoDao.findAll();
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        return movimientoDao.save(movimiento);
    }

    @Override
    public Movimiento update(Movimiento movimiento) {
        Movimiento m = movimientoDao.findById(movimiento.getId()).orElse(new Movimiento());
        m.setFecha(new Date());
        m.setSaldo(movimiento.getSaldo());
        m.setValor(movimiento.getValor());
        m.setTipoMovimiento(movimiento.getTipoMovimiento());
        return movimientoDao.save(m);
    }

    @Override
    public Movimiento registro(String tipoMovimiento, Double valor, String numeroCuenta) {
        Cuenta cuenta = cuentaDao.findCuentaByNumeroCuenta(numeroCuenta);
        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setFecha(new Date());
        movimiento.setValor(valor);
        movimiento.setIdCuenta(cuenta.getId());
        Movimiento ultimoMovimiento = movimientoDao.findTopByIdCuentaOrderByFechaDesc(cuenta.getId());
        Double saldo = 0.0;
        if(Objects.nonNull(ultimoMovimiento) && Objects.nonNull(ultimoMovimiento.getSaldo())) {
            saldo = ultimoMovimiento.getSaldo() + valor;
        }else{
            saldo = cuenta.getSaldoInicial() + valor;
        }

        if(saldo < ZERO) throw new BalanceNotAvailableException(BALANCE_ERROR_CODE, BALANCE_ERROR_MESSAGE, HttpStatus.BAD_REQUEST);
        movimiento.setSaldo(saldo);

        return movimientoDao.save(movimiento);
    }

    @Override
    public void delete(Long id) {
        movimientoDao.deleteById(id);
    }

    @Override
    public Map<String, List<ReporteEstadoCuentaResponse>>  reporteEstadoDeCuenta(Date fechaInicio, Date fechaFin, String identificacion) {
        Cliente cliente = clienteFeign.findClienteByIdentificacion(identificacion);
        List<Cuenta> cuentaList = cuentaDao.findCuentasByIdCliente(cliente.getClienteid());
        Map<String, List<ReporteEstadoCuentaResponse>> listMovimientoMap = new HashMap<>();
        Map<Long, Cuenta> cuentaMap = new HashMap<>();
        List<Long> listaCuentas = cuentaList.stream()
                .map(cuenta -> {
                    listMovimientoMap.put(cuenta.getNumeroCuenta(), new ArrayList<>());
                    cuentaMap.put(cuenta.getId(), cuenta);
                    return cuenta.getId();
                })
                .toList();
        List<Movimiento> movimientoList = movimientoDao.findMovimientosByFechaAfterAndFechaBeforeAndIdCuentaInOrderByFechaAsc(
                fechaInicio,
                fechaFin,
                listaCuentas
        );
        movimientoList.forEach(movimiento -> {
            Cuenta cuenta =  cuentaMap.get(movimiento.getIdCuenta());
            ReporteEstadoCuentaResponse reporteEstadoCuentaResponse = ReporteEstadoCuentaResponse.builder()
                    .fecha(movimiento.getFecha())
                    .cliente(cliente.getNombre())
                    .numeroCuenta(cuenta.getNumeroCuenta())
                    .tipo(cuenta.getTipoCuenta())
                    .saldoInicial(movimiento.getSaldo()-movimiento.getValor())
                    .estado(cuenta.getEstado())
                    .movimiento(movimiento.getValor())
                    .saldoDisponible(movimiento.getSaldo())
                    .build();
            listMovimientoMap.get(cuenta.getNumeroCuenta()).add(reporteEstadoCuentaResponse);
        });

        return listMovimientoMap;
    }
}
