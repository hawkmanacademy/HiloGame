package com.hilogame;

import java.util.Scanner;

import com.hilogame.model.Game;
import com.hilogame.model.GameResult;
import com.hilogame.services.MessageService;
import com.hilogame.services.impl.HiLoGameService;
import com.hilogame.services.impl.InMemoryMessageService;

public class HiLoGame {
	private MessageService messageService;

	private HiLoGameService hiloGameService;

	public static void main(String[] args) {
		new HiLoGame().run();
	}

	public HiLoGame() {
		messageService = new InMemoryMessageService();
		hiloGameService = new HiLoGameService(messageService);
	}

	public void run() {
		Scanner input = new Scanner(System.in);
		boolean signal = true;

		do {
			playGame(input);

			// Prompt the player if they want to play again.
			System.out.println("Do you want to play another game?");
			String quitSignal = input.nextLine();
			switch (quitSignal.toLowerCase()) {
			case "yes":
			case "y":
				// If they enter Y/y/Yes/yes, repeat the game until the deck
				// runs out
				signal = true;
				break;
			case "n":
			case "no":
				// If they enter N/n/No/no, end the game and shutdown the
				// program
				System.out.println("Thanks for playing. Good bye!");
				signal = false;
				break;
			default:
				// ignore and carry on
			}
		} while (signal);
	}

	private void playGame(Scanner input) {
		Game game = hiloGameService.startGame();

		System.out.println(messageService.readMessage("displayfirstcard"));
		System.out.println(messageService.readMessage("promptforhilo"));

		String playerChoice = input.nextLine();

		messageService.sendMessage("playerchoice", playerChoice);

		GameResult result = hiloGameService.endGame(game);

		System.out.println(messageService.readMessage("displayoutcome"));
	}
}
