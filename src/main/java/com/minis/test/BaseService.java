package com.minis.test;

import lombok.Data;

@Data
public class BaseService {
    private BaseBaseService bbs;
    // 省略 getter、setter方法
    public BaseBaseService getBbs() {
        return bbs;
    }
    public void setBbs(BaseBaseService bbs) {
        this.bbs = bbs;
    }
    public BaseService() {
    }
    public void sayHello() {
        System.out.print("Base Service says hello");
        bbs.sayHello();
    }
    public String getHello() {
        return "Base Service get Hello.";
    }
}