package com.rest.domain;

public class HelloWorldDomain {

	private String message;

	public HelloWorldDomain(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldDomain [message=" + message + "]";
	}

}