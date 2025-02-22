package com.bank.appcuenta.appcuenta.models.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "movimiento")
@Data
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date fecha;
    @Column(name = "tipo_movimiento")
    @NotEmpty
    private String tipoMovimiento;

    @NotNull
    private Double valor;

    @NotNull
    private Double saldo;

    @NotNull
    @Column(name = "id_cuenta")
    private Long idCuenta;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id")
    private Cuenta cuenta;*/

}
