package com.bank.appcuenta.appcuenta.models.dao;

import com.bank.appcuenta.appcuenta.models.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovimientoDao extends JpaRepository<Movimiento, Long> {
    public Movimiento findTopByIdCuentaOrderByFechaDesc(Long idCuenta);
    public List<Movimiento> findMovimientosByFechaAfterAndFechaBeforeAndIdCuentaInOrderByFechaAsc(Date fechaDesde, Date fechaHasta, List<Long> idCuentas);

}
