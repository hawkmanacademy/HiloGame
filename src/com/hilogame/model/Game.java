package com.hilogame.model;

import java.util.HashMap;
import java.util.Map;

import com.hilogame.model.card.Card;

public class Game {
	private Map<String, String> messages;

	private Card firstCard;

	private Card secondCard;

	public Game(Card firstCard, Card secondCard) {
		messages = new HashMap<String, String>();
		this.firstCard = firstCard;
		this.secondCard = secondCard;
	}

	public String getMessage(String key) {
		return messages.get(key);
	}

	public void sendMessage(String key, String message) {
		messages.put(key, message);
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
	
	public Card getFirstCard() {
		return firstCard;
	}
	
	public Card getSecondCard() {
		return secondCard;
	}
}
