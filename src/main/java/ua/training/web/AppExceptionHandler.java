package ua.training.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.training.exception.DuplicateCommentException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DuplicateCommentException.class)
    public String duplicateCommentHandler() {
        return "error/duplicate";
    }
}
