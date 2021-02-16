package com.gama.exceptions;

public class TransactionAlreadyExistsException extends Exception {
	public TransactionAlreadyExistsException()
	{
		
	}
	public TransactionAlreadyExistsException(String message)
	{
		super(message);
	}
}
