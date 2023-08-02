package com.Services.Impl;

import com.Services.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationImpl implements Operation {

    @Override
    public int addition(int n1){
        int result,n=10;
        result= n+n1;
        return result;
    }
    @Override
    public int substraction(int num){
        int result, num1=20;
        result= num-num1;

        return result;
    }
    @Override
    public float multiplication(float n){
        float result, n1=2;
        result = n*n1;

        return result;
    }
    @Override
    public float division(float number1){
        float result, number=10;
        if(number1!=0) {
            result = (number/number1);
        }else{
            result=0;
        }
        return result;
    }
    @Override
    public double percentage(float score){
        float result, totalScore=100;
         result = (float) ((score*30)/totalScore);
        return result;
    }

}
