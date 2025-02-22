package com.bank.appcuenta.appcuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppCuentaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppCuentaApplication.class, args);
    }

}
