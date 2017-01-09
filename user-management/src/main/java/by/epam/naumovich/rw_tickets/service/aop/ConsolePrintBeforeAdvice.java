package by.epam.naumovich.rw_tickets.service.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class ConsolePrintBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("method " + arg0.getName() + " was invoked with next first arg: " + arg1[0] + " of type " + arg1[0].getClass().getSimpleName());
	}

}
