/**
 * 
 */
package core.cards;

/**
 * @author Konrad Linkowski
 *
 */
public enum Suit {
	SPADES ("spades"),
    HEARTS ("hearts"),
    DIAMONDS ("diamonds"),
    CLUBS ("clubs"),
    SPECIAL ("special");

	private final String name;
	
	Suit (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLetter () {
		return name.substring(0, 1);
	}
}
