//Selim Erciyas
import java.util.*;
public class DeckOfCards {
	private Cards[] deck;
	private int currentCard; //Index of the card in deck to be dealt next.
	
	
	
	/**
	 * Constructor for DeckOfCards class.
	 * Makes 52 cards and puts them all into an array. 
	 * 13 of each suit.
	 * 4 of each face.
	 * Jack, Queen, and King get a face value of 10
	 * while Ace gets a value of 11.
	 * @return
	 */
	public DeckOfCards() {
		String[] faces = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
		String[] suits = {"Spades", "Clubs", "Diamonds", "Hearts"};
		
		deck = new Cards[52];
		currentCard = 0;
		
		/**
		 * For loop to start filling up the Cards object array deck with correct cards.
		 */
		for(int suit = 0; suit<suits.length; suit++) {
			for(int face = 0; face<faces.length; face++) {
				
				/**
				 * If conditions so when creating card objects in the object array deck, 
				 * the values that are given for the Jack, Queen, King, and Ace are correct.
				 */
				if(face<=8) {
					deck[(face)+(suit*13)] = new Cards(faces[face], suits[suit], face+2);
					continue;
				}
				if(face==12) {
					deck[(face)+(suit*13)] = new Cards(faces[face], suits[suit], 11);
					continue;
				}
				if(face>8 && face!=12) {
					deck[(face)+(suit*13)] = new Cards(faces[face], suits[suit], 10);
					continue;
				}
			}
		}
	}//End of constructor for deck of cards
	
	
	
	/**
	 * The shuffleDeck method shuffles all the cards.
	 * @return
	 */
	public void shuffleDeck() {
		Collections.shuffle(Arrays.asList(deck));
	}
	
	
	
	/**
	 * Will deal a card by checking to see if the current card number is still less than 52.
	 * Increments card by one so once a card has been dealt, the program knows to move to the
	 * next card in deck. If there are no more cards to be dealt (currentCard>=deck.length), 
	 * the method returns null.
	 * @return
	 */
	public Cards dealCard() {
		if(currentCard<deck.length) {
			return deck[currentCard++];
		}else {
			return null;
		}
	}
	
	
	
	/**
	 *The displayDeck method displays the cards inside the deck 
	 *utilizing the toString method in the Cards class.
	 *@return
	 */
	public void displayDeck() {
		for(Cards cards : deck) {
			System.out.println(cards.toString());
		}
	}
	
	
	/**
	 * The resetCurrentCardIndex method resets the current position/index of the deck array 
	 * the application is following back to [0]. Basically resets the deck.
	 * @return
	 */
	public void resetCurrentCardIndex() {
		currentCard=0;
	}
}
