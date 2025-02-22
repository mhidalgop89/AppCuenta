package com.bank.appcuenta.appcuenta.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "cuenta")
@Data
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta")
    @NotEmpty
    private String numeroCuenta;

    @Column(name = "tipo_cuenta")
    @NotEmpty
    private String tipoCuenta;

    @Column(name = "saldo_inicial")
    @NotNull
    private Double saldoInicial;

    @NotEmpty
    private Boolean estado;

    @NotNull
    @Column(name = "cliente_id")
    private Long idCliente;
}
