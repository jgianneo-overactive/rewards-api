package com.awards.common.exception;

import com.awards.common.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    private static final String ERROR = "ERROR";

    @ExceptionHandler(value = {ProcessException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MessageResponse<Object>> hadleProcessException(Exception e) {
        return new ResponseEntity<>(new MessageResponse<>(ERROR, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ResourceNotFound.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MessageResponse<Object>> hadleResourceNotFoundException(Exception e) {
        return new ResponseEntity<>(new MessageResponse<>(ERROR, e.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MessageResponse<Object>> hadleArgumentNotValidException(Exception e) {
        return new ResponseEntity<>(new MessageResponse<>(ERROR, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
}