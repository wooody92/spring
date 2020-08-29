package dev.springboot.study;

import dev.springboot.study.properties.HenryProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    @Autowired
    String hello;

    @Autowired
    HenryProperties henryProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("================");
        logger.debug(hello);
        logger.debug(henryProperties.getName());
        logger.debug("================");
    }
}
