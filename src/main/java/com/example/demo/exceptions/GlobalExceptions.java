package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@ResponseBody
public class GlobalExceptions {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorMessage>resourceNotFound(ResourceNotFound e,WebRequest request){
        ErrorMessage errorMessage= new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(),e.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalException(Exception e, WebRequest request){
        ErrorMessage message= new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value()
        ,new Date(),e.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
