package dev.spring.framework.springstudy.proxyAOP;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// Real Subject
@Service
public class SimpleEventService implements EventService{

    @Override
    public void createEvent() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Created an event");
    }

    @Override
    public void publishEvent() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Published an event");
    }

    @Override
    public void deleteEvent() {
        System.out.println("Deleted an event");
    }

    // 아래와 같이 proxy를 이용하지 않고, Real Subject 자체에서 처리하는 방식이 Crosscutting Concerns 이다.
    @Override
    public void crosscuttingConcernsEvent() {
        long begin = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test event");
        System.out.println(System.currentTimeMillis() - begin);
    }
}
