package com.hilogame.constants;

import com.hilogame.exception.InvalidPlayerChoiceException;

public enum PlayerChoice {
	Hi, Lo;

	public static PlayerChoice parse(String input) throws InvalidPlayerChoiceException {
		PlayerChoice choice;
		switch (input.toLowerCase()) {
		case "h":
		case "hi":
		case "high":
			choice = Hi;
			break;
		case "l":
		case "lo":
		case "low":
			choice = Lo;
			break;
		default:
			throw new InvalidPlayerChoiceException(input);
		}
		return choice;
	}
}
