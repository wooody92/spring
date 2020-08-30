package dev.springboot.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("================");
        logger.debug("================");
    }
}
