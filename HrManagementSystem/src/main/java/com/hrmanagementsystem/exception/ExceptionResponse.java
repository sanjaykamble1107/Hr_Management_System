package com.hrmanagementsystem.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExceptionResponse {
	private String errorMessage;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy  hh:mm:ss")
	LocalDateTime timestamp;
	private int errorCode;

	// Getters and setters
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public ExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	// constructor
	public ExceptionResponse(String errorMessage, LocalDateTime timestamp, int errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
		this.errorCode = errorCode;
	}

}
