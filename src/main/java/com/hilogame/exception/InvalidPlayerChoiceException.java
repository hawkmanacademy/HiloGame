package com.hilogame.exception;

public class InvalidPlayerChoiceException extends Exception {
	public InvalidPlayerChoiceException(String choice) {
		super("Player choice " + choice + " is invalid");
	}
}
