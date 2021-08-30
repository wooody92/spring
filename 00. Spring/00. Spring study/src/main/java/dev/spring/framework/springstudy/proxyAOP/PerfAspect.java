package dev.spring.framework.springstudy.proxyAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfAspect {

    // Advice
//    @Around("@annotation(PerLogging)")
    @Around("bean(simpleEventService)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();

        // pjp : advice가 적용되는 대상이며, 여기서는 SimpleEventService에 구현된 메서드 자체를 말한다.
        Object retVal = pjp.proceed();

        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }

    @Before("bean(simpleEventService)")
    public void hello() {
        System.out.println("hello");
    }
}
