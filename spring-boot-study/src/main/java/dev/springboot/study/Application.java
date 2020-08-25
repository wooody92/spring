package dev.springboot.study;

import java.io.PrintStream;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

    /**
     * SpringApplcation 실행 방법
     */
    public static void main(String[] args) {
//        // 1 : static method
//        SpringApplication.run(Application.class, args);
//
//        // 2 : instance
//        SpringApplication app = new SpringApplication(Application.class);
//        app.run(args);

        // 3 : builder
        new SpringApplicationBuilder()
            .sources(Application.class)
            .run(args);
    }
}
