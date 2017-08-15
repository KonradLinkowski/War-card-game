/**
 * 
 */
package core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import core.cards.ActionCard;
import core.cards.Card;
import core.cards.NormalCard;
import core.cards.Rank;
import core.cards.Suit;

/**
 * @author Konrad Linkowski
 *
 */
public class Deck {

	private final List <Card> cards = new LinkedList <Card> ();
	
	private int specialCardMax = 10;
	private int specialCardMin = 4;
	
	public Deck () {
		
	}
	
	/**
	 * 
	 */
	public Deck(long seed) {
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				if (suit == Suit.SPECIAL || rank == Rank.SPECIAL) {
					continue;
				}
				cards.add(new NormalCard (suit, rank));
			}
		}
		Random r = new Random (seed);
		int randomNumber;
		Card actionCard = null;
		for (Class<?> cardClass : ActionCard.actionCards) {
			randomNumber = r.nextInt(specialCardMax) + specialCardMin;
			for (int i = 0; i < randomNumber; i++) {
				try {
					actionCard = (Card) cardClass.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					System.err.println("Something went wrong when trying to instantiate action card");
					e.printStackTrace();
				}
				cards.add(actionCard);
			}
		}
	}
	
	public void shuffle () {
		Collections.shuffle(cards);
	}
	
	public int getSize () {
		return cards.size();
	}
	
	public void add (Card card) {
		cards.add(card);
	}
	
	public void add (int index, Card card) {
		cards.add(index, card);
	}
	
	public Card remove (int index) {
		return cards.remove(index);
	}
	
	public Card poll () {
		return (cards.remove(0));
	}
	
	public Card get (int index) {
		return cards.get(index);
	}
	
	public void addAll (List<Card> cardsList) {
		cards.addAll(cardsList);
	}
	
	public String toString () {
		String x = "";
		for (Card e : cards) {
			x += e.toString() + "\n";
		}
		return x;
	}

}
