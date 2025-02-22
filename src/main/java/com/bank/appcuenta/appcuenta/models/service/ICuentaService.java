package com.bank.appcuenta.appcuenta.models.service;

import com.bank.appcuenta.appcuenta.models.entity.Cuenta;

import java.util.List;

public interface ICuentaService {
    public List<Cuenta> findAll();
    public Cuenta save(Cuenta cuenta);
    public Cuenta update(Cuenta cuenta);
    public void delete(Long id);
}
