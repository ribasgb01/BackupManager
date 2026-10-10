package com.microservice.backupmanager.controller.response;

import java.util.UUID;

public record BackupUploadResponse(
        String uploadUrl,
        UUID backupId
) {
}
