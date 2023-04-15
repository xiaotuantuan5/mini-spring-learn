package com.minis.beans.factory.support;

import com.minis.beans.factory.BeanFactory;
import com.minis.beans.BeansException;

public interface BeanFactoryPostProcessor {
	void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}