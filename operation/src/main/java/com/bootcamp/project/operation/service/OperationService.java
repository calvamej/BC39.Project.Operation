package com.bootcamp.project.operation.service;

import com.bootcamp.project.operation.entity.OperationEntity;
import com.bootcamp.project.operation.entity.report.OperationReportEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface OperationService {

    public Flux<OperationEntity> getAll();
    public Mono<OperationEntity> getOne(String accountNumber);
    public Mono<OperationEntity> save(OperationEntity colEnt);
    public Mono<OperationEntity> update(String accountNumber, double amount);
    public Mono<Void> delete(String accountNumber);
    public Mono<OperationEntity> addOperation(OperationEntity colEnt);
    public Flux<OperationEntity> getByClientAndProduct(String clientDocumentNumber, String productCode);
    public Flux<OperationEntity> getByAccount(String accountNumber);
    public Flux<OperationEntity> getByCredit(String creditNumber);
    public Flux<OperationReportEntity> getCommissionsByProduct(Date initialDate, Date finalDate);
    public Flux<OperationEntity> getLast10ByDebitCard(String debitCardNumber);
    public Flux<OperationEntity> getLast10ByCreditCard(String creditCardNumber);

}
