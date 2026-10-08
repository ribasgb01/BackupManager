package com.microservice.backupmanager.application.usecases;

import com.microservice.backupmanager.application.exceptions.EntityAlreadyExistsException;
import com.microservice.backupmanager.application.exceptions.EntityNotFoundException;
import com.microservice.backupmanager.application.gateways.MarketGateway;
import com.microservice.backupmanager.domain.Market;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class MarketUseCase {

    private final MarketGateway marketGateway;

    public Market registerMarket(String name, String cnpj, LocalTime expectedBackupTime){

        if(!marketGateway.existsByCnpj(cnpj)){
            throw new EntityAlreadyExistsException("Esse CNPJ já está cadastrado.");
        }

        String apiKey = UUID.randomUUID().toString();

        return Market.builder()
                .id(UUID.randomUUID())
                .name(name)
                .cnpj(cnpj)
                .apiKey(apiKey)
                .expectedBackupTime(expectedBackupTime)
                .active(true)
                .build();
    }

    public List<Market> listActiveMarkets(){
        return marketGateway.findAllActive();
    }

    public Market getMarketById(UUID id){
        return marketGateway.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Este mercado não foi encontrado."));
    }
}
