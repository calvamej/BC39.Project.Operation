package com.bootcamp.project.operation.service;

import com.bootcamp.project.operation.entity.OperationEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationService {
    /*
CRUD BÁSICO
*/
    public Mono<OperationEntity> getOne(String accountNumber);
    public Flux<OperationEntity> getAll();
    public Mono<OperationEntity> save(OperationEntity colEnt);
    public Mono<OperationEntity> update(String accountNumber, double amount);
    public Mono<Void> delete(String accountNumber);
    public Flux<OperationEntity> getOperationsByClient(String client);
    public Flux<OperationEntity> getAccountOperationsByClient(String client, String accountNumber);
    public Flux<OperationEntity> getCreditOperationsByClient(String client, String creditNumber);
}
