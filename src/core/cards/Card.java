package core.cards;

public abstract class Card {
	
	private Suit suit;
	private Rank rank;
	private int value;
	private String name;
	
	public Card (Suit suit, Rank rank) {
		this.suit = suit;
		this. rank = rank;
		value = rank.getValue();
		name = rank.getName() + " " + suit.getName ();
	}
	
	protected Card (Suit suit, Rank rank, String name) {
		this.suit = suit;
		this. rank = rank;
		value = rank.getValue();
		this.name = name;
	}
	
	public void changeValue (int difference) {
		value += difference;
	}
	
	public int getValue () {
		return rank.getValue();
	}
	
	public void setValue (int value) {
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public abstract Card clone ();
	
	public String toString () {
		return name + value;
	}
	
	public String getName () {
		return name;
	}
	
	public String getDiscription () {
		return "";
	}
}
