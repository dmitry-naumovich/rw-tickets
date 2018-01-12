package by.epam.naumovich.rw_tickets.service.aop;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.ThrowsAdvice;

/**
* Spring AOP ThrowsAdvice implementation which logs the info about the exception and method which has thrown it right after it was thrown.
* 
* @author Dzmitry_Naumovich
* @version 1.0
*/
@Slf4j
public class LoggingThrowsAdvice implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
		log.error("{} occurred in {} service. The message is: \"{}\".",
		        e.getClass().getSimpleName(), target.getClass().getSimpleName(), e.getMessage());
	}
}
