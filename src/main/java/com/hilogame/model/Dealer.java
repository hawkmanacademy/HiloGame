package com.hilogame.model;

import java.util.ArrayList;
import java.util.List;

import com.hilogame.exception.NoMoreCardsException;

public class Dealer {
	private Deck deck = new Deck();
	
	public Card deal() throws NoMoreCardsException{
		return deal(1).get(0);
	}
	public List<Card> deal(int numberOfCards) throws NoMoreCardsException{
		List<Card> cards = new ArrayList<Card>();
		for (int i=0;i<numberOfCards;i++){
			cards.add(deck.getNextCard());
		}
		return cards;
	}
}
