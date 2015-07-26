package com.hilogame.ui;

import java.util.Scanner;

import com.hilogame.model.Game;
import com.hilogame.model.GameResult;
import com.hilogame.service.HiLoGameService;

public class HiLoGameCli {
	private static HiLoGameService hgs = new HiLoGameService();
	public static void main(String[] args) {
		Scanner input =  new Scanner(System.in);
		boolean signal = true;
		while (signal){
			playGame(input);
			
			//6. Prompt the player if they want to play again.
			System.out.println("Do you want to play another round?");
			String quitSignal = input.nextLine();
			switch(quitSignal.toLowerCase()){
				case "yes":
				case "y":
					//7. If they enter Y/y/Yes/yes, repeat the game until the deck runs out
					signal = true;
					break;
				case "n":
				case "no":
					//8. If they enter N/n/No/no, end the game and shutdown the program
					System.out.println("Thanks for playing. Good bye!");
					signal = false;
					break;
				default:
					//ignore and carry on
			}
		}
	}
	
	private static void playGame(Scanner input) {
		Game game = hgs.startGame();
		
		System.out.println(game.getMessage("displayfirstcard"));
		System.out.println(game.getMessage("promptforhilo"));
		
		String playerChoice = input.nextLine();
		
		game.sendMessage("playerchoice", playerChoice);
		
		GameResult result = hgs.endGame(game);
		
		System.out.println(result.getMessage());
	}
	
	
		

}
