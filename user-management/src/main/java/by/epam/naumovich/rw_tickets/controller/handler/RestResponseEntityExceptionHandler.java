package by.epam.naumovich.rw_tickets.controller.handler;

import by.epam.naumovich.rw_tickets.service.exception.ServiceException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    private final static String TEXT_FOR_EX = "Error! Please, try again!";

    @ExceptionHandler(value = {InvalidParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    protected ErrorResponse handleException(HttpServletRequest request, InvalidParameterException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, TEXT_FOR_EX);
    }

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    protected ErrorResponse handleException(HttpServletRequest request, ServiceException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, TEXT_FOR_EX);
    }

    @Data
    private static class ErrorResponse {
        public String timestamp;
        private String status;
        private int error;
        private String message;

        ErrorResponse(HttpStatus httpStatus, String message) {
            this.timestamp = LocalDateTime.now().toString();
            this.error = httpStatus.value();
            this.status = httpStatus.getReasonPhrase();
            this.message = message;
        }
    }

}
