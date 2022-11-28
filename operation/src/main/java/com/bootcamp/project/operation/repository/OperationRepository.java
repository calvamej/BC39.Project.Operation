package com.bootcamp.project.operation.repository;

import com.bootcamp.project.operation.entity.OperationEntity;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OperationRepository extends ReactiveCrudRepository<OperationEntity, ObjectId> {
}
