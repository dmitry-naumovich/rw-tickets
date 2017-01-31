package by.epam.naumovich.rw_tickets.service.aop;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Spring AOP AfterReturningAdvice implementation which logs the info about the method straight after it has returned.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
public class LoggingAfterAdvice implements AfterReturningAdvice {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		if (arg0 != null) {
			logger.info("method " + arg1.getName() + " was sucessfully run and returned " + arg0.getClass().getSimpleName() + " object");
		} else {
			logger.info("method " + arg1.getName() + " was sucessfully run");
		}
		
	}

}