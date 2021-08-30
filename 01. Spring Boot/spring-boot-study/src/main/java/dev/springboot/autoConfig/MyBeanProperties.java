package dev.springboot.autoConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("MyBean")
public class MyBeanProperties {

    String name;

    int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
