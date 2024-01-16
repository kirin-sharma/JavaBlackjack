import java.util.Stack;
import java.util.ArrayList;

public class Deck 
{

	protected Stack<Card> deck = new Stack<Card>(); // the stack of shuffled cards
	
	
	/**
	 * Initializes a deck, first by creating all possible cards, then shuffling them randomly
	 * into the deck stack
	 */
	public Deck()
	{
		// Initialize all cards that will be contained in the deck stack eventually
		ArrayList<Card> unshuffledDeck = new ArrayList<Card>();
		for(CardNames name : CardNames.values())
		{
			for(int i = 0; i < 4; i++)
			{
				unshuffledDeck.add(new Card(name));
			}
		}
		
		// Shuffle Deck
		int index;
		while(unshuffledDeck.size() > 0)
		{
			index = (int) (Math.random() * unshuffledDeck.size());
			deck.push(unshuffledDeck.remove(index));
		}
		
	} // end default constructor

	
	/**
	 * Returns the card on the top of the deck
	 * @return top card
	 */
	public Card deal()
	{
		return deck.pop();
	} // end deal
	
	
} // end class
