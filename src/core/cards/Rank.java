/**
 * 
 */
package core.cards;

/**
 * @author Konrad Linkowski
 *
 */
public enum Rank {
	TWO (2, "2"),
	THREE (3, "3"),
	FOUR (4, "4"),
	FIVE (5, "5"),
	SIX (6, "6"),
	SEVEN (7, "7"),
	EIGHT (8, "8"),
	NINE (9, "9"),
	TEN (10, "10"),
	JACK (11, "Jack"),
	QUEEN (12, "Queen"),
	KING (13, "King"),
	ACE (14, "Ace"),
	SPECIAL (0, "Special");

	private final int value;
	private final String name;
    Rank (int value, String name) {
    	this.value = value;
    	this.name = name;
    }
    
    public int getValue() {
    	return value;
    }

	public String getName() {
		return name;
	}
	
	public String getLetter () {
		return value <= 10 && value > 0 ? name : name.substring(0, 1);
	}
}
