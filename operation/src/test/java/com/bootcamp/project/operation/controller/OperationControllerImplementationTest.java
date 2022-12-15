package com.bootcamp.project.operation.controller;

import com.bootcamp.project.operation.entity.OperationEntity;
import com.bootcamp.project.operation.service.OperationService;
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
        OperationEntity OE = new OperationEntity("AAA",null, null, null, null, null, null, null, 0, null, null, null,null);
        Mono<OperationEntity> MTest = Mono.just(OE);
        when(operationService.getOne(any())).thenReturn(MTest);
        Flux<OperationEntity> responseBody = webTestClient.get().uri("/Operation/FindOne/AAA")
                .exchange()
                .expectStatus().isOk()
                .returnResult(OperationEntity.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p -> p.getOperationNumber().equals("AAA"))
                .verifyComplete();
    }
    @Test
    public void getAll()
    {
        OperationEntity OE = new OperationEntity("AAA",null, null, null, null, null, null, null, 0, null, null, null,null);
        OperationEntity OE2 = new OperationEntity("BBB",null, null, null, null, null, null, null, 0, null, null, null,null);
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
}
