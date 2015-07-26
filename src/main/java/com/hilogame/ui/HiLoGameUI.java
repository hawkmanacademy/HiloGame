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

import com.hilogame.model.Game;
import com.hilogame.model.GameResult;
import com.hilogame.model.Outcome;
import com.hilogame.model.PlayerChoice;
import com.hilogame.model.card.Card;
import com.hilogame.model.card.Rank;
import com.hilogame.model.card.Suit;
import com.hilogame.service.MessageService;
import com.hilogame.service.impl.HiLoGameService;
import com.hilogame.service.impl.InMemoryMessageService;

public class HiLoGameUI {
	private MessageService messageService;
	private HiLoGameService gameService;
	private AtomicInteger lossCounter;
	private AtomicInteger winCounter;
	
	

	Game currentGame = null;

	public HiLoGameUI() {
		messageService = new InMemoryMessageService();
		gameService = new HiLoGameService(messageService);
		lossCounter = new AtomicInteger();
		winCounter = new AtomicInteger();
		
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
			winCounter.incrementAndGet();
		}else{
			lossCounter.incrementAndGet();
		}
		scorePanel.updateScores(winCounter.intValue(), lossCounter.intValue());
		currentGame = null;
	}

	public static void main(String[] args) {
		new HiLoGameUI();
	}
}
