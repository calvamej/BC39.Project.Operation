package com.bootcamp.project.operation.controller;

import com.bootcamp.project.operation.entity.OperationEntity;
import com.bootcamp.project.operation.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/Operation")
public class OperationController {
    @Autowired
    OperationService operationService;

    @GetMapping(value = "/FindOne/{operationNumber}")
    public Mono<OperationEntity> Get_One(@PathVariable("operationNumber") String operationNumber){
        return operationService.getOne(operationNumber);
    }
    @GetMapping(value = "/FindAll")
    public Flux<OperationEntity> Get_All(){

        return operationService.getAll();
    }
    @PostMapping(value = "/Save")
    public Mono<OperationEntity> Save(@RequestBody OperationEntity col){

        return operationService.save(col);
    }
    @PutMapping(value = "/Update/{operationNumber}/{amount}")
    public Mono<OperationEntity> Update(@PathVariable("operationNumber") String accountNumber,@PathVariable("amount") double amount){
        return operationService.update(accountNumber, amount);
    }
    @DeleteMapping  (value = "/Delete/{operationNumber}")
    public Mono<Void> Delete(@PathVariable("operationNumber") String operationNumber){
        return operationService.delete(operationNumber);
    }
    @GetMapping(value = "/getAllOperationsByClient/{client}")
    public Flux<OperationEntity> getOperationsByClient(@PathVariable("client") String client){
        return operationService.getOperationsByClient(client);
    }
    @GetMapping(value = "/getCreditOperationsByClient/{client}/{creditNumber}")
    public Flux<OperationEntity> getCreditOperationsByClient(@PathVariable("client") String client,@PathVariable("creditNumber") String creditNumber){
        return operationService.getCreditOperationsByClient(client, creditNumber);
    }
    @GetMapping(value = "/getAccountOperationsByClient/{client}/{accountNumber}")
    public Flux<OperationEntity> getAccountOperationsByClient(@PathVariable("client") String client,@PathVariable("accountNumber") String accountNumber){
        return operationService.getAccountOperationsByClient(client,accountNumber);
    }
}
