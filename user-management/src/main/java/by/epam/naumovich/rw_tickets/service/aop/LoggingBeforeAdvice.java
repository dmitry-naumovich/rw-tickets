package by.epam.naumovich.rw_tickets.service.aop;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

/**
* Spring AOP MethodBeforeAdvice implementation which logs the info about the method just before it was called.
* 
* @author Dzmitry_Naumovich
* @version 1.0
*/
public class LoggingBeforeAdvice implements MethodBeforeAdvice {

	private static final Logger logger = LogManager.getLogger(Logger.class.getName());
	
	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		logger.info("method " + arg0.getName() + " was invoked with next first arg: " + arg1[0] + " of type " + arg1[0].getClass().getSimpleName());
	}

}
