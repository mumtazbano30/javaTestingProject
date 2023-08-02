package com.expriment.Controller;

import com.expriment.entity.vo.OperationValue;
import com.expriment.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value ="/oper/")
public class OperationController {

    @Autowired
    OperationService operationService;

    @PostMapping(value ="add",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> add(@RequestBody OperationValue operationValue){
        return operationService.addition(operationValue);
    }
    @PostMapping(value ="sub",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> sub(@RequestBody OperationValue operationValue){
        return operationService.addition(operationValue);
    }
}
