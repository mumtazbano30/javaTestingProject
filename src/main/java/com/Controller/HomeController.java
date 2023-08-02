package com.Controller;

import com.Services.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    Operation operation;

    @GetMapping(value="/hello")// /Offer/saveOfferModule
    public String saveCDIOfferModule(){

        return "Hello";
    }
    @GetMapping("/{userId}")
    public ResponseEntity<String> getUser(@PathVariable("userId") String userId){
        return new ResponseEntity("Hello "+userId, HttpStatus.OK);
    }

    @GetMapping("/add/{n1}")
    public ResponseEntity<Integer> getUser(@PathVariable("n1") int n1){
        int result=0;
        try {
           result= operation.addition(n1);
           return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }
    @GetMapping("/sub/{num}")
    public ResponseEntity<Integer> getSub(@PathVariable("num") int num){
        int result=0;
        try {
            result= operation.substraction(num);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }
    @GetMapping("/mult/{n}")
    public ResponseEntity<Float> getmult(@PathVariable("n") float n){
        float result=0;
        try {
            result= operation.multiplication(n);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }
    @GetMapping("/div/{number1}")
    public ResponseEntity<Float> getdiv(@PathVariable("number1") float number1){
        float result=0;
        try {
            result= operation.division(number1);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }
    @GetMapping("/per/{score}")
    public ResponseEntity<Float> getper(@PathVariable("score") float score){
        float result=0;
        try {
            result= (float) operation.percentage(score);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(result, HttpStatus.OK);

    }


}
