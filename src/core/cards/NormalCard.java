package core.cards;

public class NormalCard extends Card {

	public NormalCard(Suit suit, Rank rank) {
		super(suit, rank);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Card clone() {
		return new NormalCard (getSuit(), getRank());
	}

	
}
