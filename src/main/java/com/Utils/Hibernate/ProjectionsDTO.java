package com.Utils.Hibernate;

/*
* @author Indradev kumar
* @empId 1238
* @date 7-mar-2023
* */
public class ProjectionsDTO {
    private String propertyName;

    public ProjectionsDTO() {
    }

    public ProjectionsDTO setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public static void main(String[] args){
        Integer roi= 1234;
        System.out.println("₹ "+roi);
        System.out.println("% "+roi);
    }
}

