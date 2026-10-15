package com.microservice.backupmanager.infrastructure.config;

import com.microservice.backupmanager.application.gateways.BackupHistoryGateway;
import com.microservice.backupmanager.application.gateways.FileStorageGateway;
import com.microservice.backupmanager.application.gateways.MarketGateway;
import com.microservice.backupmanager.application.usecases.BackupHistoryUseCase;
import com.microservice.backupmanager.application.usecases.MarketUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public BackupHistoryUseCase backupHistoryUseCase(
            BackupHistoryGateway backupHistoryGateway,
            FileStorageGateway fileStorageGateway,
            MarketGateway marketGateway
    ){
        return new BackupHistoryUseCase(backupHistoryGateway, fileStorageGateway, marketGateway);
    }

    @Bean
    public MarketUseCase marketUseCase(
            MarketGateway marketGateway
    ){
        return new MarketUseCase(marketGateway);
    }
}
