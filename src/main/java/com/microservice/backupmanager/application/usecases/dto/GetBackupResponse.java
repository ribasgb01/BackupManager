package com.microservice.backupmanager.application.usecases.dto;

import com.microservice.backupmanager.domain.BackupHistory;
import org.springframework.data.domain.Page;

public record GetBackupResponse(
        Page<BackupHistory> backupHistoryPage,
        String marketName
) {
}
