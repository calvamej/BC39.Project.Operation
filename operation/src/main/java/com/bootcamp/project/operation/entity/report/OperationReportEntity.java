package com.bootcamp.project.operation.entity.report;

import com.bootcamp.project.operation.entity.OperationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationReportEntity {
    private String productCode;
    List<OperationEntity> operationList;
}
