package by.epam.naumovich.rw_tickets.controller.handler;

import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final static String TEXT_FOR_RUNTIME_EX = "Error! Please, try again!";
    private final static String TEXT_FOR_SERVICE_EX = "Error! Please, try again!";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(value = { IllegalArgumentException.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, TEXT_FOR_RUNTIME_EX, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(value = { ServiceException.class})
    protected ResponseEntity<Object> handleException(ServiceException ex, WebRequest request) {
        return handleExceptionInternal(ex, TEXT_FOR_SERVICE_EX, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
