package com.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ming.li
 * @date 2023/9/21 16:42
 */
@Component
@ConfigurationProperties(prefix="invoice")
public class InvoiceProperties {
    private  Integer age;
    private String name;
    private String imageMv;

    @PostConstruct
    public void postConstruct(){
        System.out.println(this.toString());
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageMv() {
        return imageMv;
    }

    public void setImageMv(String imageMv) {
        this.imageMv = imageMv;
    }

    @Override
    public String toString() {
        return "InvoiceProperties{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", imageMv='" + imageMv + '\'' +
                '}';
    }
}
