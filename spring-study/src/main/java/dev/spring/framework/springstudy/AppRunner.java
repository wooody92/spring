package dev.spring.framework.springstudy;

import dev.spring.framework.springstudy.validate.Event;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    Validator validator;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(validator.getClass());

        // 테스트를 위한 유효하지 않는 값 입력
        Event event = new Event();
        event.setLimit(-1);
        event.setEmail("abcde");
        Errors errors = new BeanPropertyBindingResult(event, "event");

        validator.validate(event, errors);
        System.out.println(errors.hasErrors());

        errors.getAllErrors().forEach(e -> {
            System.out.println("===== error code =====");
            Arrays.stream(e.getCodes()).forEach(System.out::println);
            System.out.println(e.getDefaultMessage());
        });
    }
}
