package com.minis.beans.factory.config;

import lombok.Data;

@Data
public class ConstructorArgumentValue {
    private Object value;
    private String type;
    private String name;
    public ConstructorArgumentValue(Object value, String type) {
        this.value = value;
        this.type = type;
    }
    public ConstructorArgumentValue(Object value, String type, String name) {
        this.value = value;
        this.type = type;
        this.name = name;
    }
    //省略getter和setter
}