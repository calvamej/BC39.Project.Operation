package com.bootcamp.project.operation.service;

import com.bootcamp.project.operation.entity.OperationEntity;
import com.bootcamp.project.operation.entity.OperationReportEntity;
import com.bootcamp.project.operation.exception.CustomNotFoundException;
import com.bootcamp.project.operation.repository.OperationRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class OperationServiceImplementation implements OperationService{
    private static Logger Log = Logger.getLogger(OperationServiceImplementation.class);
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Flux<OperationEntity> getAll() {
        return operationRepository.findAll().switchIfEmpty(Mono.error(new CustomNotFoundException("Operations not found")));
    }
    @Override
    public Mono<OperationEntity> getOne(String operationNumber) {
        return operationRepository.findAll().filter(x -> x.getOperationNumber() != null && x.getOperationNumber().equals(operationNumber)).next();
    }
    @Override
    public Mono<OperationEntity> save(OperationEntity colEnt) {
        colEnt.setCreateDate(new Date());
        return operationRepository.save(colEnt);
    }

    @Override
    public Mono<OperationEntity> update(String operationNumber, double amount) {
        return getOne(operationNumber).flatMap(c -> {
            c.setAmount(amount);
            c.setModifyDate(new Date());
            return operationRepository.save(c);
        }).switchIfEmpty(Mono.error(new CustomNotFoundException("Operation not found")));
    }

    @Override
    public Mono<Void> delete(String operationNumber) {
        return getOne(operationNumber)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Operation not found")))
                .flatMap(c -> {
                    return operationRepository.delete(c);
                });
    }
    @Override
    public Mono<OperationEntity> addOperation(OperationEntity colEnt) {
        colEnt.setCreateDate(new Date());
        return getOne(colEnt.getOperationNumber())
                .switchIfEmpty(operationRepository.save(colEnt));
    }
    @Override
    public Flux<OperationEntity> getByClientAndProduct(String clientDocumentNumber, String productCode)
    {
        return operationRepository.findAll().filter(x -> x.getClientDocumentNumber() != null && x.getClientDocumentNumber().equals(clientDocumentNumber)
                && x.getProductCode() != null && x.getProductCode().equals(productCode))
                .switchIfEmpty(Mono.error(new CustomNotFoundException("The client doesn't have operations related to the type of product")));

    }
    @Override
    public Flux<OperationEntity> getByAccount(String accountNumber)
    {
        return operationRepository.findAll().filter(x -> x.getAccountNumber() != null && x.getAccountNumber().equals(accountNumber))
                .switchIfEmpty(Mono.error(new CustomNotFoundException("The client doesn't have operations related to that account")));

    }
    @Override
    public Flux<OperationEntity> getByCredit(String creditNumber)
    {
        return operationRepository.findAll().filter(x -> x.getCreditNumber() != null && x.getCreditNumber().equals(creditNumber))
                .switchIfEmpty(Mono.error(new CustomNotFoundException("The client doesn't have operations related to that credit")));

    }
    @Override
    public Flux<OperationReportEntity> getCommissionsByProduct(Date initialDate, Date finalDate)
    {
        return operationRepository.findAll().filter(x -> x.getOperationType() != null && x.getOperationType().equals("Commission"))
                .filter(c -> c.getCreateDate() != null && c.getCreateDate().after(initialDate) && c.getCreateDate().before(finalDate))
                .groupBy(OperationEntity::getProductCode)
                .flatMap(a -> a
                        .collectList().map(list ->
                                new OperationReportEntity(a.key(), list)))
                .switchIfEmpty(Mono.error(new CustomNotFoundException("Commissions not found.")));
    }
    @Override
    public Flux<OperationEntity> getLast10ByDebitCard(String debitCardNumber)
    {
        return operationRepository.findAll().filter(x -> x.getDebitCardNumber() != null && x.getDebitCardNumber().equals(debitCardNumber))
                .sort(Comparator.comparing(OperationEntity::getCreateDate))
                .takeLast(10)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("The debit card does not have operations")));

    }
    @Override
    public Flux<OperationEntity> getLast10ByCreditCard(String creditCardNumber) {
        return operationRepository.findAll().filter(x -> x.getCreditCardNumber() != null && x.getCreditCardNumber().equals(creditCardNumber))
                .sort(Comparator.comparing(OperationEntity::getCreateDate))
                .takeLast(10)
                .switchIfEmpty(Mono.error(new CustomNotFoundException("The credit card does not have operations")));
    }
}
