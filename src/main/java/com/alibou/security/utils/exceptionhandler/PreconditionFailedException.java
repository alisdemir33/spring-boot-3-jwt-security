package com.alibou.security.utils.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED)
public class PreconditionFailedException extends RuntimeException {

	private static final long serialVersionUID = -3883188533452495717L;

	public PreconditionFailedException() {
	}

	public PreconditionFailedException(String message) {
		super(message);
	}

	public PreconditionFailedException(Throwable cause) {
		super(cause);
	}

	public PreconditionFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PreconditionFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
