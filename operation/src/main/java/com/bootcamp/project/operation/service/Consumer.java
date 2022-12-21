package com.bootcamp.project.operation.service;

import com.bootcamp.project.operation.entity.OperationDTO;
import com.bootcamp.project.operation.entity.OperationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Consumer {

	@Autowired
	private MongoTemplate mongoTemplate;

	@KafkaListener(topics="mytopicOperation", groupId="mygroup")
	public void consumeFromTopic(OperationDTO operationDTO) {
		addOperation(operationDTO);
		System.out.println("Operation created.");
	}
	public void addOperation(OperationDTO operationDTO) {
		OperationEntity entity = new OperationEntity();
		entity.setOperationType(operationDTO.getOperationType());
		entity.setClientDocumentNumber(operationDTO.getClientDocumentNumber());
		entity.setProductCode(operationDTO.getProductCode());
		entity.setAccountNumber(operationDTO.getAccountNumber());
		entity.setCreditNumber(operationDTO.getCreditNumber());
		entity.setDebitCardNumber(operationDTO.getDebitCardNumber());
		entity.setCreditCardNumber(operationDTO.getCreditCardNumber());
		entity.setAmount(operationDTO.getAmount());
		entity.setCreateDate(new Date());
		mongoTemplate.insert(entity);
	}
}
