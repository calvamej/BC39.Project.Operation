package com.bootcamp.project.operation.service;

import com.bootcamp.project.operation.entity.OperationEntity;
import com.bootcamp.project.operation.repository.OperationRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class OperationServiceImplementation implements OperationService{
    private static Logger Log = Logger.getLogger(OperationServiceImplementation.class);
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Mono<OperationEntity> getOne(String operationNumber) {
        Log.info("Inicio método getOne.");
        Mono<OperationEntity> col = operationRepository.findAll().filter(x -> x.getOperationNumber().equals(operationNumber)).next();
        return col;
    }

    @Override
    public Flux<OperationEntity> getAll() {
        Log.info("Inicio método getAll.");
        Flux<OperationEntity> col = operationRepository.findAll();
        return col;
    }

    @Override
    public Mono<OperationEntity> save(OperationEntity colEnt) {
        Log.info("Inicio método save.");
        return operationRepository.save(colEnt);
    }

    @Override
    public Mono<OperationEntity> update(String operationNumber, double amount) {
        Log.info("Inicio método update.");
        Mono<OperationEntity> col = getOne(operationNumber);
        OperationEntity newCol = col.block();
        newCol.setAmount(amount);
        return operationRepository.save(newCol);
    }

    @Override
    public Mono<Void> delete(String operationNumber) {
        Log.info("Inicio método delete.");
        Mono<OperationEntity> col = getOne(operationNumber);
        OperationEntity newCol = col.block();
        return operationRepository.delete(newCol);
    }
    @Override
    public Flux<OperationEntity> getOperationsByClient(String client)
    {
        Log.info("Inicio método getOperationsByClient.");
        Flux<OperationEntity> col = operationRepository.findAll().filter(x -> x.getClient().equals(client));
        return col;
    }
    @Override
    public Flux<OperationEntity> getCreditOperationsByClient(String client, String creditNumber)
    {
        Log.info("Inicio método getCreditOperationsByClient.");
        Flux<OperationEntity> col = operationRepository.findAll().filter(x -> x.getClient().equals(client) && x.getCreditNumber().equals(creditNumber));
        return col;
    }
    @Override
    public Flux<OperationEntity> getAccountOperationsByClient(String client, String accountNumber)
    {
        Log.info("Inicio método getAccountOperationsByClient.");
        Flux<OperationEntity> col = operationRepository.findAll().filter(x -> x.getClient().equals(client) && x.getAccountNumber().equals(accountNumber));
        return col;
    }
}
