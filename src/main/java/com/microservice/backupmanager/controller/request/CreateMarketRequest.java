package com.microservice.backupmanager.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalTime;

public record CreateMarketRequest(

        @NotBlank(message = "O nome é obrigatório.")
        String name,

        @NotBlank(message = "O CNPJ é obrigatório")
        @CNPJ(message = "CNPJ inválido.")
        String cnpj,

        @NotNull(message = "Horário esperado de backup é obrigatório.")
        LocalTime expectedBackupTime
) {}
