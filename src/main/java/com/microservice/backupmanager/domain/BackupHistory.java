package com.microservice.backupmanager.domain;

import com.microservice.backupmanager.domain.enums.BackupStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackupHistory {
    UUID id;
    UUID marketId;
    LocalDateTime createdAt;
    LocalDateTime completedAt;
    Long fileSizeBytes;
    String fileHashMd5;
    String s3Key;
    BackupStatus status;
    String errorMessage;
}
