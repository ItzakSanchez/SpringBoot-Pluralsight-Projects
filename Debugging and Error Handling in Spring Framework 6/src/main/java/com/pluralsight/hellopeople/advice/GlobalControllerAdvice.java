package com.pluralsight.hellopeople.advice;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = "com.pluralsight.hellopeople.controllers")
public class GlobalControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail badRequestExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException ex) {
    	ProblemDetail probDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    	probDetail.setTitle("Exception ocurred");
    	probDetail.setType(URI.create("https://www.google.com"));
    	probDetail.setProperty("Method", request.getMethod());
    	return probDetail;
    }
    
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String badRequestExceptionHandler(HttpServletRequest request, HttpMessageNotReadableException ex) {
//    	return "[GlobalControllerAdvice] - Error on "+request.getMethod()+" method: Data types are not compatible\nMessage: "+ ex.getMessage();
//    }
    
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(HttpServletRequest request, IndexOutOfBoundsException ex) {
    	return "[GlobalControllerAdvice] - Error on "+request.getMethod()+" method: The id provided is out of bounds\nMessage: "+ ex.getMessage();
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String genericExceptionHandler(HttpServletRequest request, Exception ex) {
    	return "[GlobalControllerAdvice] - Error on "+request.getMethod()+" method: Unknown error\nMessage: "+ ex.getMessage();
    }
}
