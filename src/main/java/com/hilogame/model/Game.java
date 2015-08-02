package com.hilogame.model;

import com.hilogame.constants.PlayerChoice;

public class Game {
	private Card firstCard;

	private Card secondCard;

	public Game(Card firstCard, Card secondCard) {
		this.firstCard = firstCard;
		this.secondCard = secondCard;
	}
	
	public Card getFirstCard() {
		return firstCard;
	}
	
	public Card getSecondCard() {
		return secondCard;
	}
	
	public PlayerChoice getResult() {
		int score = firstCard.compareTo(secondCard);

		PlayerChoice gameResult = null;
		if (score > 0) {
			gameResult = PlayerChoice.Hi;
		} else {
			gameResult = PlayerChoice.Lo;
		}
		return gameResult;
	}
}
