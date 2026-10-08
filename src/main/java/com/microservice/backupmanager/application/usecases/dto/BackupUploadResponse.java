package com.microservice.backupmanager.application.usecases.dto;

import java.util.UUID;

public record BackupUploadResponse(
        UUID backupHistoryId,
        String preSignedUrl
) {
}
