import java.util.Scanner;


public class Game 
{

	protected Player player; // the active player in the game
	protected Dealer dealer; // the simulated dealer in the game
	protected Deck deck; // the current deck of cards
	protected double pot; // the pot of the current round
	protected String winner = null;
	
	
	/**
	 * Default constructor initializes the player, dealer, deck
	 */
	public Game()
	{
		player = new Player();
		dealer = new Dealer();
		deck = new Deck();
	} // end default constructor
	
	
	/**
	 * The first deal of the game, deals cards to dealer and player
	 */
	public void openingDeal()
	{
		// deal first card to player
		player.addCard(deck.deal());
		
		// deal face-down card to dealer
		dealer.setHiddenCard(deck.deal());
		if (dealer.getHiddenCard().getCardName() == CardNames.ACE)
			dealer.numAces++;
		
		//deal second card to player
		player.addCard(deck.deal());
		
		// deal face-up card to dealer
		dealer.addCard(deck.deal());
		
		player.calculateTotalScore();
		dealer.calculateTotalScore();
	}
	
	
	/**
	 * The game loop for the player, where it asks if the player 
	 */
	public void playerGameLoop(Scanner s)
	{
		
		while(player.getScore() <= 21)
		{
			System.out.println("The dealer's visible card is: " + dealer.hand[0].getCardName() + "\n");
			char input;
			
			// Display cards and total score
			player.displayCards();
			System.out.println("Hand value: " + player.getScore());
			
			// ask player if they want to hit or stand
			System.out.println("\nWould you like to hit or stand?");
			
			// input loop
			do
			{
			System.out.print("Type h to hit, or s to stand: ");
			input = s.nextLine().charAt(0);
			if(input == 's')
			{
				System.out.println("\nYour final hand: ");
				player.displayCards();
				System.out.println("Hand value: " + player.getScore());
				return;
			}
			else if(input == 'h')
			{
				hitPlayer();
				player.calculateTotalScore();
			}
			else
				System.out.println("Sorry, that is not a valid command. Please try again.");
			} while(input != 's' && input != 'h');
			
		}
		
		System.out.println("\nOh no, you busted! Better luck next time.");
		System.out.println("\nYour final hand: ");
		player.displayCards();
		System.out.println("Hand value: " + player.calculateTotalScore());
		
	} // end playerGameLoop
	
	
	/**
	 * The game loop for the dealer, who will reveal his hidden card
	 * If they have a score of 17 or more, they will stand, otherwise they will hit themselves
	 */
	public void dealerGameLoop()
	{
		
		// dealer reveals his hidden card
		System.out.println("\nThe dealer's full hand is: ");
		dealer.displayCards();
		
		// dealer will hit until they have hand worth at least 17 or they bust
		while(dealer.getScore() < 17)
		{
			hitDealer();
			System.out.println("\nThe dealer hits himself.");
			System.out.println("The dealer's new hand is: ");
			dealer.displayCards();
			dealer.calculateTotalScore();
			
			// print if dealer has busted
			if(dealer.getScore() > 21)
			{
				System.out.println("\nThe dealer has busted!");
				System.out.println("The dealer's final hand score is: " + dealer.getScore());
				return;
			}
			else	
				System.out.println("The dealer's hand score is: " + dealer.calculateTotalScore());
		}
		
		System.out.println("The dealer stands.");
		System.out.println("The dealer's final hand score is: " + dealer.getScore());
				
	} // end dealerGameLoop
	
	
	
	/**
	 * Hits the player with another card
	 */
	public void hitPlayer()
	{
		player.addCard(deck.deal());
	}
	
	
	/**
	 * Hits the dealer with another card
	 */
	public void hitDealer()
	{
		dealer.addCard(deck.deal());
	}
	
	
	/**
	 * Prepares for a new game
	 */
	public void resetValues()
	{
		player.clearHand();
		dealer.clearHand();
		deck = new Deck();
		pot = 0;
	}
	
	
	/**
	 * Determines the winner for the current hand
	 * @return
	 */
	public String determineWinner()
	{
		if(player.getScore() > 21)
			winner = "Dealer";
		else if(player.getScore() < 21 && dealer.getScore() > 21)
			winner = "Player";
		else if(player.getScore() < dealer.getScore())
			winner = "Dealer";
		else if(player.getScore() > dealer.getScore())
			winner = "Player";
		else
			winner = "Tie";
		return winner;
	}
	
	
	/**
	 * Places a bet, adding to the pot and subtracting from the player's hand
	 * @param bet
	 * @return
	 */
	public void placeBet(double bet)
	{	
		pot = (bet * 2);
		player.subtractMoney(bet);
		dealer.subtractMoney(bet);		
	}
	
	
} // end class
