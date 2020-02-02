import java.util.Scanner;
/*	
 * The CollectionManger class tests the CardCollection class and the 
 *   BaseballCard class by asking the user for commands and asking for 
 *   user input in order to perform methods within the other two classes.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #1
 * CSE 214 Rectitation #1
 * */
public class CollectionManager {
	/*	
	 * The main driver method that tests the CardCollection and BaseballCard 
	 *   classes. The method acquires user input and asks the user for commands. 
	 *   After which methods from either class are used to perform tasks
	 *   
	 * Precondition: none
	 * Postcondition: Prints out the program and asks user for tasks
	 * */
	public static void main(String[] args) throws Exception{
		Scanner stdin = new Scanner(System.in);
		CardCollection collectionA = new CardCollection();
		CardCollection collectionB = new CardCollection();
		
		char userAction;
		char collection;
		int position;
		do
		{
			System.out.println("Main menu:\r\n\n" +
			  "A) Add Card\r\n" + 
			  "C) Copy\r\n" + 
			  "E) Update price\r\n" + 
			  "G) Get Card\r\n" + 
			  "L) Locate Card\r\n" + 
			  "N) Update name\r\n" + 
			  "P) Print All Cards\r\n" + 
			  "R) Remove Card\r\n" + 
			  "S) Size\r\n" + 
			  "T) Trade\r\n" + 
			  "V) Value of collections\r\n" + 
			  "Q) Quit\r\n\n");
			System.out.print("Select an operation: ");
			userAction = stdin.next().charAt(0);
			if(userAction == 'A' || userAction == 'a')
			{
			    try 
				{
					System.out.print("Enter the collection: ");
					collection = stdin.next().charAt(0);
					System.out.print("Enter the position: ");
					position = stdin.nextInt();
					if(collection == 'A')
					{
						BaseballCard cardA = userInputCard();
						collectionA.addCard(cardA, position);
						System.out.println("Added " + cardA.getName() + ", " + 
						  cardA.getManufacturer() + " " + cardA.getYear() +
						  ", " + cardA.getSizeX() + "x" + cardA.getSizeY() + 
						  ", $" + cardA.getPrice() + " at position " +
						  position + " of collection " + collection);
						System.out.println();
					}
					else if(collection == 'B')
					{
						BaseballCard cardB = userInputCard();
						collectionB.addCard(cardB, position);
						System.out.println("Added " + cardB.getName() + ", " + 
						  cardB.getManufacturer() + " " + cardB.getYear() +
						  ", " + cardB.getSizeX() + "x" + cardB.getSizeY() + 
						  ", $" + cardB.getPrice() + " at position " +
						  position + " of collection " + collection);
						System.out.println();
					}
					else
					{
						System.out.println("The collection does not exist");
						System.out.println();
					}
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
					System.out.println();
				}
				catch(FullCollectionException fce)
				{
					System.out.println("The collection is full");
					System.out.println();
				}
			}
			else if(userAction == 'C'|| userAction == 'c')
			{
				try 
				{
					CardCollection fromCollection = null, toCollection = null;
					System.out.print("Enter the collection to copy from: ");
					char collectionFromName = stdin.next().charAt(0);
					if(collectionFromName == 'A')
					{
						fromCollection = collectionA;
					}
					else if(collectionFromName =='B')
					{
						fromCollection = collectionB;
					}
					System.out.print("Enter the position of the card to copy: ");
					position = stdin.nextInt();
					System.out.print("Enter the collection to copy to: ");
					char collectionToName = stdin.next().charAt(0);
					if(collectionToName == 'A')
					{
						toCollection  = collectionA;
					}
					else if(collectionToName == 'B')
					{
						toCollection = collectionB;
					}
					toCollection.addCard(fromCollection.getCard(position));
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
					System.out.println();
				}
				catch(FullCollectionException fce)
				{
					System.out.println("The collection is full");
					System.out.println();
				}
			}
			else if(userAction == 'E'|| userAction == 'e') {
				try 
				{
					CardCollection collectionPrice = null;
					System.out.print("Enter the collection: ");
					char collectionNamePrice = stdin.next().charAt(0); 
					if(collectionNamePrice == 'A')
					{
						collectionPrice = collectionA;
					}
					else if(collectionNamePrice =='B')
					{
						collectionPrice = collectionB;
					}
					System.out.print("Enter the position: ");
					position = stdin.nextInt();
					System.out.print("Enter the new price: ");
					double price = stdin.nextDouble();
					collectionPrice.getCard(position).setPrice(price);
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
					System.out.println();
				}
			}
			else if(userAction == 'G'|| userAction == 'g')
			{
				try
				{
					System.out.print("Enter the collection: ");
					char collectionOutputName = stdin.next().charAt(0);
					System.out.print("Enter the position: ");
					position = stdin.nextInt();
					CardCollection collectionOutput = null;
					if(collectionOutputName == 'A')
					{
						collectionOutput = collectionA;
					}
					else if(collectionOutputName =='B')
					{
						collectionOutput = collectionB;
					}
					String output = "#  Name                 Manufacturer  Year  Price   "
					  + "Size\r\n";
					output += "-- ----                 ------------  ----  -----   ----\r\n";
					output += String.format("%-3d%-21s%-14s%-6d%-8.2f%dx%d\n", position, 
					  collectionOutput.getCard(position).getName(), 
					  collectionOutput.getCard(position).getManufacturer(), 
					  collectionOutput.getCard(position).getYear(),
					  collectionOutput.getCard(position).getPrice(),
					  collectionOutput.getCard(position).getSizeX(), 
					  collectionOutput.getCard(position).getSizeY());
					System.out.println(output);
					System.out.println();
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
					System.out.println();
				}
			}
			else if(userAction == 'L'|| userAction == 'l')
			{
				BaseballCard target = userInputCard();
				boolean valueA = collectionA.exists(target);
				boolean valueB = collectionB.exists(target);
				if(valueA)
				{
					System.out.print("The card is in collection A.");
				}
				else
				{
					System.out.print(" The card is not in collection A.");
				}
				if(valueB)
				{
					System.out.println("The card is in collection B.");
					System.out.println();
				}
				else
				{
					System.out.println(" The card is not in collection B.");
					System.out.println();
				}
			}
			else if(userAction == 'N'|| userAction == 'n')
			{
				try 
				{
					System.out.print("Enter the collection: ");
					char nameCollectionChangename = stdin.next().charAt(0);
					System.out.print("Enter the position: ");
					position = stdin.nextInt();
					stdin.nextLine();
					System.out.print("Enter the new name: ");
					String changeName = stdin.nextLine();
					CardCollection collectionChangeName = null;
					if(nameCollectionChangename == 'A')
					{
						collectionChangeName = collectionA;
					}
					else if(nameCollectionChangename =='B')
					{
						collectionChangeName = collectionB;
					}
					collectionChangeName.getCard(position).setName(changeName);
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
					System.out.println();
				}
			}
			else if(userAction == 'P'|| userAction == 'p')
			{
				collectionA.printAllCards();
				collectionB.printAllCards();
			}
			else if(userAction == 'R' || userAction == 'r')
			{
				try 
				{
					System.out.print("Enter the collection to remove from: ");
					char collectionRemoveCardName = stdin.next().charAt(0);
					CardCollection collectionRemoveCard = null;
					if(collectionRemoveCardName == 'A')
					{
						collectionRemoveCard = collectionA;
					}
					else if(collectionRemoveCardName =='B')
					{
						collectionRemoveCard = collectionB;
					}
					System.out.print("Enter the position to remove: ");
					position = stdin.nextInt();
					BaseballCard temporary = collectionRemoveCard.getCard(position);
					collectionRemoveCard.removeCard(position);
					System.out.println("Removed " + temporary.getName() + ", " + 
					  temporary.getManufacturer() + " " + temporary.getYear() +
					  ", " + temporary.getSizeX() + "x" + temporary.getSizeY() + ", $" +
					  temporary.getPrice() + " of collection " + collectionRemoveCardName);
					System.out.println();
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
					System.out.println();
				}
			}
			else if(userAction == 'S'|| userAction == 's')
			{
				if(collectionA.size() == 1)
				{
					System.out.print("Collection A has 1 card.");
				}
				else
				{
					System.out.print("Collection A has " + collectionA.size() + 
					  " cards.");
				}
				if(collectionB.size() == 1)
				{
					System.out.println("Collection A has 1 card.");
					System.out.println();
				}
				else
				{
					System.out.println("Collection A has " + collectionB.size() + 
				      " cards.");
					System.out.println();
				}
			}
			else if(userAction == 'T'|| userAction == 't')
			{
				try 
				{
					System.out.print("Enter the position of the card to trade " + 
				      "from collection A: ");
					int positionA = stdin.nextInt();
					System.out.print("Enter the position of the card to trade " + 
					  "from collection B: ");
					int positionB = stdin.nextInt();
					collectionA.trade(collectionB, positionA, positionB);
					System.out.println("Traded " + 
					  collectionB.getCard(positionB).getName() + ", " + 
					  collectionB.getCard(positionB).getManufacturer() + " " + 
					  collectionB.getCard(positionB).getYear() +
					  ", " + collectionB.getCard(positionB).getSizeX() + "x" + 
					  collectionB.getCard(positionB).getSizeY() + ", $" + 
				      collectionB.getCard(positionB).getPrice() + " for " + 
					  collectionA.getCard(positionA).getName() + ", " + 
					  collectionA.getCard(positionA).getManufacturer() + " " + 
					  collectionA.getCard(positionA).getYear() +
					  ", " + collectionA.getCard(positionA).getSizeX() + "x" + 
				      collectionA.getCard(positionA).getSizeY() + ", $" + 
					  collectionA.getCard(positionA).getPrice());
					System.out.println();
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println(iae.getMessage());
					System.out.println();
				}
			}
			else if(userAction == 'V'|| userAction == 'v')
			{
				double collectionAValue = 0;
				double collectionBvalue = 0;
				for(int i = 1; i <= collectionA.size(); i++)
				{
					collectionAValue += collectionA.getCard(i).getPrice();
				}
				for(int i = 1; i <= collectionB.size(); i++)
				{
					collectionBvalue += collectionB.getCard(i).getPrice();
				}
				System.out.println("The total value of collection A is " + 
				  collectionAValue + ". The total value of collection B is " + 
				  collectionBvalue + ".");
				System.out.println();
			}
		}while(!(userAction == 'Q' || userAction == 'q'));
	}	
	/*	
	 * Returns a baseball card created by acquiring values from the user
	 *   
	 * Precondition: none
	 * Postcondition: a Baseball card object with specified values  
	 *     
	 * @return
	 *   A baseball card object with specified values
	 * */
	public static BaseballCard userInputCard() {
		int[] imageSize = new int[2];
		Scanner stdin = new Scanner(System.in);
		System.out.print("Enter the name: ");
		String name = stdin.nextLine();
		System.out.print("Enter the manufacturer: ");
		String manufacturer = stdin.nextLine();
		System.out.print("Enter the year: ");
		int year = stdin.nextInt();
		System.out.print("Enter the size: ");
		int xSize = stdin.nextInt();
		int ySize = stdin.nextInt();
		imageSize[0] = xSize;
		imageSize[1] = ySize;
		System.out.print("Enter the price: ");
		double price = stdin.nextDouble();
		BaseballCard tempCard = new BaseballCard(name, manufacturer, year, 
		  imageSize, price);
		return tempCard;	
	}
}