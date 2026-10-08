package com.microservice.backupmanager.infrastructure.persistence.mappers;

import com.microservice.backupmanager.domain.BackupHistory;
import com.microservice.backupmanager.infrastructure.persistence.entities.BackupHistoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BackupHistoryMapper {

    BackupHistoryEntity toDomain(BackupHistory domain);
    BackupHistory toEntity(BackupHistoryEntity entity);
}
