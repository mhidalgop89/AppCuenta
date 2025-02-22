package com.bank.appcuenta.appcuenta.clients;

import com.bank.appcuenta.appcuenta.dto.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AppClient", url = "localhost:8080/clientes")
public interface ClienteFeign {

    @GetMapping("/{identificacion}")
    public Cliente findClienteByIdentificacion(@PathVariable String identificacion);
}
