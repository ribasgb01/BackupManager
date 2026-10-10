package com.microservice.backupmanager.controller.response;

import java.time.LocalTime;
import java.util.UUID;

public record MarketResponse(
        UUID id,
        String name,
        String cnpj,
        String apiKey,
        LocalTime expectedBackupTime,
        Boolean active
) {}
