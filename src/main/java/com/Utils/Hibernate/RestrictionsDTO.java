package com.Utils.Hibernate;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


public class RestrictionsDTO {
    private String propertyName;
    private Object propertyValue;
    private RestrictionsConditions conditions;

    public RestrictionsDTO(String propertyName, Object propertyValue, RestrictionsConditions conditons) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.conditions = conditons;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public RestrictionsDTO setPropertyName(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public Object getPropertyValue() {
        return this.propertyValue;
    }

    public RestrictionsDTO setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
        return this;
    }

    public RestrictionsConditions getConditions() {
        return this.conditions;
    }

    public RestrictionsDTO setConditions(RestrictionsConditions conditions) {
        this.conditions = conditions;
        return this;
    }
}

