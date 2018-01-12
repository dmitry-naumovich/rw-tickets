package by.epam.naumovich.rw_tickets.service.aop;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;

/**
* Spring AOP MethodBeforeAdvice implementation which logs the info about the method just before it was called.
* 
* @author Dzmitry_Naumovich
* @version 1.0
*/
@Slf4j
public class LoggingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		log.info("method {} was invoked with next first arg: {} of type {}",
                arg0.getName(), arg1[0], arg1[0].getClass().getSimpleName());
	}

}
