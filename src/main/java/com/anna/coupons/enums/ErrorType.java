package com.anna.coupons.enums;

public enum ErrorType {

	INVALID_EMAIL(601, "This Email Is Invalid"),
	INVALID_PARMETER(602, "This Parameter Is Invalid"),
	SYSTEM_ERROR(603, "System Error"),
	INVALID_PASSWROD(604, "This Password Is Invalid"),
	ALREADY_EXISTS(605, "Already Exists");

	private int errorNumber;
	private String errorMessage;

	private ErrorType(int errorNumber, String errorMessage) {
		this.errorNumber = errorNumber;
		this.errorMessage = errorMessage; 
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public static ErrorType fromString(final String s) {
		return ErrorType.valueOf(s);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

}
