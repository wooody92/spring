package dev.springboot.study;

import dev.springboot.study.properties.HenryProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    @Autowired
    String hello;

    @Autowired
    HenryProperties henryProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("================");
        System.out.println(hello);
        System.out.println(henryProperties.getName());
        System.out.println("================");
    }
}
