package com.pluralsight.hellopeople.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = "com.pluralsight.hellopeople.controllers")
public class PeopleControllerAdvice {

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String badRequestExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException ex) {
//    	return "[PeopleControllerAdvice] - Error on "+request.getMethod()+" method: Data types are not compatible\nMessage: "+ ex.getMessage();
//    }
//    
//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String notFoundExceptionHandler(HttpServletRequest request, IndexOutOfBoundsException ex) {
//    	return "[PeopleControllerAdvice] - Error on "+request.getMethod()+" method: The id provided is out of bounds\nMessage: "+ ex.getMessage();
//    }
//    
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String genericExceptionHandler(HttpServletRequest request, Exception ex) {
//    	return "[PeopleControllerAdvice] - Error on "+request.getMethod()+" method: Unknown error\nMessage: "+ ex.getMessage();
//    }
}
