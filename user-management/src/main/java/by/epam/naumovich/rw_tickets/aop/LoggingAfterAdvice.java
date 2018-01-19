package by.epam.naumovich.rw_tickets.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Spring AOP AfterReturningAdvice implementation which logs the info about the method straight after it has returned.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class LoggingAfterAdvice {

    @AfterReturning(pointcut = "within(by.epam.naumovich..*)", returning = "retVal")
    public void after(JoinPoint jp, Object retVal) throws Throwable {
        if (retVal != null) {
            log.debug("{}::{} successfully finished and returned {} object",
                    jp.getSignature().getDeclaringType().getSimpleName(), jp.getSignature().getName(), retVal);
        } else {
            log.debug("{}::{} successfully finished",  jp.getSignature().getDeclaringType().getSimpleName(), jp.getSignature().getName());
        }
    }

}
