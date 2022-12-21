package com.bootcamp.project.operation.controller;

import com.bootcamp.project.operation.entity.OperationEntity;
import com.bootcamp.project.operation.service.OperationService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.ArgumentMatchers.*;


@RunWith(SpringRunner.class)
@WebFluxTest(OperationController.class)
public class OperationControllerImplementationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private OperationService operationService;

    @Test
    public void save()
    {
        OperationEntity OE = new OperationEntity();
        Mono<OperationEntity> MTest = Mono.just(OE);
        when(operationService.save(OE)).thenReturn(MTest);
        webTestClient.post().uri("/Operation/Save")
                .body(Mono.just(MTest),OperationEntity.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void update()
    {
        OperationEntity OE = new OperationEntity();
        Mono<OperationEntity> MTest = Mono.just(OE);
        when(operationService.update("ABC",400)).thenReturn(MTest);
        webTestClient.put().uri("/Operation/Update/ABC/400")
                .body(Mono.just(MTest),OperationEntity.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void delete()
    {
        given(operationService.delete(any())).willReturn(Mono.empty());
        webTestClient.delete().uri("/Operation/Delete/ABC")
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void getOne()
    {
        OperationEntity OE = new OperationEntity("AAA",null,null,null,null,null,null,null,null,0,null,null);
        Mono<OperationEntity> MTest = Mono.just(OE);
        when(operationService.getOne(any())).thenReturn(MTest);
        Flux<OperationEntity> responseBody = webTestClient.get().uri("/Operation/FindOne/AAA")
                .exchange()
                .expectStatus().isOk()
                .returnResult(OperationEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p -> p.getId().equals("AAA"))
                .verifyComplete();
    }
    @Test
    public void getAll()
    {
        OperationEntity OE = new OperationEntity(null,"AAA",null,null,null,null,null,null,null,0,null,null);
        OperationEntity OE2 = new OperationEntity(null,"BBB",null,null,null,null,null,null,null,0,null,null);
        Flux<OperationEntity> MTest = Flux.just(OE,OE2);
        when(operationService.getAll()).thenReturn(MTest);
        Flux<OperationEntity> responseBody = webTestClient.get().uri("/Operation/FindAll")
                .exchange()
                .expectStatus().isOk()
                .returnResult(OperationEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(OE)
                .expectNext(OE2)
                .verifyComplete();
    }
    @Test
    public void getLast10ByDebitCard()
    {
        OperationEntity OE = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE2 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE3 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE4 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE5 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE6 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE7 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE8 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE9 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE10 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);

        Flux<OperationEntity> MTest = Flux.just(OE,OE2,OE3,OE4,OE5,OE6,OE7,OE8,OE9,OE10);
        when(operationService.getLast10ByDebitCard(any())).thenReturn(MTest);
        Flux<OperationEntity> responseBody = webTestClient.get().uri("/Operation/GetLast10ByDebitCard/1234")
                .exchange()
                .expectStatus().isOk()
                .returnResult(OperationEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(OE)
                .expectNext(OE2)
                .expectNext(OE3)
                .expectNext(OE4)
                .expectNext(OE5)
                .expectNext(OE6)
                .expectNext(OE7)
                .expectNext(OE8)
                .expectNext(OE9)
                .expectNext(OE10)
                .verifyComplete();
    }
    @Test
    public void getLast10ByCreditCard()
    {
        OperationEntity OE = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE2 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE3 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE4 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE5 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE6 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE7 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE8 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE9 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);
        OperationEntity OE10 = new OperationEntity(null,"AAA",null,null,null,null,null,null,"1234",0,null,null);

        Flux<OperationEntity> MTest = Flux.just(OE,OE2,OE3,OE4,OE5,OE6,OE7,OE8,OE9,OE10);
        when(operationService.getLast10ByCreditCard(any())).thenReturn(MTest);
        Flux<OperationEntity> responseBody = webTestClient.get().uri("/Operation/GetLast10ByCreditCard/1234")
                .exchange()
                .expectStatus().isOk()
                .returnResult(OperationEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(OE)
                .expectNext(OE2)
                .expectNext(OE3)
                .expectNext(OE4)
                .expectNext(OE5)
                .expectNext(OE6)
                .expectNext(OE7)
                .expectNext(OE8)
                .expectNext(OE9)
                .expectNext(OE10)
                .verifyComplete();
    }
}
