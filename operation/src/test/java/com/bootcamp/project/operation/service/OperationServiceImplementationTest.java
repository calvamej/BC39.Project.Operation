package com.bootcamp.project.operation.service;

import com.bootcamp.project.operation.entity.OperationEntity;
import com.bootcamp.project.operation.repository.OperationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

class OperationServiceImplementationTest {
    @InjectMocks
    OperationServiceImplementation operationServiceImplementation;

    @Mock
    OperationRepository operationRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getLast10ByDebitCard() {
        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setDebitCardNumber("12345");
        List<OperationEntity> operationEntityList = new ArrayList<>();
        operationEntityList.add(operationEntity);
        Mockito.when(operationRepository.findAll()).thenReturn(Flux.fromIterable(operationEntityList));
        Flux<OperationEntity> result = operationServiceImplementation.getLast10ByDebitCard("12345");
        Assertions.assertNotNull(result);
    }

    @Test
    void getLast10ByCreditCard() {
        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setDebitCardNumber("12345");
        List<OperationEntity> operationEntityList = new ArrayList<>();
        operationEntityList.add(operationEntity);
        Mockito.when(operationRepository.findAll()).thenReturn(Flux.fromIterable(operationEntityList));
        Flux<OperationEntity> result = operationServiceImplementation.getLast10ByCreditCard("12345");
        Assertions.assertNotNull(result);
    }
}