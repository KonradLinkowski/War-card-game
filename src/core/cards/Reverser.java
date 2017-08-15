package core.cards;

import java.util.LinkedList;
import java.util.List;

import core.Game;

public class Reverser extends ActionCard {

	public Reverser() {
		super ("Switcher", "Reverses your deck.");
	}

	@Override
	public void doSomething() {
		List <Card> tempList = new LinkedList <Card> ();
		int size = Game.playerDeck.getSize();
		for (int i = 0; i < size; i++) {
			tempList.add(Game.playerDeck.poll());
		}
		for (int i = 0; i < size; i++) {
			Game.playerDeck.add(tempList.get(i));
		}
		tempList.clear();
	}

	@Override
	public Card clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
