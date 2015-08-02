package com.hilogame.model;

import com.hilogame.constants.Outcome;
import com.hilogame.constants.PlayerChoice;

public class GameResult {
	private final Game game;
	
	private final PlayerChoice playerChoice;
	
	private final Outcome gameOutcome;
	
	public GameResult(Game game, PlayerChoice playerChoice, Outcome gameOutcome) {
		this.game = game;
		this.playerChoice = playerChoice;
		this.gameOutcome = gameOutcome;
	}
	
	public Game getGame() {
		return game;
	}
	
	public PlayerChoice getPlayerChoice() {
		return playerChoice;
	}
	
	public Outcome getGameOutcome() {
		return gameOutcome;
	}
}
