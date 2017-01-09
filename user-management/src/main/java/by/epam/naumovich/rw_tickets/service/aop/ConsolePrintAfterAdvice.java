package by.epam.naumovich.rw_tickets.service.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class ConsolePrintAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		System.out.println("method " + arg1.getName() + " was sucessfully run and returned " + arg0.getClass().getSimpleName() + " object");
	}

}
