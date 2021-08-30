package dev.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("henry")
public class HenryProperties {

    private String name;

    //  `Conversion Service` 통해서 `Type Conversion`이 일어난다.
    private int age;

    private String fullName;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFullName() {
        return fullName;
    }
}
