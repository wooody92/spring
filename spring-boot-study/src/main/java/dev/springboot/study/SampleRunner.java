package dev.springboot.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    HenryProperties henryProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("================");
        System.out.println(henryProperties.getFullName());
        System.out.println(henryProperties.getAge());
        System.out.println("================");
    }
}
