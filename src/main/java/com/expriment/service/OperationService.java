package com.expriment.service;

import com.expriment.entity.vo.OperationValue;
import org.springframework.http.ResponseEntity;

public interface OperationService {
    ResponseEntity<?> addition(OperationValue operationValue);
}
