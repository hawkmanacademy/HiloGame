package com.hilogame.constants;

public enum Suit {
	Clubs("c"),Hearts("h"),Diamonds("d"),Spades("s");
	private final String value;
	
	private Suit(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
