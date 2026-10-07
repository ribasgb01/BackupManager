package com.microservice.backupmanager.domain;

import java.time.LocalTime;
import java.util.UUID;

public class Market {
    UUID id;
    String name;
    String cnpj;
    String apiKey;
    LocalTime expectedBackupTime;
    Boolean active;
}
