package com.hilogame.exception;

public class InvalidPlayerChoiceException extends Exception {
	private static final long serialVersionUID = 1587989767730179363L;

	public InvalidPlayerChoiceException(String choice) {
		super("Player choice " + choice + " is invalid");
	}
}
