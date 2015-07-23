package com.hilogame;

import java.util.Scanner;

import com.hilogame.exception.NoMoreCardsException;
import com.hilogame.model.card.Card;
import com.hilogame.model.card.Deck;

public class HiLoGame {
	private static Deck deck = new Deck();
	public static void main(String[] args) throws NoMoreCardsException {
		Scanner input =  new Scanner(System.in);
		boolean signal = true;
		while (signal){
			playGame(input);
			
			//6. Prompt the player is they want to play again.
			System.out.println("Do you want to play another round?");
			String quitSignal = input.nextLine();
			switch(quitSignal.toLowerCase()){
				case "yes":
				case "y":
					//7. If they enter Y/y/Yes/yes, repeat the game until the deck runs out
					signal = false;
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
	
	private static void playGame(Scanner input) throws NoMoreCardsException{
		//0 play out the game and 
		Card firstCard = deck.getNextCard();
		Card secondCard = deck.getNextCard();
		
		//1. Display first card
		System.out.println("First card is " + firstCard);
		
		//2. prompt player to guess whether second card is high or low (H/h/High/high =High, L/l/Low/low = Low)
		System.out.println("Guess whether second card is high or low (H/h/High/high =High, L/l/Low/low = Low)");
		
		//get the users input
		String hilo = input.nextLine();
		
		//3. Compare the cards and compare result to players choice. 
		int score = firstCard.compareTo(secondCard);
		switch(hilo.toLowerCase()){
		case "h":
		case "high":
			//4. if the player guesses correctly, show message "You Win" and display both cards e.g. 2H 5D - You Win!
			//5. If the player guesses wrongly, show message "You looser. Better luck next time" and display the cards e.g. 6H 3D - "You looser. Better luck next time"
			if (score > 0){
				System.out.println( firstCard + " " + secondCard + "- You Win!" );
			}else{
				System.out.println( firstCard + " " + secondCard + "- You looser! Bette rluck next time!" );
			}
			
			break;
		case "l":
		case "low":
			//4. if the player guesses correctly, show message "You Win" and display both cards e.g. 2H 5D - You Win!
			//5. If the player guesses wrongly, show message "You looser. Better luck next time" and display the cards e.g. 6H 3D - "You looser. Better luck next time"
			if (score < 0){
				System.out.println( firstCard + " " + secondCard + "- You Win!" );
			}else{
				System.out.println( firstCard + " " + secondCard + "- You looser! Bette rluck next time!" );
			}
			break;
		default:
			throw new IllegalArgumentException(hilo + " lis not recognised");
		}
	}
		

}
