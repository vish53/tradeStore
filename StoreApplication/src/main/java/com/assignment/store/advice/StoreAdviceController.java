package com.assignment.store.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.assignment.store.exception.InvalidTradeVersionException;
import com.assignment.store.exception.MaturityDateException;

@ControllerAdvice
public class StoreAdviceController {
	
	@ExceptionHandler(InvalidTradeVersionException.class)
	public ResponseEntity<Object> handleInvalidTradeVersionException(InvalidTradeVersionException invalidTradeVersionException)
	{
		return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(invalidTradeVersionException.getMessage());
		
	}
	
	@ExceptionHandler(MaturityDateException.class)
	public ResponseEntity<Object> handleMaturityDateException(MaturityDateException maturityDateException)
	{
		return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(maturityDateException.getMessage());
		
	}
}
