package com.cognizant.exception;

public class DataAlreayExistException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public DataAlreayExistException(String message){
		super(message);
	}

}
