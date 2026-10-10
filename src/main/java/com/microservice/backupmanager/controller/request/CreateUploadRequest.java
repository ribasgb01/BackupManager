package com.microservice.backupmanager.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateUploadRequest(

        @Positive(message = "Arquivo inválido.")
        Long fileSizeBytes,

        @NotBlank(message = "Nome do arquivo é obrigatório.")
        String fileName
) {
}
