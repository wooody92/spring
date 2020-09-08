package dev.springboot.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

//    @CrossOrigin(origins = "http://localhost:18080")
    @GetMapping("/cors")
    public String corsTest() {
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
//        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }
}
