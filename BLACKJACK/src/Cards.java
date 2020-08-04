//Selim Erciyas
public class Cards {
	private String faceName, suit;
	private int faceValue;
	
	/**
	 * Constructor for card class.
	 * @param faceName 2,3,4,5,6...10,Jack,Queen,King,Ace
	 * @param suit "spades","clubs","diamonds","hearts"
	 * @param faceValue 2,3,4,5,6,7,8,9,10,11
	 */
	public Cards(String faceName, String suit, int faceValue) {
		this.faceName = faceName;
		this.suit = suit;
		this.faceValue = faceValue;
	}
	
	/**
	 * This returns a string representation of the face name of the card as well with the suit.
	 * @return
	 */
	public String toString(){
		return faceName + " of " + suit;
	}
	
	/**
	 * This returns an integer of the face value of the card.
	 * @return
	 */
	public int getFaceValue(){
		return faceValue;
	}
}
