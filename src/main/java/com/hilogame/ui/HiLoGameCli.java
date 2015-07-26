package com.hilogame.ui;

import java.util.Scanner;

import com.hilogame.model.Game;
import com.hilogame.model.GameResult;
import com.hilogame.service.MessageService;
import com.hilogame.service.impl.HiLoGameService;
import com.hilogame.service.impl.InMemoryMessageService;

public class HiLoGameCli {
	
	public static void main(String[] args) {
		new HiLoGameRunner().run();
	}
	
	static class HiLoGameRunner{
		private MessageService messageService;
		
		private  HiLoGameService hiloGameService ;
		
		public HiLoGameRunner() {
			messageService = new InMemoryMessageService();
			hiloGameService = new HiLoGameService(messageService);
		}
		
		public void run(){
			Scanner input =  new Scanner(System.in);
			boolean signal = true;
			
			do{
				playGame(input);
				
				//6. Prompt the player if they want to play again.
				System.out.println("Do you want to play another game?");
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
			}while (signal);
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
}
