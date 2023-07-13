package com.hrmanagementsystem.exception;

public class ErrorResponse {

	private String errorMessage;
	private String errorDetails;
	private String timestamp;

	public ErrorResponse(String errorMessage, String errorDetails, String timestamp) {
		super();
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
		this.timestamp = timestamp;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ErrorResponse [errorMessage=" + errorMessage + ", errorDetails=" + errorDetails + ", timestamp="
				+ timestamp + "]";
	}

}
