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
		
		private  HiLoGameService hgs ;
		
		public HiLoGameRunner() {
			messageService = new InMemoryMessageService();
			
			hgs = new HiLoGameService();
			hgs.setMessageService(messageService);
		}
		
		public void run(){
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
		
		private void playGame(Scanner input) {
			Game game = hgs.startGame();
			
			System.out.println(messageService.readMessage("displayfirstcard"));
			System.out.println(messageService.readMessage("promptforhilo"));
			
			String playerChoice = input.nextLine();
			
			messageService.sendMessage("playerchoice", playerChoice);
			
			GameResult result = hgs.endGame(game);
			
			System.out.println(messageService.readMessage("displayoutcome"));
		}
	}
	
	
	
	
		

}
