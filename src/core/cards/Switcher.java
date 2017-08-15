/**
 * 
 */
package core.cards;

import core.Game;

/**
 * @author Konrad Linkowski
 *
 */
public class Switcher extends ActionCard {

	/**
	 * 
	 */
	public Switcher() {
		super ("Swapper", "Swaps next card with enemy's next card.");
	}

	/* (non-Javadoc)
	 * @see core.cards.ActionCard#doSomething()
	 */
	@Override
	public void doSomething() {
		Card temp1 = Game.enemyDeck.poll();
		Card temp2 = Game.playerDeck.poll();
		Game.playerDeck.add(0, temp1);
		Game.enemyDeck.add(0, temp2);
	}

	/* (non-Javadoc)
	 * @see core.cards.Card#clone()
	 */
	@Override
	public Card clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
