package com.bootcamp.project.operation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Operation")
public class OperationEntity {
    private String operationNumber;
    private String clientDocumentNumber;
    private String idProduct;
    private String operationType;
    private String accountNumber;
    private String creditNumber;
    private String debitCardNumber;
    private String creditCardNumber;
    private double amount;
    private Date createDate;
    private Date modifyDate;

    private String clientType;
    private String productCode;
}
