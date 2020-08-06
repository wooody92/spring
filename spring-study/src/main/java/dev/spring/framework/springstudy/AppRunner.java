package dev.spring.framework.springstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // WebApplicationContext
        System.out.println(resourceLoader.getClass());

        // ClassPathResource
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        System.out.println(resource.getClass());
        System.out.println(resource.exists());
        System.out.println(resource.getDescription());

        // ServletContextResource
        Resource resource2 = resourceLoader.getResource("test.txt");
        System.out.println(resource2.getClass());
        System.out.println(resource2.exists());
        System.out.println(resource2.getDescription());
    }
}
