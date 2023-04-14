package com.minis.beans.factory.support;

import com.minis.beans.BeanFactory;
import com.minis.beans.BeansException;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.ConstructorArgumentValue;
import com.minis.beans.factory.config.ConstructorArgumentValues;
import com.minis.context.PropertyValue;
import com.minis.context.PropertyValues;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();
    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);

    //getBean，容器的核心方法
    public Object getBean(String beanName) throws BeansException {
        Object singleton = this.getSingleton(beanName);
        //先尝试直接拿bean实例
        if (singleton == null) {
            //如果没有实例，则尝试从毛胚实例中获取
            singleton = this.earlySingletonObjects.get(beanName);
            //如果连毛胚都没有，则创建bean实例并注册
            if (singleton == null) {
                System.out.println("get bean null -------------- " + beanName);
                //获取bean的定义
                BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
                singleton = createBean(beanDefinition);
                //新注册这个bean实例
                this.registerSingleton(beanName, singleton);
            }
        }
        return singleton;
    }

    private Object createBean(BeanDefinition beanDefinition) {
        //创建毛胚bean实例
        Object obj = doCreateBean(beanDefinition);
        //存放到毛胚实例缓存中
        this.earlySingletonObjects.put(beanDefinition.getId(), obj);
        Class<?> clz = null;
        Constructor<?> con = null;
        try {
            clz = Class.forName(beanDefinition.getClassName());
        } catch (Exception e) {
        }
        // 处理属性
        handleProperties(beanDefinition, clz, obj);
        return obj;
    }

    private Object doCreateBean(BeanDefinition bd) {
        Class<?> clz = null;
        Object obj = null;
        Constructor<?> con = null;
        try {
            clz = Class.forName(bd.getClassName());

            //handle constructor
            ConstructorArgumentValues ConstructorArgumentValues = bd.getConstructorArgumentValues();
            if (ConstructorArgumentValues != null) {
                if (!ConstructorArgumentValues.isEmpty()) {
                    Class<?>[] paramTypes = new Class<?>[ConstructorArgumentValues.getArgumentCount()];
                    Object[] paramValues = new Object[ConstructorArgumentValues.getArgumentCount()];
                    for (int i = 0; i < ConstructorArgumentValues.getArgumentCount(); i++) {
                        ConstructorArgumentValue ConstructorArgumentValue = ConstructorArgumentValues.getIndexedConstructorArgumentValue(i);
                        if ("String".equals(ConstructorArgumentValue.getType()) || "java.lang.String".equals(ConstructorArgumentValue.getType())) {
                            paramTypes[i] = String.class;
                            paramValues[i] = ConstructorArgumentValue.getValue();
                        } else if ("Integer".equals(ConstructorArgumentValue.getType()) || "java.lang.Integer".equals(ConstructorArgumentValue.getType())) {
                            paramTypes[i] = Integer.class;
                            paramValues[i] = Integer.valueOf((String) ConstructorArgumentValue.getValue());
                        } else if ("int".equals(ConstructorArgumentValue.getType())) {
                            paramTypes[i] = int.class;
                            paramValues[i] = Integer.valueOf((String) ConstructorArgumentValue.getValue()).intValue();
                        } else {
                            paramTypes[i] = String.class;
                            paramValues[i] = ConstructorArgumentValue.getValue();
                        }
                    }
                    try {
                        con = clz.getConstructor(paramTypes);
                        obj = con.newInstance(paramValues);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    obj = clz.newInstance();
                }
            } else {
                obj = clz.newInstance();
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(bd.getId() + " bean created. " + bd.getClassName() + " : " + obj.toString());

        return obj;

    }


    private void handleProperties(BeanDefinition bd, Class<?> clz, Object obj) {
        //handle properties
        System.out.println("handle properties for bean : " + bd.getId());
        PropertyValues propertyValues = bd.getPropertyValues();
        if (propertyValues != null) {
            if (!propertyValues.isEmpty()) {
                for (int i = 0; i < propertyValues.size(); i++) {
                    PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                    String pName = propertyValue.getName();
                    String pType = propertyValue.getType();
                    Object pValue = propertyValue.getValue();
                    boolean isRef = propertyValue.getIsRef();
                    Class<?>[] paramTypes = new Class<?>[1];
                    Object[] paramValues = new Object[1];
                    if (!isRef) {
                        if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                            paramTypes[0] = String.class;
                            paramValues[0] = pValue;
                        } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                            paramTypes[0] = Integer.class;
                            paramValues[0] = Integer.valueOf((String) pValue);
                        } else if ("int".equals(pType)) {
                            paramTypes[0] = int.class;
                            paramValues[0] = Integer.valueOf((String) pValue).intValue();
                        } else {
                            paramTypes[0] = String.class;
                            paramValues[0] = pValue;
                        }


                    } else { //is ref, create the dependent beans
                        try {
                            paramTypes[0] = Class.forName(pType);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            paramValues[0] = getBean((String) pValue);

                        } catch (BeansException e) {
                            e.printStackTrace();
                        }
                    }

                    String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);

                    Method method = null;
                    try {
                        method = clz.getMethod(methodName, paramTypes);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                    try {
                        method.invoke(obj, paramValues);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
    }


    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try {
                getBean(beanName);
            }catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
    }

    public boolean containsBean(String name) {
        return containsSingleton(name);
    }

    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
        this.beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e) {
            }
        }
    }

    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }

    public boolean isSingleton(String name) {
        return this.beanDefinitionMap.get(name).isSingleton();
    }

    public boolean isPrototype(String name) {
        return this.beanDefinitionMap.get(name).isPrototype();
    }

    public Class<?> getType(String name) {
        return this.beanDefinitionMap.get(name).getClass();
    }
}
