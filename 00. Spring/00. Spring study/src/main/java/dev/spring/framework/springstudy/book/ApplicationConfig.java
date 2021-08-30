package dev.spring.framework.springstudy.book;

import dev.spring.framework.springstudy.SpringStudyApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = SpringStudyApplication.class)
public class ApplicationConfig {

}
