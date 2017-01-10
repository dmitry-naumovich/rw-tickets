package by.epam.naumovich.rw_tickets.service.aop;


import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

import by.epam.naumovich.rw_tickets.service.exception.InvalidInputServiceException;
import by.epam.naumovich.rw_tickets.service.exception.ServiceException;

public class ExceptionLoggingAdvice implements ThrowsAdvice {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	private static final String SERVICE_EXCEPTION_LOG_MESSAGE = "%s occured in %s service. Message for user is: \"%s\".";
	
	public void afterThrowing(Method method, Object[] args, Object target, ServiceException e) {
		logger.error(String.format(SERVICE_EXCEPTION_LOG_MESSAGE, e.getClass().getSimpleName(), target.getClass().getSimpleName(), e.getMessage()));
	}
	
	/*public void afterThrowing(Method method, Object[] args, Object target, InvalidInputServiceException e) {
		logger.error(String.format(SERVICE_EXCEPTION_LOG_MESSAGE, e.getClass().getSimpleName(), target.getClass().getSimpleName(), e.getMessage()));
	}*/
	
}
