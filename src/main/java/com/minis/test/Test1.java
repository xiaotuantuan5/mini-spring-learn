package com.minis.test;


import com.minis.context.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml",true);
        ctx.getBeanFactory().getBeanNamesForType(AService.class);
        AService aService = (AService) ctx.getBean("aservice");
        System.out.println(aService);
        aService.sayHello();
    }
} 