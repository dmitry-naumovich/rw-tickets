package by.epam.naumovich.rw_tickets.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
* Spring AOP ThrowsAdvice implementation which logs the info about the exception and method which has thrown it right after it was thrown.
* 
* @author Dzmitry_Naumovich
* @version 1.0
*/
@Aspect
@Component
@Slf4j
public class LoggingThrowsAdvice {

    @AfterThrowing(pointcut = "within(by.epam.naumovich..*)", throwing = "e")
	public void afterThrowing(JoinPoint jp, Exception e) {
		log.debug("{} occurred in {}::{} method. The message is: \"{}\".",
		        e.getClass().getSimpleName(), jp.getSignature().getDeclaringType().getSimpleName(),
                jp.getSignature().getName(), e.getMessage());
	}
}
