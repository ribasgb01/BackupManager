package com.microservice.backupmanager.application.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message){
        super(message);
    }
}
