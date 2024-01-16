
public class Player
{

	protected Card[] hand = new Card[6]; // the cards in the player's hand
	protected int totalCards = 0; 
	protected int numAces = 0; // the number of aces in the player's hand
	protected int score = 0; // the score of the player's current hand
	private double money; // the amount of money the player has
	
	
	/**
	 * Default constructor initializes the player's hand to null
	 */
	public Player()
	{		
		for(int i = 0; i < 6; i++)
		{
			hand[i] = null;
		}
	} // end default constructor
	
	
	/**
	 * Calculates the player's total score, factoring in whether the ace should be
	 * counted as a one or eleven per the rules of Blackjack
	 * @return
	 */
	public int calculateTotalScore()
	{
		score = 0;
		// Iterate through hand, calculate score w/o aces factored in
		for(int i = 0; i < totalCards; i++)
		{
			if(hand[i].getCardName() != CardNames.ACE)
				score += hand[i].getCardValue();
		}
		
		// Can't have 2 aces both be 11, so must make remaining aces equal to 1
		if(numAces > 1)
			score += (numAces - 1);
		
		if (numAces > 0)
		{
			// Check if counting ace as 11 or 1 will work better for player
			if(11 + score <= 21)
			{
				score += 11;
			}
			else
			{
				score += 1;
			}
		}
		return score;
	}
	
	
	/**
	 * Adds a card to the player's hand
	 * If the card added is an ace, it will acknowledge this and add to the number of aces
	 * @param card
	 */
	public void addCard(Card card)
	{
		if(card.getCardName() == CardNames.ACE)
			numAces++;
		
		hand[totalCards] = card;
		totalCards++;
	}
	
	
	/**
	 * Prints all of the player's cards to the screen
	 */
	public void displayCards()
	{
		for(int i = 0; i < totalCards; i++)
		{
			System.out.println(hand[i].getCardName());
		}
	}
	
	
	/**
	 * Clears the player's hand of cards, resets all other relevant values to 0
	 */
	public void clearHand()
	{
		for(int i = 0; i < totalCards; i++)
		{
			hand[i] = null;
		}
		
		numAces = 0;
		totalCards = 0;
		score = 0;
	}
	
	
	/**
	 * Setter for money
	 * @param m the amount of money to set
	 */
	public void setMoney(double m)
	{
		money = m;
	}
	
	
	/**
	 * Getter for money
	 * @return the amount of money the player has
	 */
	public double getMoney()
	{
		return money;
	}
	
	
	/**
	 * Adds money to the player
	 * @param m
	 */
	public void addMoney(double m)
	{
		money += m;
	}
	
	
	/**
	 * Subtracts money from the player's hand
	 * @param m
	 */
	public void subtractMoney(double m)
	{
		money -= m;
	}
	
	/**
	 * Getter for score
	 * @return the player's score
	 */
	public int getScore()
	{
		return score;
	}
	
} // end class
