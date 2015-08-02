package com.hilogame.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import com.hilogame.constants.Outcome;
import com.hilogame.constants.PlayerChoice;
import com.hilogame.constants.Rank;
import com.hilogame.constants.Suit;
import com.hilogame.model.Card;
import com.hilogame.model.Game;
import com.hilogame.model.GameResult;
import com.hilogame.services.GameService;
import com.hilogame.services.MessageService;
import com.hilogame.services.ScoreTrackingService;
import com.hilogame.services.impl.HiLoGameService;
import com.hilogame.services.impl.InMemoryMessageService;
import com.hilogame.services.impl.SimpleScoreTrackingService;

public class HiLoGameUI {
	private MessageService messageService;
	private GameService gameService;
	private ScoreTrackingService scoreTrackingService;
	
	Game currentGame = null;

	public HiLoGameUI() {
		messageService = new InMemoryMessageService();
		gameService = new HiLoGameService(messageService);
		scoreTrackingService = new SimpleScoreTrackingService();
		
		currentGame = startGame();
		final JFrame frame = new JFrame(); // make sure the program exits when
											// the
		// frame closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("HiLo Game UI");
		frame.setSize(300, 250);

		JPanel canvas = new JPanel();
		canvas.setLayout(new BorderLayout());

		setupUI(canvas);
		
		frame.getContentPane().add(canvas);
		//frame.pack();
		frame.setVisible(true);
	}

	private void setupUI(final JPanel canvas) {

		ScorePanel scorePanel = new ScorePanel();

		JPanel cardPanel = new JPanel();
		CardPanel cardPanel1 = new CardPanel();
		cardPanel1.setCard(currentGame.getFirstCard());
		cardPanel.add(cardPanel1);

		CardPanel cardPanel2 = new CardPanel();
		cardPanel.add(cardPanel2);

		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Game newGame = startGame();
				cardPanel1.setCard(newGame.getFirstCard());
				cardPanel2.reset();
			}
		});
		
		canvas.add(scorePanel, BorderLayout.NORTH);

		JPanel buttonPanelBottom = new JPanel();

		JButton hiButton = new JButton("Hi");
		hiButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				messageService.sendMessage("playerchoice", "h");
				endGame(cardPanel2, scorePanel,currentGame);
			}
		});
		buttonPanelBottom.add(hiButton);

		JButton loButton = new JButton("Lo");
		loButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				messageService.sendMessage("playerchoice", "l");
				endGame(cardPanel2, scorePanel,currentGame);
			}
		});
		buttonPanelBottom.add(loButton);
		buttonPanelBottom.add(newGameButton);

		canvas.add(buttonPanelBottom, BorderLayout.SOUTH);

		canvas.add(cardPanel, BorderLayout.CENTER);

	}

	private Game startGame() {
		currentGame = gameService.startGame();
		return currentGame;
	}

	private void endGame(CardPanel cardPanel,ScorePanel scorePanel, Game game) {
		GameResult result = gameService.endGame(game);
		Card card = result.getGame().getSecondCard();
		cardPanel.setCard(card);
		
		if (result.getGameOutcome() == Outcome.Won){
			scoreTrackingService.recordWin();
		}else{
			scoreTrackingService.recordLoss();
		}
		scorePanel.updateScores(scoreTrackingService.getTotalWins(), scoreTrackingService.getTotalLosses());
		currentGame = null;
	}
 
	public static void main(String[] args) {
		new HiLoGameUI();
	}
}
