package core.cards;

import java.util.Random;

import core.Game;

public class Unicorn extends ActionCard {

	/**
	 * 
	 * @param value
	 */
	public Unicorn() {
		super ("Unicorn", "Randomly changes value of your next card.");
	}

	@Override
	public void doSomething() {
		Game.playerDeck.get (0).changeValue (new Random().nextInt(30) - 15);
	}

	@Override
	public Card clone() {
		
		return new Unicorn ();
	}

}
