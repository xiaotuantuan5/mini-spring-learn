package com.minis.test;

import lombok.Data;

@Data
public class BaseBaseService {
    private AServiceImpl as;
    // 省略 getter、setter方法
    public AServiceImpl getAs() {
        return as;
    }
    public void setAs(AServiceImpl as) {
        this.as = as;
    }
    public BaseBaseService() {
    }
    public void sayHello() {
        System.out.println("Base Base Service says hello");

    }

    public void init() {
        System.out.println("..........call init-mothod..........");
    }
}