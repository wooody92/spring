package dev.spring.framework.springstudy;

import dev.spring.framework.springstudy.Event.MyEvent;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        System.out.println(resource.exists());
        System.out.println(resource.getDescription());

        // 해당 경로의 파일 내용 읽어오기. Java11 부터 정상 동작한다.
//        System.out.println(Files.readString(Path.of(resource.getURI())));
    }
}
