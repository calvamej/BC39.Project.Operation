package com.bootcamp.project.operation.service;

import com.bootcamp.project.operation.entity.OperationEntity;
import com.bootcamp.project.operation.exception.CustomNotFoundException;
import com.bootcamp.project.operation.repository.OperationRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

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
        return operationRepository.findAll().filter(x -> x.getOperationNumber().equals(operationNumber)).next();
    }
    @Override
    public Mono<OperationEntity> save(OperationEntity colEnt) {
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
    public Flux<OperationEntity> getByClientAndProduct(String clientDocumentNumber, String productCode)
    {
        return operationRepository.findAll().filter(x -> x.getClientDocumentNumber().equals(clientDocumentNumber)
                && x.getProductCode().equals(productCode))
                .switchIfEmpty(Mono.error(new CustomNotFoundException("The client doesn't have operations related to the type of product")));

    }
    @Override
    public Flux<OperationEntity> getByClientAndProduct_Month(String clientDocumentNumber, String productCode)
    {
        Instant instant = Instant.now();
        return operationRepository.findAll().filter(x -> x.getClientDocumentNumber().equals(clientDocumentNumber)
                        && x.getProductCode().equals(productCode)
                && x.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() == instant.atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue())
                .switchIfEmpty(Mono.error(new CustomNotFoundException("The client doesn't have operations related to the type of product this month")));

    }
}
