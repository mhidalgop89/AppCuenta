package com.bank.appcuenta.appcuenta.models.service.impl;

import com.bank.appcuenta.appcuenta.models.dao.CuentaDao;
import com.bank.appcuenta.appcuenta.models.entity.Cuenta;
import com.bank.appcuenta.appcuenta.models.service.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    private CuentaDao cuentaDao;
    @Override
    public List<Cuenta> findAll() {
        return cuentaDao.findAll();
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return cuentaDao.save(cuenta);
    }

    @Override
    public Cuenta update(Cuenta cuenta) {
        Cuenta c = new Cuenta();
        c.setId(cuenta.getId());
        c.setNumeroCuenta(cuenta.getNumeroCuenta());
        c.setEstado(cuenta.getEstado());
        c.setTipoCuenta(cuenta.getTipoCuenta());
        c.setSaldoInicial(cuenta.getSaldoInicial());

        return cuentaDao.save(c);
    }

    @Override
    public void delete(Long id) {
        cuentaDao.deleteById(id);
    }
}
