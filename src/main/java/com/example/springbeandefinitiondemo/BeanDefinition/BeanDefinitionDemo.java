package com.example.springbeandefinitiondemo.BeanDefinition;

import com.example.springbeandefinitiondemo.BeanClass.MyBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;

public class BeanDefinitionDemo {
    public static void main(String[] args) throws IOException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registerBeanDefinition("myBean" , createBeanDefinition());

        // 获取MyBean
        MyBean myChildBean = defaultListableBeanFactory.getBean("myBean", MyBean.class);

        // 打印Bean对象
        System.out.println("MyBean = " + myChildBean);

        // 销毁myBean
        defaultListableBeanFactory.destroySingleton("myBean");
    }

    public static ScannedGenericBeanDefinition createBeanDefinition() throws IOException {
        SimpleMetadataReaderFactory simpleMetadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = simpleMetadataReaderFactory.getMetadataReader(MyBean.class.getName());

        ScannedGenericBeanDefinition scannedGenericBeanDefinition = new ScannedGenericBeanDefinition(metadataReader);
        scannedGenericBeanDefinition.setScope("singleton");
        scannedGenericBeanDefinition.setLazyInit(true);
        scannedGenericBeanDefinition.setPrimary(true);
        scannedGenericBeanDefinition.setAbstract(false);
        scannedGenericBeanDefinition.setInitMethodName("init");
        scannedGenericBeanDefinition.setDestroyMethodName("destroy");
        scannedGenericBeanDefinition.setAutowireCandidate(true);
        scannedGenericBeanDefinition.setRole(BeanDefinition.ROLE_APPLICATION);
        scannedGenericBeanDefinition.setDescription("This is a custom bean definition");
        scannedGenericBeanDefinition.setResourceDescription("com.example.springbeandefinitiondemo.BeanClass.MyBean");
        scannedGenericBeanDefinition.getPropertyValues().add("name", "lex");
        scannedGenericBeanDefinition.getPropertyValues().add("age", "18");
        return scannedGenericBeanDefinition;
    }
}
