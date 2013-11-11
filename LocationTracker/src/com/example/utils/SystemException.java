package com.example.utils;
/**
 * @author Indrajit Kumar (Android)
 */
public class SystemException extends Exception {
	private static final long serialVersionUID = 1L;
	public String errorCode;
	public String message;

	public SystemException(String errorCode) {
		this.errorCode = errorCode;
	}

	public SystemException(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
