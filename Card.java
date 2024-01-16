
public class Card 
{

	private CardNames cardName;
	private int cardValue;
	
	public Card(CardNames name)
	{
		cardName = name;
		// switch statement
		switch(cardName)
		{
		
		case ACE:
			cardValue = 1;
			break;
		case TWO:
			cardValue = 2;
			break;
		case THREE:
			cardValue = 3;
			break;
		case FOUR:
			cardValue=4;
			break;
		case FIVE:
			cardValue=5;
			break;
		case SIX:
			cardValue=6;
			break;
		case SEVEN:
			cardValue=7;
			break;
		case EIGHT:
			cardValue=8;
			break;
		case NINE:
			cardValue=9;
			break;
		case TEN:
			cardValue=10;
			break;
		case JACK:
			cardValue=10;
			break;
		case QUEEN:
			cardValue=10;
			break;
		case KING:
			cardValue=10;
			break;
		default:
			System.out.println("Something went wrong");
			break;
		
		} // end switch
	}
	
	public void setCardValue(int value)
	{
		cardValue = value;
	}
	
	public int getCardValue()
	{
		return cardValue;
	}
	
	public CardNames getCardName()
	{
		return cardName;
	}
	
} // end class
