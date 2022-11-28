package com.bootcamp.project.operation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Operation")
public class OperationEntity {
    @Id
    private ObjectId id;
    private String operationNumber;
    private String client;
    private String operationType;
    private String accountNumber;
    private String creditNumber;
    private double amount;
    private Date insert_date;
}
