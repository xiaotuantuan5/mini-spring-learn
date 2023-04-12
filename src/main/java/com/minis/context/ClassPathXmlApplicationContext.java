package com.minis.context;

import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;

import com.minis.beans.SimpleBeanFactory;
import com.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

public class ClassPathXmlApplicationContext implements BeanFactory, ApplicationEventPublisher {
    SimpleBeanFactory beanFactory;

    //context负责整合容器的启动过程，读外部配置，解析Bean定义，创建BeanFactory
    public ClassPathXmlApplicationContext(String fileName,boolean isRefresh) {
        Resource resource = new ClassPathXmlResource(fileName);
        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(simpleBeanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = simpleBeanFactory;
        if (isRefresh){
            this.beanFactory.refresh();
        }
    }

    //context再对外提供一个getBean，底下就是调用的BeanFactory对应的方法
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }


    public boolean containsBean(String name) {
        return this.beanFactory.containsBean(name);
    }

    public void publishEvent(ApplicationEvent event) {
    }

    public boolean isSingleton(String name) {
        return false;
    }

    public boolean isPrototype(String name) {
        return false;
    }

    public Class<?> getType(String name) {
        return null;
    }
}
