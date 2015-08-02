package com.hilogame.services;

import com.hilogame.model.Game;
import com.hilogame.model.GameResult;

public interface GameService {
	public Game startGame();
	public GameResult endGame(Game game);
}
