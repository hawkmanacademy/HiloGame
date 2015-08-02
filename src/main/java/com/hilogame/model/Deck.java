package com.hilogame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hilogame.constants.Rank;
import com.hilogame.constants.Suit;
import com.hilogame.exception.NoMoreCardsException;

public class Deck {
	private List<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
		reset();
	}

	public void reset() {
		cards.clear();
		for (Suit suite:Suit.values()){
			for (Rank rank :Rank.values()){
				Card card = new Card(rank,suite);
				cards.add(card);
			}
		}
		shuffle();
	}
	
	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	
	public Card getNextCard() throws NoMoreCardsException{
		if (cards.size() > 0){
			return cards.remove(0);
		}else{
			throw new NoMoreCardsException();
		}
	}
}
