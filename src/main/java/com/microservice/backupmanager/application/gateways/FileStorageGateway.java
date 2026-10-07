package com.microservice.backupmanager.application.gateways;

import java.time.Duration;

public interface FileStorageGateway {
    String generateUploadPresignedUrl(String fileStoragePath, Duration duration);
    String generateDownloadPresignedUrl(String fileStoragePath, Duration duration);
    void deleteFile(String fileStoragePath);
}
