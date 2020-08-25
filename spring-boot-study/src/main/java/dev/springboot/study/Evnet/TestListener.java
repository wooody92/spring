package dev.springboot.study.Evnet;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
//@Order(1)
public class TestListener implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("debug : " + args.containsOption("debug"));
        System.out.println("bar : " + args.containsOption("bar"));
    }
}
