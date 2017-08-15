package core;

import core.cards.Card;

public class Turn {

	private Card lastEnemyCard;
	private Card lastPlayerCard;
	private Deck turnWinner;
	private boolean end;
	private Deck gameWinner;
	private boolean war;
	private boolean cardsReversed;
	/*
	private final Queue <Card> playerCardsList = new LinkedList <Card> ();
	private final Queue <Card> enemyCardsList = new LinkedList <Card> ();
	*/
	public Turn () {
		
	}
	
	public Turn(Deck turnWinner, boolean end, Deck gameWinner, boolean war) {
		this.turnWinner = turnWinner;
		this.end = end;
		this.gameWinner = gameWinner;
		this.war = war;
	}

	public Deck getTurnWinner() {
		return turnWinner;
	}

	public void setTurnWinner(Deck turnWinner) {
		this.turnWinner = turnWinner;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public Deck getGameWinner() {
		return gameWinner;
	}

	public void setGameWinner(Deck gameWinner) {
		this.gameWinner = gameWinner;
	}
	
	public boolean isWar () {
		return war;
	}
	
	public void setWar ( boolean war) {
		this.war = war;
	}


	public boolean isCardsReversed() {
		return cardsReversed;
	}

	public void setCardsReversed(boolean cardsReversed) {
		this.cardsReversed = cardsReversed;
	}

	public Card getLastEnemyCard() {
		return lastEnemyCard;
	}


	public Card getLastPlayerCard() {
		return lastPlayerCard;
	}
	public void setLastEnemyCard(Card lastEnemyCard) {
		this.lastEnemyCard = lastEnemyCard;
		//enemyCardsList.add(lastEnemyCard);
	}
	
	public void setLastPlayerCard(Card lastPlayerCard) {
		this.lastPlayerCard = lastPlayerCard;
		//playerCardsList.add(lastPlayerCard);
	}
/*
	Queue<Card> getPlayerCardsList() {
		return playerCardsList;
	}

	Queue<Card> getEnemyCardsList() {
		return enemyCardsList;
	}
	*/
}
