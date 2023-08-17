package com.clinicBackEnd.exceptions;

public class UserAlreadyExistException extends Exception {

	private static final long serialVersionUID = -9123054104353441772L;

	public UserAlreadyExistException() {
        super();
    }

	public UserAlreadyExistException(String message) {
        super(message);
    }
	
	public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
