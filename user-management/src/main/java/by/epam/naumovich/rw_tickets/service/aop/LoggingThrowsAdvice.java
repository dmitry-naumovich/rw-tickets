package by.epam.naumovich.rw_tickets.service.aop;


import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

/**
* Spring AOP ThrowsAdvice implementation which logs the info about the exception and method which has thrown it right after it was thrown.
* 
* @author Dzmitry_Naumovich
* @version 1.0
*/
public class LoggingThrowsAdvice implements ThrowsAdvice {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	private static final String SERVICE_EXCEPTION_LOG_MESSAGE = "%s occured in %s service. The message is: \"%s\".";
	
	public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
		logger.error(String.format(SERVICE_EXCEPTION_LOG_MESSAGE, e.getClass().getSimpleName(), target.getClass().getSimpleName(), e.getMessage()));
	}
}
