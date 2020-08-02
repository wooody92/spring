package dev.spring.framework.springstudy.Environment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    public StudyRepository studyRepository() {
        return new TestStudyRepository();
    }
}
