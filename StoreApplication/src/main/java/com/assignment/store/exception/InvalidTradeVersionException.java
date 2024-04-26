package com.assignment.store.exception;

public class InvalidTradeVersionException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidTradeVersionException(String message)
	{
		super(message);
	}

}
