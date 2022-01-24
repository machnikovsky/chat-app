package pl.chatty.javabackend.exception.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.chatty.javabackend.exception.exceptions.UserEntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ChattyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserEntityNotFoundException.class)
    protected ResponseEntity<String> handleUserEntityNotFound(UserEntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
    }

}
