package com.br.ent.restspringbootudemy.exception;

import java.util.Date;

public class ExceptionResponse {
	private static final long serialVersionUID = 1l;
	
	private Date timestamp;
	private String mensage;
	private String details;
	
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMensage() {
		return mensage;
	}

	public String getDetails() {
		return details;
	}

	public ExceptionResponse(Date timestamp, String mensage, String details) {
		super();
		this.timestamp = timestamp;
		this.mensage = mensage;
		this.details = details;
	}
	
}
