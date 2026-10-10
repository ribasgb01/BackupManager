package com.microservice.backupmanager.controller.response;

import com.microservice.backupmanager.domain.enums.BackupStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BackupHistoryResponse(

        UUID id,
        UUID marketId,
        String marketName,
        LocalDateTime createdAt,
        LocalDateTime completedAt,
        Long fileSizeBytes,
        BackupStatus status,
        String errorMessage
) {}
