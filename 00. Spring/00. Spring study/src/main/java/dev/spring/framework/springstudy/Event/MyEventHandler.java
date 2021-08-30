package dev.spring.framework.springstudy.Event;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler {

    @EventListener
    public void handle(MyEvent event) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("Event #1. Data is " +event.getData());
    }
}
