import java.util.Scanner;
/*	
 * The Stackotaire class tests the CardStack class and the 
 *   Card class by asking the user for commands and asking for 
 *   user input in order to perform methods within the other two classes.
 *   This class contains several methods, including the main method, that
 *   allow the user to play and interact with the stackotaire game
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #3
 * CSE 214 Rectitation #1
 * */
public class Stackotaire {
	/*	
	 * The different stacks in the stackotaire game
	 * */
	public static CardStack[] tableauStacks = new CardStack[7];
	public static CardStack[] foundationStacks = new CardStack[4];
	public static CardStack stockStack = new CardStack('s');
	public static CardStack wasteStack = new CardStack('w');
	public static CardStack mainStack = new CardStack('t');
	/*	
	 * The main driver method that tests CardStack class and the 
	 *   Card class. The method acquires user input and asks the
	 *   user for commands about which actions to perform in the 
	 *   stackotaire game. This method then makes the moves in the 
	 *   game commanded by the user or displays if the move is illegal.
	 *   This method also uses the displayGame() method to print out the
	 *   game for the user.
	 *   
	 * Precondition: none
	 * Postcondition: Prints out the program and asks the user to make
	 *   moves within the game
	 * */
	public static void main(String[] args)
	{
		Scanner stdin = new Scanner(System.in);
		String userInput;
		initializeGame();
		displayGameBoard();
		boolean gameContinue = true;
		do
		{
			System.out.print("Enter a command: ");
			userInput = stdin.next();
			userInput = userInput.toLowerCase();
			switch(userInput) 
			{
				case "draw":
				{
					try 
					{
						if(!stockStack.isEmpty())
						{
							Card tempCard = stockStack.pop();
							tempCard.setFaceUp(true);
							wasteStack.push(tempCard);
						}
						else if(stockStack.isEmpty() && !(wasteStack.isEmpty()))
						{	
							while(!wasteStack.isEmpty())
							{
								Card temp = wasteStack.pop();
								temp.setFaceUp(false);
								stockStack.push(temp);
							}
							Card tempCard = stockStack.pop();
							tempCard.setFaceUp(true);
							wasteStack.push(tempCard);
						}
						else
						{
							System.out.println("Both the stock stacks"
							  + " and waste stacks are empty");
						}
					}
					catch(EmptyStackException ese)
					{
						System.out.println(ese.getMessage());
					}
					catch(NullPointerException npe)
					{
						System.out.println("Invalid move");
					}
					break;
				}
				case "restart":
				{
					System.out.print("Do you want to start a new game? (Y/N): ");
					char userRestart = stdin.next().charAt(0);
					if(userRestart == 'Y' || userRestart == 'y')
					{
						System.out.println("Sorry, you lose. Starting new game.");
						initializeGame();
					}
					break;
				}
				case "move":
				{
					String fromStackString = stdin.next();
					String toStackString = stdin.next();
					String fromStackChar = "" + fromStackString.charAt(1);
					String toStackChar = "" + toStackString.charAt(1);
					int fromStackNumber = Integer.parseInt(fromStackChar) - 1;
					int toStackNumber = Integer.parseInt(toStackChar) - 1;
					CardStack fromActualStack = null;
					CardStack toActualStack = null;
					if(fromStackString.charAt(0) == 'w' ||
					  fromStackString.charAt(0) == 'W')
					{
						fromActualStack = wasteStack;
					}
					else if(fromStackString.charAt(0) == 'f' ||
					  fromStackString.charAt(0) == 'F')
					{
						fromActualStack = foundationStacks[fromStackNumber];
					}
					else if(fromStackString.charAt(0) == 't' ||
					  fromStackString.charAt(0) == 'T')
					{
						fromActualStack = tableauStacks[fromStackNumber];
					}
					else
					{
						System.out.println("First stack does not exist");
					}
					if(toStackString.charAt(0) == 'f' ||
					  toStackString.charAt(0) == 'F')
					{
						toActualStack = foundationStacks[toStackNumber];
					}
					else if(toStackString.charAt(0) == 't' ||
					  toStackString.charAt(0) == 'T')
					{
						toActualStack = tableauStacks[toStackNumber];
					}
					else
					{
						System.out.println("Illegal move");
					}
					try 
					{
						if(fromStackString.charAt(0) == 'w' ||
						  fromStackString.charAt(0) == 'W')
						{
							if(toStackString.charAt(0) == 't' ||
							  toStackString.charAt(0) == 'T')
							{
								if(toActualStack.isEmpty() && 
								  fromActualStack.peek().getCardValue() != 13)
								{
									System.out.println("Illegal move");
								}
								else if(toActualStack.isEmpty() && 
								  fromActualStack.peek().getCardValue() == 13)
								{
									toActualStack.push(fromActualStack.pop());
								}	
								else if((toActualStack.peek().getCardValue()
								  == (fromActualStack.peek().getCardValue() + 1)) &&
								  (toActualStack.peek().isRed() 
								  != fromActualStack.peek().isRed()))
								{
									toActualStack.push(fromActualStack.pop());
								}
								else
								{
									System.out.println("Illegal move");
								}
							}
							else if(toStackString.charAt(0) == 'f' || 
							  toStackString.charAt(0) == 'F')
							{
								if(toActualStack.isEmpty() && 
								  fromActualStack.peek().getCardValue() != 1)
								{
									System.out.println("Illegal move");
								}
								else if(toActualStack.isEmpty() && 
								  fromActualStack.peek().getCardValue() == 1)
								{
									toActualStack.push(fromActualStack.pop());
								}
								else if((toActualStack.peek().getCardValue() 
								  == (fromActualStack.peek().getCardValue() - 1)) &&
								  (toActualStack.peek().getCardSuit() 
								  == fromActualStack.peek().getCardSuit()))
								{
									toActualStack.push(fromActualStack.pop());
								}
								else
								{
									System.out.println("Illegal move");
								}
							}
							else
							{
								System.out.println("Illegal move");
							}
						}
						else if((fromStackString.charAt(0) == 't' || 
						  fromStackString.charAt(0) == 'T') &&
						  (toStackString.charAt(0) == 'f' || 
						  toStackString.charAt(0) == 'F') )
						{
							if(toActualStack.isEmpty() && 
							  fromActualStack.peek().getCardValue() != 1)
							{
								System.out.println("Illegal move");
							}
							else if(toActualStack.isEmpty() && 
							  fromActualStack.peek().getCardValue() == 1)
							{
								toActualStack.push(fromActualStack.pop());
								if(!(fromActualStack.isEmpty()))
								{
									fromActualStack.peek().setFaceUp(true);
								}
							}
							else if(toActualStack.peek().getCardValue() 
							  == (fromActualStack.peek().getCardValue() - 1) &&
							  toActualStack.peek().getCardSuit() 
							  == fromActualStack.peek().getCardSuit())
							{
								toActualStack.push(fromActualStack.pop());
								if(!(fromActualStack.isEmpty()))
								{
									fromActualStack.peek().setFaceUp(true);
								}
							}
							else
							{
								System.out.println("Illegal move");
							}
						}
						else if((fromStackString.charAt(0) == 'f' 
						  || fromStackString.charAt(0) == 'F') 
						  && (toStackString.charAt(0) == 't' 
						  || toStackString.charAt(0) == 'T'))
						{
							if(toActualStack.isEmpty() && 
							  fromActualStack.peek().getCardValue() != 13)
							{
								System.out.println("Illegal move");
							}
							else if(toActualStack.isEmpty() && 
							  fromActualStack.peek().getCardValue() == 13)
							{
								toActualStack.push(fromActualStack.pop());
							}
							else if((toActualStack.peek().getCardValue() 
							  == (fromActualStack.peek().getCardValue() + 1)) 
							  && (toActualStack.peek().isRed() 
							  != fromActualStack.peek().isRed()))
							{
								toActualStack.push(fromActualStack.pop());
							}
							else
							{
								System.out.println("Illegal move");
							}
						}
						else
						{
							System.out.println("Illegal move");
						}
					}
					catch(EmptyStackException ese)
					{
						System.out.println(ese.getMessage());
					}
					catch(NullPointerException npe)
					{
						System.out.println("Invalid move");
					}
					break;
				}
				case "moven":
				{
					String fromStackString = stdin.next();
					String toStackString = stdin.next();
					String fromStackChar = "" + fromStackString.charAt(1);
					String toStackChar = "" + toStackString.charAt(1);
					int fromStackNumber = Integer.parseInt(fromStackChar) - 1;
					int toStackNumber = Integer.parseInt(toStackChar) - 1;
					CardStack fromActualStack = null;
					CardStack toActualStack = null;
					fromActualStack = tableauStacks[fromStackNumber];
					toActualStack = tableauStacks[toStackNumber];
					int numberOfCards = stdin.nextInt();		
					try
					{	
						Card tempCard = fromActualStack.get(numberOfCards - 1);
						if(toActualStack.isEmpty() && 
						  (tempCard.getCardValue() != 13))
						{
							System.out.println("Illegal move");
						}
						else if(toActualStack.isEmpty() && 
						  (tempCard.getCardValue() == 13))
						{
							Card[] tempStorage = new Card[numberOfCards];
							for(int i = 0; i < numberOfCards; i++)
							{
								tempStorage[i] = fromActualStack.pop();
							}
							for(int j = tempStorage.length - 1; j >= 0; j--)
							{
								toActualStack.push(tempStorage[j]);
							}
							if(!(fromActualStack.isEmpty()))
							{
								fromActualStack.peek().setFaceUp(true);
							}
						}
						else if((toActualStack.peek().getCardValue() 
						  == (tempCard.getCardValue() + 1)) 
						  && toActualStack.peek().isRed() != tempCard.isRed())
						{
							Card[] tempStorage = new Card[numberOfCards];
							for(int i = 0; i < numberOfCards; i++)
							{
								tempStorage[i] = fromActualStack.pop();
							}
							for(int j = tempStorage.length - 1; j >= 0; j--)
							{
								toActualStack.push(tempStorage[j]);
							}
							if(!(fromActualStack.isEmpty()))
							{
								fromActualStack.peek().setFaceUp(true);
							}
						}
						else
						{
							System.out.println("Illegal move");
						}
					}
					catch(EmptyStackException ese)
					{
						System.out.println(ese.getMessage());
					}
					catch(NullPointerException npe)
					{
						System.out.println("Invalid move");
					}
					break;
				}
				case "quit":
				{
					System.out.println("Do you want to quit? (Y/N):");
					char quit = stdin.next().charAt(0);
					if(quit == 'y' || quit == 'Y')
					{
						System.out.println("Sorry, you lose.");
						System.out.println();
						System.out.println("Program "
						  + "terminating...");
						System.out.println();
						gameContinue = false;
					}
					break;
				}
				default:
				{
					System.out.println();
					System.out.println("Invalid Argument");
				}
			}
			System.out.println();
			displayGameBoard();
			if(checkWinningBoard())
			{
				System.out.println("You have won!");
				gameContinue = false;
			}
		}while(gameContinue);
	}
	/* 
	 * Resets and/or starts the card game
	 * 
	 * Precondition: none
	 * Postcondition: Resets and/or starts the card game
	 *  */
	public static void initializeGame()
	{
		for(int i = 0; i < tableauStacks.length; i++)
		{
			tableauStacks[i] = new CardStack('t');
		}
		for(int i = 0; i < foundationStacks.length; i++)
		{
			foundationStacks[i] = new CardStack('f');
		}
		stockStack = new CardStack('s');
		wasteStack = new CardStack('w');
		mainStack = new CardStack('s');
		for(int i = 1; i < 14; i++)
		{
			for(int j = 1; j < 5; j++)
			{
				mainStack.push(new Card(i, j, false));
			}
		}
		Card[] tempShuffler = new Card[mainStack.size()];
		try
		{
			for(int i = 0; i < tempShuffler.length; i++)
			{
				tempShuffler[i] = mainStack.pop();
			}
		}
		catch(EmptyStackException ese)
		{
			System.out.println(ese.getMessage());
		}
		for(int i = 0; i < tempShuffler.length; i++)
		{
			int rand = (int)(Math.random() * tempShuffler.length);
			Card temp = tempShuffler[i];
			tempShuffler[i] = tempShuffler[rand];
			tempShuffler[rand] = temp;
		}
		for(int i = 0; i < tempShuffler.length; i++)
		{
			mainStack.push(tempShuffler[i]);
		}
		try 
		{
			for(int i = 0; i < tableauStacks.length; i++)
			{
				for(int j = 0; j <= i; j++)
				{
					tableauStacks[i].push(mainStack.pop());
				}
				tableauStacks[i].peek().setFaceUp(true);
			}
			while(!mainStack.isEmpty())
			{
				stockStack.push(mainStack.pop());
			}
		}
		catch(EmptyStackException ese)
		{
			System.out.println(ese.getMessage());
		}
	}
	/* 
	 * Checks whether or not the user has won by checking
	 *   whether or not all the cards are facing up
	 * 
	 * Precondition: none
	 * Postcondition: none
	 *  */
	public static boolean checkWinningBoard()
	{
		for(int i = 0; i < tableauStacks.length; i++)
		{
			for(int j = 0; j < tableauStacks[i].size(); j++)
			{
				if(!(tableauStacks[i].get(j).isFaceUp()))
					return false;
			}
		}
		for(int i = 0; i < foundationStacks.length; i++)
		{
			for(int j = 0; j < foundationStacks[i].size(); j++)
			{
				if(!(foundationStacks[i].get(j).isFaceUp()))
				{
					return false;
				}
			}
		}
		for(int i = 0; i < stockStack.size(); i++)
		{
			if(!(stockStack.get(i).isFaceUp()))
			{
				return false;
			}
		}
		for(int i = 0; i < wasteStack.size(); i++)
		{
			if(!(wasteStack.get(i).isFaceUp()))
			{
				return false;
			}
		}
		return true;
	}
	/* 
	 * Prints/displays the game and all of the different stacks
	 * 
	 * Precondition: none
	 * Postcondition: Prints/displays the game and all 
	 *   of the different stacks
	 *  */
	public static void displayGameBoard()
	{
		try 
		{
			for(int i = 0; i < foundationStacks.length; i++)
			{
				if(foundationStacks[i].isEmpty())
				{
					System.out.print("[F"+ (i + 1) + "]");
				}
				else
				{
					foundationStacks[i].printStack('f');
				}
			}
			System.out.print("      ");
			System.out.print("W1");
			wasteStack.printStack('w');
			System.out.print("      ");
			stockStack.printStack('s');
			System.out.print(stockStack.size());
			System.out.println();
			System.out.println();
			for(int i = tableauStacks.length - 1; i >= 0; i--)
			{
				System.out.print("T" + (i + 1));
				tableauStacks[i].printStack('t');
				System.out.println();
			}
		}
		catch(EmptyStackException ese)
		{
			System.out.println(ese.getMessage());
		}
		System.out.println();
	}
}