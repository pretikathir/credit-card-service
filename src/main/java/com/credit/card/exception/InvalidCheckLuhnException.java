package com.credit.card.exception;

public class InvalidCheckLuhnException extends RuntimeException{
	public InvalidCheckLuhnException(String message){
		super(message);
	}

}
