package com.br.ent.restspringbootudemy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends AuthenticationException {
	private static final long serialVersionUID = 1L;

	public FileStorageException(String ex) {
		super(ex);
	}

	public FileStorageException(String ex, Throwable cause) {
		super(ex, cause);
	}
}