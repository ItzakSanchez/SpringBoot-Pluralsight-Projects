package eventservice.dev.edgaritzak;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ErrorResponse notFound(NoSuchElementException nseex) {
		return ErrorResponse.create(nseex, HttpStatus.NOT_FOUND, nseex.getMessage());
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ErrorResponse missingParameter(MissingServletRequestParameterException msrpex) {
		return ErrorResponse.create(msrpex,HttpStatus.BAD_REQUEST, msrpex.getMessage());
	}
}
