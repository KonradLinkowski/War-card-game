package core;

import java.util.LinkedList;
import java.util.List;
import core.cards.ActionCard;
import core.cards.Card;

public class Game {

	public static Deck playerDeck;
	public static Deck enemyDeck;
	public static Deck allCards;
	
	private List <Card> putCardsList = new LinkedList <Card> ();
	
	private int turnIndex = 1;
	
	public Game() {
		playerDeck = new Deck ();
		enemyDeck = new Deck ();
		allCards = new Deck (System.nanoTime());
		allCards.shuffle();
		for (int i = 0; i < allCards.getSize (); i++) {
			if (i % 2 == 0) {
				playerDeck.add(allCards.get(i));
			} else {
				enemyDeck.add(allCards.get(i));
			}
		}
		for (int i = 0; i < playerDeck.getSize (); i++) {
			System.out.println(playerDeck.get(i).toString());
		}
		System.out.println();
		for (int i = 0; i < enemyDeck.getSize (); i++) {
			System.out.println(enemyDeck.get(i).toString());
		}
	}
	
	public Turn nextTurn (Turn turn) {
		turn.setLastPlayerCard(playerDeck.poll());
		turn.setLastEnemyCard(enemyDeck.poll());
		
		
		putCardsList.add(turn.getLastPlayerCard());
		putCardsList.add(turn.getLastEnemyCard());
		
		
		if (turn.isWar()) {
			turn.setCardsReversed(!turn.isCardsReversed());
		}
		
		if (!turn.isWar() && !turn.isCardsReversed()) {
			if (turn.getLastPlayerCard() instanceof ActionCard) {
				((ActionCard) turn.getLastPlayerCard()).doSomething();
			}
			if (turn.getLastEnemyCard() instanceof ActionCard) {
				((ActionCard) turn.getLastEnemyCard()).doSomething();
			}
		}
		
		if (!turn.isCardsReversed()) {
			if (turn.getLastEnemyCard().getValue() > turn.getLastPlayerCard().getValue()) {
				enemyDeck.addAll(putCardsList);
				putCardsList.clear();
				turn.setTurnWinner(enemyDeck);
				turn.setWar(false);
			} else if (turn.getLastEnemyCard().getValue() < turn.getLastPlayerCard().getValue()) {
				playerDeck.addAll(putCardsList);
				putCardsList.clear();
				turn.setTurnWinner(playerDeck);
				turn.setWar(false);
			} else {
				turn.setWar(true);
			}
		}
		
		if (enemyDeck.getSize() == 0) {
			turn.setEnd(true);
			turn.setGameWinner(playerDeck);
		} else if (playerDeck.getSize() == 0) {
			turn.setEnd(true);
			turn.setGameWinner(enemyDeck);
		}
		turnIndex++;
		return turn;
	}

	public int getTurnIndex() {
		return turnIndex;
	}
}
