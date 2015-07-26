package com.hilogame.model.card;

public class Card implements Comparable<Card>{
	private Rank rank;
	
	private Suit suite;
	
	public Card(Rank rank,Suit suite) {
		this.rank = rank;
		this.suite = suite;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Suit getSuite() {
		return suite;
	}

	public void setSuite(Suit suite) {
		this.suite = suite;
	}
	
	public String getImageName(){
		return suite.getValue() + rank.getValue();
	}
	
	@Override
	public int compareTo(Card o) {
		int score = o.rank.getIntValue() - rank.getIntValue();
		return score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suite == null) ? 0 : suite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suite != other.suite)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + rank + " of " + suite + "]";
	}
}
