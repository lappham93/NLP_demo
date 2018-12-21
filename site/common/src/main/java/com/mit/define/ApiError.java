package com.mit.define;

public enum ApiError {
	SUCCESS(0), SERVER_ERROR(-1, "Unknown error.");
	
	private int value;
	private String message;
	
	private ApiError(int value) {
		this.value = value;
	}

	private ApiError(int value, String message) {
		this.value = value;
		this.message = message;
	}
	
	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}