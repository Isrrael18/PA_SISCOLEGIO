package com.colegio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException {

	public GeneralException() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	public GeneralException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GeneralException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GeneralException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GeneralException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}