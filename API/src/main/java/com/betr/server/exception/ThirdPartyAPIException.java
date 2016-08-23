package com.betr.server.exception;

public class ThirdPartyAPIException extends Exception {
	private static final long serialVersionUID = 1L;

	public ThirdPartyAPIException(Throwable t) {
		super(t.getMessage(), t);
	}
	
	public ThirdPartyAPIException(String message, Throwable t) {
		super(message, t);
	}
}
