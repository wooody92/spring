package dev.springboot.autoConfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MyBeanProperties.class)
public class MyBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MyBean myBean(MyBeanProperties properties) {
        MyBean mybean = new MyBean();
        mybean.setName(properties.getName());
        mybean.setAge(properties.getAge());
        return mybean;
    }
}
