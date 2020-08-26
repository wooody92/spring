package dev.springboot.Evnet;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 서블릿 웹 서버가 생성이 되면 이벤트 리스너가 호출된다.
 * Override 한 메서드가 callback 된다
 */
@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(
        ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        ServletWebServerApplicationContext applicationContext = servletWebServerInitializedEvent
            .getApplicationContext();
//        System.out.println(">>> port : " + applicationContext.getWebServer().getPort());
    }
}
