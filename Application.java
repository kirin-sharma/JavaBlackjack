import java.util.Scanner;

public class Application 
{
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args)
	{		
	
		Game myGame = new Game();
		
		// Gets the amount of money the user is playing with during the given game
		do
		{
			try
			{
				System.out.println("How much money in chips would you like today?");
				myGame.player.setMoney(scan.nextDouble());
				break;
			}
			catch(Exception e)
			{
				System.out.println("That is not a valid input");
				scan.nextLine();
			}
		} while(true);
		scan.nextLine();
		
		while(true)
		{
			// Ask player how much money they would like to bet on this game
			double bet = 0;
			do
			{
				try
				{
					System.out.println("How much money would you like to bet on this hand?");
					bet = scan.nextDouble();
					if(bet <= myGame.player.getMoney())
						break;
					else
						System.out.println("You cannot bet more money than you have!");
				}
				catch(Exception e)
				{
					System.out.println("That is not a valid bet amount.");
					scan.nextLine();
				}
			} while(bet > myGame.player.getMoney() || bet == 0);
			scan.nextLine();
			
			// Initialize the pot in the game object
			myGame.placeBet(bet);
			
			// Opening deal of the game
			myGame.openingDeal();
			
			// runs the player game loop
			myGame.playerGameLoop(scan);
			
			// If player busts while being served, prints that winner is the dealer
			if(myGame.determineWinner().equals("Dealer"))
			{
				System.out.println("\nThe winner is the " + myGame.determineWinner());
				
				// Return the pot to the dealer
				myGame.dealer.addMoney(myGame.pot);
			}
			else
			{
				// runs the dealer game loop, then determines the winner based on the results
				myGame.dealerGameLoop();
				
				// return the pot to either the dealer or the player
				if(myGame.determineWinner().equals("Tie"))
				{
					System.out.println("This game is a tie! Bets are pushed.");
					myGame.dealer.addMoney(myGame.pot / 2);
					myGame.player.addMoney(myGame.pot / 2);
				}
				else if(myGame.determineWinner().equals("Player"))
				{
					System.out.println("\nThe winner is the " + myGame.determineWinner());
					myGame.player.addMoney(myGame.pot);
				}
				else
				{
					System.out.println("\nThe winner is the " + myGame.determineWinner());
					myGame.dealer.addMoney(myGame.pot);
				}
			}
			
			// if player has no more money, then return
			// otherwise, print how much money the player now has
			if(myGame.player.getMoney() == 0)
			{
				System.out.println("\nYou are out of money!");
				return;
			}
			else
				System.out.println("\nYou have $" + myGame.player.getMoney() + " left.");
			
			System.out.println("\nWould you like to play again?");
			System.out.print("Type y for yes, type n for no: ");
			char input;
			input = scan.nextLine().charAt(0);
			
			// Checks if the user would like to continue playing
			while(input != 'y' && input != 'n')
			{
				System.out.println("Sorry, that is not a valid input. Please try again.");
				System.out.print("Type y for yes, type n for no: ");
				input = scan.nextLine().charAt(0);					
			} 
			if(input == 'n')
			{
				scan.close();
				return;
			}
			
			// Prepares the game object for the next hand
			myGame.resetValues();
			System.out.println();
			
		} // end while
		
		
	} // end main
	
} // end TestApplication
