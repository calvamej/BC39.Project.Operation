package com.bootcamp.project.operation.service;

import com.bootcamp.project.operation.entity.OperationEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationService {

    public Flux<OperationEntity> getAll();
    public Mono<OperationEntity> getOne(String accountNumber);

    public Mono<OperationEntity> save(OperationEntity colEnt);
    public Mono<OperationEntity> update(String accountNumber, double amount);
    public Mono<Void> delete(String accountNumber);
    public Flux<OperationEntity> getByClientAndProduct(String clientDocumentNumber, String productCode);
    public Flux<OperationEntity> getByClientAndProduct_Month(String clientDocumentNumber, String productCode);

}
