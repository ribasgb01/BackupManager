package com.microservice.backupmanager.application.usecases;

import com.microservice.backupmanager.application.exceptions.BusinessRuleException;
import com.microservice.backupmanager.application.exceptions.EntityNotFoundException;
import com.microservice.backupmanager.application.gateways.BackupHistoryGateway;
import com.microservice.backupmanager.application.gateways.FileStorageGateway;
import com.microservice.backupmanager.application.gateways.MarketGateway;
import com.microservice.backupmanager.application.usecases.dto.BackupUploadResponse;
import com.microservice.backupmanager.domain.BackupHistory;
import com.microservice.backupmanager.domain.Market;
import com.microservice.backupmanager.domain.enums.BackupStatus;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RequiredArgsConstructor
public class BackupHistoryUseCase {

    private final BackupHistoryGateway backupHistoryGateway;
    private final FileStorageGateway fileStorageGateway;
    private final MarketGateway marketGateway;

    private static final Duration UPLOAD_URL_EXPIRATION = Duration.ofMinutes(30);
    private static final Duration DOWNLOAD_URL_EXPIRATION = Duration.ofMinutes(15);

    public BackupUploadResponse requestBackupUpload(String apiKey, String fileName, Long fileSizeBytes){

        Market market = marketGateway.findByApiKey(apiKey)
                .orElseThrow(() -> new EntityNotFoundException("Este mercado não foi encontrado."));

        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDate = currentDate.format(formatter);

        String fileStoragePath = String.format(
                "markets/%s/backups/%d/%02d/%s_%s",
                market.getId(),
                currentDate.getYear(),
                currentDate.getMonthValue(),
                formattedDate,
                fileName
        );

        String presignedUrl = fileStorageGateway.generateUploadPresignedUrl(fileStoragePath, UPLOAD_URL_EXPIRATION);

        BackupHistory backupHistory = BackupHistory.builder()
                .id(UUID.randomUUID())
                .marketId(market.getId())
                .createdAt(currentDate)
                .completedAt(null)
                .fileSizeBytes(fileSizeBytes)
                .fileHashMd5(null)
                .s3Key(fileStoragePath)
                .status(BackupStatus.PENDING)
                .errorMessage(null)
                .build();

        BackupHistory savedBackupHistory = backupHistoryGateway.save(backupHistory);

        return new BackupUploadResponse(savedBackupHistory.getId(), presignedUrl);
    }

    public void completeBackup(UUID backupId, BackupStatus status, String errorMessage, String fileHashMd5){

        BackupHistory backupHistory = backupHistoryGateway.findById(backupId)
                .orElseThrow(() -> new EntityNotFoundException("Este backup não foi encontrado."));

        if(backupHistory.getStatus() != BackupStatus.PENDING){
            throw new BusinessRuleException("Este backup não está constando como pendente.");
        }

        if (status == BackupStatus.FAILED){
            if (errorMessage == null){
                throw new IllegalArgumentException("A mensagem de erro é obrigatória quando o status for FAILED.");
            }
        }

        backupHistory.setStatus(status);
        backupHistory.setErrorMessage(errorMessage);
        backupHistory.setFileHashMd5(fileHashMd5);
        backupHistory.setCompletedAt(LocalDateTime.now());

        backupHistoryGateway.save(backupHistory);
    }

    public String requestBackupDownload(UUID backupId){

        BackupHistory backupHistory = backupHistoryGateway.findById(backupId)
                .orElseThrow(() -> new EntityNotFoundException("Este backup não foi encontrado."));

        if (backupHistory.getStatus() != BackupStatus.SUCCESS){
            throw new BusinessRuleException("Este backup não foi gerado com sucesso, não é possível recuperar.");
        }

        return fileStorageGateway.generateDownloadPresignedUrl(backupHistory.getS3Key(), DOWNLOAD_URL_EXPIRATION);
    }
}
