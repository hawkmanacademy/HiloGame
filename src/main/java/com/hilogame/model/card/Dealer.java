package com.hilogame.model.card;

import java.util.ArrayList;
import java.util.List;

import com.hilogame.exception.NoMoreCardsException;

public class Dealer {
	private Deck deck = new Deck();
	
	public List<Card> deal(int numberOfCards) throws NoMoreCardsException{
		List<Card> cards = new ArrayList<Card>();
		for (int i=0;i<numberOfCards;i++){
			cards.add(deck.getNextCard());
		}
		return cards;
	}
}
