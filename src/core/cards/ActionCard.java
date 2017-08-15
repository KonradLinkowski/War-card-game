/**
 * 
 */
package core.cards;

/**
 * @author Konrad Linkowski
 *
 */
public abstract class ActionCard extends Card {

	private String discription;
	
	public static Class<?> [] actionCards = new Class [] {
		Unicorn.class,
		Switcher.class,
		Reverser.class
	};
	
	
	public ActionCard(String name, String discription) {
		super(Suit.SPECIAL, Rank.SPECIAL, name);
		this.discription = discription;
	}
	
	public abstract void doSomething ();

	
	public String getDiscription () {
		return discription;
	}
}
