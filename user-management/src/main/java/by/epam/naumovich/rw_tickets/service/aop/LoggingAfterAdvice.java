package by.epam.naumovich.rw_tickets.service.aop;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Spring AOP AfterReturningAdvice implementation which logs the info about the method straight after it has returned.
 * 
 * @author Dzmitry_Naumovich
 * @version 1.0
 */
@Slf4j
public class LoggingAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		if (arg0 != null) {
			log.info("method {} was successfully run and returned {} object", arg1.getName(), arg0.getClass().getSimpleName());
		} else {
			log.info("method {} was successfully run", arg1.getName());
		}
		
	}

}
