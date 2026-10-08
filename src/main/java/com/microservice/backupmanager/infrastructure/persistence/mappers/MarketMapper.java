package com.microservice.backupmanager.infrastructure.persistence.mappers;

import com.microservice.backupmanager.domain.Market;
import com.microservice.backupmanager.infrastructure.persistence.entities.MarketEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarketMapper {

    MarketEntity toDomain(Market domain);
    Market toEntity(MarketEntity entity);
}
