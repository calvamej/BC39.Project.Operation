package com.bootcamp.project.operation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Operation")
public class OperationEntity {
    @Id
    private String id;
    private String operationNumber;
    private String operationType;
    private String clientDocumentNumber;
    private String clientType;
    private String productCode;
    private String accountNumber;
    private String creditNumber;
    private String debitCardNumber;
    private String creditCardNumber;
    private double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifyDate;
}
