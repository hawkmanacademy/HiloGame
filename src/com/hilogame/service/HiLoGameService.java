package com.hilogame.service;

import java.util.ArrayList;
import java.util.List;

import com.hilogame.exception.NoMoreCardsException;
import com.hilogame.model.Game;
import com.hilogame.model.GameResult;
import com.hilogame.model.Outcome;
import com.hilogame.model.PlayerChoice;
import com.hilogame.model.card.Card;
import com.hilogame.model.card.Dealer;

public class HiLoGameService implements GameService {
	private Dealer dealer;

	public HiLoGameService() {
		dealer = new Dealer();
	}

	@Override
	public Game startGame() {
		
		List<Card> cards = new ArrayList<Card>();
		try {
			// 0 deal two cards
			cards.addAll(dealer.deal(2));
			Card firstCard = cards.get(0);
			Card secondCard = cards.get(1);

			Game game = new Game(firstCard, secondCard);

			// 1. Display first card
			game.sendMessage("displayfirstcard",
					"First card: " + firstCard.toString());

			// 2. prompt player to guess whether second card is high or low
			// (H/h/High/high =High, L/l/Low/low = Low)
			game.sendMessage("promptforhilo", "Is the next card is Hi or Lo?");
			return game;
		} catch (NoMoreCardsException e) {
			throw new IllegalStateException(
					"Deck is broken so the Game aborted");
		}

	}

	@Override
	public GameResult endGame(Game game) {

		String choice = game.getMessage("playerchoice");
		PlayerChoice playerChoice = PlayerChoice.valueOf(choice);

		PlayerChoice gameResult = game.getResult();

		Outcome gameOutcome = null;
		if (gameResult == playerChoice) {
			gameOutcome = Outcome.Won;
		} else {
			gameOutcome = Outcome.Lost;
		}

		switch (gameOutcome) {
		case Won:
			game.sendMessage("displayoutcome", "Second Card: "
					+ game.getSecondCard().toString() + " - You Win!");
			break;
		case Lost:
			game.sendMessage("displayoutcome", "Second Card: "
					+ game.getSecondCard().toString()
					+ "- You looser! Better luck next time!");
			break;
		}

		return new GameResult(game, playerChoice, gameOutcome);
	}
}
