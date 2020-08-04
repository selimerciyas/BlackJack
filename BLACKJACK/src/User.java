//Selim Erciyas
import java.util.*;

public class User {
	private int cash;
	private String userName;
	private int handCount;
	private int hitOrStay;
	private Cards[] hand = new Cards[10];
	
	Scanner scan = new Scanner(System.in);
	
	
	
	/**
	 * Constructor for the User class. Defines the users name and the amount of cash
	 * the user starts out with.
	 * The amount of cards in the users hand is also initialized. 
	 * @return
	 */
	public User(String userName, int cash) {
		this.handCount = 0;
		this.userName = userName;
		this.cash = cash;
	}
	
	/**
	 * The cardSum method return the sum of the cards the user has in his or her hand as
	 * an integer. 
	 * @return
	 */
	public int cardSum() {
		int sum = 0;
		for(int x= 0; x<handCount; x++) {
			sum+=hand[x].getFaceValue();
		}
	
		return sum;
	}
	
	/**
	 * toString method where it prints out the user name and how much cash they have
	 * left to gamble. 
	 * @return
	 */
	public String toString() {
		return this.userName + ", you have " + this.cash + " dollars to bet.";
	}
	
	/**
	 * The getName method returns the users name as a string.
	 * @return
	 */
	public String getName() {
		return this.userName;
	}
	
	/**
	 * The resetHand method resets the hand of the user by making all
	 * elements in the hand array "null" and the hand count(number of cards in hand) 
	 * equal to 0.
	 * @return
	 */
	public void resetHand() {
		Arrays.fill(this.hand, null);
		this.handCount=0;
	}
	
	/**
	 * The addCardToHand method adds a card to the hand array and adds a number
	 * to the hand count.
	 * @param Card
	 * @return
	 */
	public void addCardToHand(Cards Card) {
		this.hand[handCount] = Card;
		this.handCount++;
	}
	
	/**
	 * The displayHand method displays the current hand of the user by printing out
	 * each card in the hand.
	 * @return
	 */
	public String displayHand() {
		String handDisplay = "";
		for(int x=0; x<handCount; x++) {
			handDisplay+= hand[x].toString() + "\n";
		}
		return handDisplay;
	}
	
	/**
	 * The computerBeginningDisplay method is similar to the displayHand method however it
	 * is used to display the hand of the computer as one card is always hidden.
	 * @return
	 */
	public String computerBeginningDisplay() {
		String handDisplay = "";
		handDisplay = hand[0].toString() + "\n{CARD HIDDEN}\n";
		return handDisplay;
	}
	
	/**
	 * The getCash method is a getter for this.cash.
	 * @return
	 */
	public int getCash() {
		return this.cash;
	}
	
	/**
	 * The betCash method takes in an int parameter for the amount the user 
	 * wants to bet. It lets the user to bet cash and makes sure the amount can be 
	 * withdrawn from the users cash stack.
	 * @param betAmount
	 * @return
	 */
	public void betCash(int betAmount) {
		if(this.cash-betAmount>=0) {
			this.cash-=betAmount;
		}else {
			System.out.println("Error. Insufficient funds.");
		}
	}
	
	/**
	 * The winBet method is called when the user wins a bet. It takes in
	 * the integer parameter betTotal which is then added to the users cash stack.
	 * @param betTotal
	 * @return
	 */
	public void winBet(int betTotal) {
		this.cash += betTotal;
	}
	
	/**
	 * The hitOrStayQuestion method asks the user if they want to hit or stay.
	 * @return
	 */
	public void hitOrStayQuestion() {
		System.out.println("Would you like to hit(1) or stay(2)?");
		while(!scan.hasNextInt()) {
			scan.next();	
			System.out.println("\nWould you like to hit(1) or stay(2)?");
		}
		this.hitOrStay = scan.nextInt();
	}
	
	/**
	 * The getHitOrStay method gets the users response to the hitOrStayQuestion method.
	 * Or in other words, return this.hitOrStay as an integer.
	 * @return
	 */
	public int getHitOrStay() {
		return this.hitOrStay;
	}
	
	/**
	 * This is a setter for the this.hitOrStay. It takes in a integer parameter called num.
	 * @param num
	 * @return
	 */
	public void setHitOrStay(int num) {
		this.hitOrStay = num;
	}
	
}
