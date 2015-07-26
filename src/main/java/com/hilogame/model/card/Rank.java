package com.hilogame.model.card;

public enum Rank {
	Two("2",2),Three("3",3),
	Four("4",4),Five("5",5),Six("6",6),
	Seven("7",7),Eight("8",8),Nine("9",9),
	Ten("10",10),Jack("j",11),Queen("q",12),
	King("k",13),Ace("1",1);
	
	private final Integer intValue;
	
	private final String value;
	
	private Rank(String value,Integer intValue) {
		this.value = value;
		this.intValue = intValue;
	}
	
	public String getValue(){
		return value;
	}
	
	public Integer getIntValue() {
		return intValue;
	}
}
