import java.util.*;

public class GameRunner {
	public static void main(String[] args) {
		int pot = 0; //The bet amounts every round.

		/**
		 * The human player and the computer is initialized using the User class.
		 */
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your name: ");
		User humanPlayer = new User(scan.nextLine(), 500);
		User computer = new User("Bot", 500);
		
		/**
		 * The deck that is going to be used in the game is initialized 
		 * using the DeckOfCards class.
		 */
		DeckOfCards theDeck = new DeckOfCards();
		
		//A boolean condition to make the game running as long as the user wants it to run. 
		boolean gameContinue = true;
		while(gameContinue) {
			
			/**
			 * This block of code tries to get the correct bet amount
			 * from the user by making sure the user is inputting 
			 * an integer and also making sure that the user can 
			 * afford to bet the specified integer.
			 */
			boolean betCondition = false;
			while(!betCondition) {
				
				System.out.println("\n"+ humanPlayer.toString());
				System.out.println("How much would you like to bet?");
				while(!scan.hasNextInt()) {
					System.out.println(humanPlayer.toString() +
						"\nHow much would you like to bet?");
					scan.next();
					}
					pot = scan.nextInt();
	
				
					if(humanPlayer.getCash() - pot >= 0 && pot>0) {
						betCondition = true;
					}else{
						betCondition = false;
					}
			}
			
			//The User class for the human player puts money in the pot.
			humanPlayer.betCash(pot);
			pot = pot*2; 
			
			/**
			 * The deck is shuffled using the shuffleDeck method
			 * found in the DeckOfCards class. 
			 */
			theDeck.shuffleDeck();
			
			/**
			 * Two cards each are dealt to the user and the computer
			 * using the dealCard method found in the DeckOfCards class.
			 * The cards that are dealt get added to each users hand using 
			 * addCardToHand method in the User class.
			 */
			for (int x=0; x<2; x++) {
				humanPlayer.addCardToHand(theDeck.dealCard());
				computer.addCardToHand(theDeck.dealCard());
			}
			
			/**
			 * Information about the cards that have been dealt are displayed
			 * on console including the total sum of the dealt cards for each user.
			 */
			
			System.out.println("\nComputer has: \n"+
			computer.computerBeginningDisplay()+"\n"+ humanPlayer.getName()
			+ ", you have the following cards: \n"+
			humanPlayer.displayHand()+"Total: "+humanPlayer.cardSum());
			
			/**
			 * A condition to make sure before giving the option to the user
			 * to hit or stand that the dealt cards do not add to 21. Else game ends.
			 */
			boolean cont = true;
			if(humanPlayer.cardSum()==21 || computer.cardSum()==21) {
				cont=false;
			}
			
			/**
			 * Makes sure that none of the players already have 21 before continuing.
			 */
			if(cont) {
				
				/**
				 * The "brain" of the computer user. Lets the computer decide whether to hit
				 * or stay. The closer the current card sum is to 21, the less
				 * likely the computer is to hit. 
				 * This is done by getting the difference between 21 and the current card
				 * sum of the computer user and choosing a random integer between 1-12.
				 * If the random number is less than or equal to the difference, the computer
				 * hits and gets another card and the whole process is repeated(finding the
				 * difference, choosing a random number). If the random number is bigger than
				 * the difference, the computer "stays" and does not get another card.
				 */
				boolean computerHitDecision= true;
				while(computerHitDecision) {
					Random rand = new Random();
					if(computer.cardSum()==21) {
						computerHitDecision=false;
						break;
					}
					if(computer.cardSum()<21) {
						int difference = 21 - computer.cardSum();
						int randomNum = 0;
						while (true){
						    randomNum = rand.nextInt(13);
						    if(randomNum !=0) {
						    	break;
						    }
						}
						if(randomNum<=difference) {
							computerHitDecision = true;
							computer.addCardToHand(theDeck.dealCard());
							continue;
						}
						if(randomNum>difference) {
							computerHitDecision = false;
							break;
						}
					}
				}
				
				
				
				
				
				/**
				 * This is where the hit or stay option is decided for the user. The code 
				 * repeatedly asks the user if they want to hit or stay till they input
				 * an integer(either 1 or 2). If the user inputs a integer other than 1 or 2
				 * or a character, the code keeps asking for an integer. 
				 */
				boolean humanHit=true;
				while(humanHit==true) {
					humanPlayer.hitOrStayQuestion();
					while(humanPlayer.getHitOrStay()>2||humanPlayer.getHitOrStay()<1) {
						humanPlayer.hitOrStayQuestion();
					}
					if(humanPlayer.getHitOrStay()==2) {
					    humanHit = false;
					    break;
					}
					if(humanPlayer.getHitOrStay()==1) {		
						humanHit = true;
						humanPlayer.addCardToHand(theDeck.dealCard());
					}
		
					if(humanPlayer.cardSum()>21) {
						humanHit=false;
						break;
					}
					
					System.out.println("---------\n"+humanPlayer.getName()
					+ ", you have the following cards: \n" + humanPlayer.displayHand()+"Total: "
					+humanPlayer.cardSum());
				}
					
				
			/**
			 * The end sequence where the winner and loser is determined and where the pot
			 * is given to the winner and is deducted from the loser. 
			 */
			}
			System.out.println("\nComputer had: \n"+computer.displayHand()+"\nTotal: "+
			computer.cardSum()+"\n---------\nYou had: \n" + humanPlayer.displayHand()+
			"\nTotal: "+humanPlayer.cardSum()+"\n---------");
			String winner = "";
			if(humanPlayer.cardSum()>computer.cardSum() && humanPlayer.cardSum()<=21) {
				winner+=humanPlayer.getName()+", you won the hand!\nYou earned: "+pot/2;
				humanPlayer.winBet(pot);
			}
			if(humanPlayer.cardSum()<computer.cardSum()&&computer.cardSum()<=21) {
				winner+="The computer wins...\nYou lost " + pot/2;
			}
			if(humanPlayer.cardSum()==computer.cardSum()){
				winner+="DRAW! No winners.";
				humanPlayer.winBet(pot/2);
			}
			if(computer.cardSum()>humanPlayer.cardSum() && computer.cardSum()>21) {
				winner+=humanPlayer.getName()+", you won the hand!\nYou earned: "+pot/2;
				humanPlayer.winBet(pot);
			}
			if(computer.cardSum()<humanPlayer.cardSum() && humanPlayer.cardSum()>21) {
				winner+="The computer wins...\nYou lost "+pot/2;
			}
			if(computer.cardSum()>21 && humanPlayer.cardSum()>21) {
				winner+="The computer/HOUSE wins...\nYou lost " + pot/2;
			}
			System.out.println(winner);
			System.out.println("Current amount of cash left: "+humanPlayer.getCash()+"\n---------\n");
			/**
			 * The hand is reset for all users and the deck is reset and shuffled again for 
			 * the next round of the game.
			 */
			humanPlayer.resetHand();
			computer.resetHand();
			theDeck.resetCurrentCardIndex();
			pot=0;
			
			/**
			 * Program asks whether or not if the user wants to continue playing. The user
			 * is asked to input a 1 or 2 and will continue asking till they do. 
			 */
			int decision = 0;
			boolean decisionCondition = true;
			while(decisionCondition) {
				System.out.println("Would you like to continue(1) or exit(2)? ");
				while(!scan.hasNextInt()) {
						scan.next();	
						System.out.println("Would you like to continue(1) or exit(2)? ");
				}
				decision = scan.nextInt();
				if(decision>2 || decision<1) {
					decisionCondition = true;
				}
				if(decision==1) {
					gameContinue = true;
					decisionCondition = false;
				}
				if(decision==2) {
					gameContinue = false;
					decisionCondition = false;
				}	
			}
			
		}
		scan.close();
	}
}

