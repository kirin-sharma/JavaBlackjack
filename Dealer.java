
public class Dealer extends Player
{
	protected Card hiddenCard; // represents the dealer's card that is face-down at the beginning of game
	
	
	/**
	 * Default constructor
	 */
	public Dealer() 
	{
		super();
		setMoney(1000);
	} // end default constructor
	
	
	@Override
	public void displayCards()
	{
		System.out.println(hiddenCard.getCardName());
		super.displayCards();
	}
	
	
	@Override
	public int calculateTotalScore()
	{
		score = 0;
		if(hiddenCard.getCardName() != CardNames.ACE)
			score = hiddenCard.getCardValue();
		
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
	} // end calculateTotalScore
	
	
	@Override
	public void clearHand()
	{
		super.clearHand();
		hiddenCard = null;
	}
	
	
	/**
	 * Sets the value of hiddenCard
	 * @param c the card to set
	 */
	public void setHiddenCard(Card c)
	{
		hiddenCard = c;
	}
	
	
	/**
	 * Gets the hidden card
	 * @return the hidden card
	 */
	public Card getHiddenCard()
	{
		return hiddenCard;
	}
	
} // end class
