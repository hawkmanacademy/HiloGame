package com.hilogame.exception;

public class NoMoreCardsException extends Exception {

	private static final long serialVersionUID = -7636733395602496303L;

	public NoMoreCardsException() {
		super("No more cards left");
	}
}
