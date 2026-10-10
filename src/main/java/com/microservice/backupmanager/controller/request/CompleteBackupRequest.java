package com.microservice.backupmanager.controller.request;

import com.microservice.backupmanager.domain.enums.BackupStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompleteBackupRequest(

        @NotNull(message = "Status não foi informado.")
        BackupStatus status,

        @NotBlank(message = "Hash MD5 não foi informado.")
        String fileHashMd5,

        String errorMessage
) {
}
