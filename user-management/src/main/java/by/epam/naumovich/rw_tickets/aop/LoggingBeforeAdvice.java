package by.epam.naumovich.rw_tickets.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
* Spring AOP MethodBeforeAdvice implementation which logs the info about the method just before it was called.
* 
* @author Dzmitry_Naumovich
* @version 1.0
*/
@Aspect
@Component
@Slf4j
public class LoggingBeforeAdvice {

    @Before(value = "within(by.epam.naumovich..*)")
    public void before(JoinPoint jp) throws Throwable {
        List<String> paramNames = getParameterNames(MethodSignature.class.cast(jp.getSignature()).getMethod());
        Object[] paramValues = jp.getArgs();

        StringBuilder sb = new StringBuilder(String.format("%s::%s invoked", jp.getSignature().getDeclaringType().getSimpleName(), jp.getSignature().getName()));

        if (paramValues.length != 0 && paramValues.length == paramNames.size()) {
            sb.append(", args: [");
            for (int i = 0; i < paramValues.length; i++) {
                sb.append(paramNames.get(i)).append('=').append(paramValues[i]).append(',');
            }
            sb.deleteCharAt(sb.length() - 1).append("]");
        }
        log.debug(sb.toString());

    }

    private List<String> getParameterNames(Method method) {
        return Arrays.stream(method.getParameters())
                .filter(Parameter::isNamePresent)
                .map(Parameter::getName)
                .collect(Collectors.toList());
    }

}
