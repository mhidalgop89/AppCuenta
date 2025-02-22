package com.bank.appcuenta.appcuenta.models.dao;

import com.bank.appcuenta.appcuenta.models.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaDao extends JpaRepository<Cuenta, Long> {

    public Cuenta findCuentaByNumeroCuenta(String numeroCuenta);
    public List<Cuenta> findCuentasByIdCliente(Long idCliente);
}
