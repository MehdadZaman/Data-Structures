/*	
 * The CardCollection class instantiates an array of baseball cards 
 *   which have attributes that are accessible through methods in the 
 *   BaseballCard class. A collection can have a maximum of 100 baseball 
 *   cards. There methods which allow the addition and removal of baseball 
 *   cards. There also methods that allows the access of the attributes of the 
 *   cards. Methods also allow to see the whole collection and to trade cards 
 *   with other collections. 
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #1
 * CSE 214 Rectitation #1
 * */
public class CardCollection {
	/*	
	 * Attributes representing the number of deck the cards has, the maximum 
	 *   number of cards the collection can hold, as well as the collection 
	 *   itself
	 * */
	final int MAX_CARDS = 100;
	private int cardCount = 0;
	BaseballCard[] collection = new BaseballCard[MAX_CARDS];
	/*	
	 * Creates a baseball card collection object
	 * */
	public CardCollection() {}
	/*	
	 * Returns the number of baseball cards in the collection
	 *
	 * Precondition: none
	 * Postcondition: Returns the the number of baseball cards in the collection.
	 *
	 * @return
	 *   the number of baseball cards in the collection
	 *   */
	public int size() {
		return cardCount;
	}
	/*	
	 * Adds a baseball card to the collection at an assigned position
	 * 
	 * Precondition: position is an integer and doesn't exceed the maximum 
	 *   space of type int. It is also in the collection range 
	 *   (>=1 and <=size() and size() <= MAX_CARDS). newCard is of type 
	 *   BaseballCard
	 * Postcondition: none
	 * 
	 * @param newCard
	 *   a baseball card object
	 * @param position
	 *   a position in the CardCollection object
	 *   
	 * @throws IllegalArgumentException
	 *   Indicates that the position entered by the user is out of the range
	 * @throws FullCollectionException
	 *   Indicates that collection has no more room for additional baseball 
	 *   cards
	 * */
	public void addCard(BaseballCard newCard, int position) throws Exception{
		if(!(position >= 1 && position <= this.size() + 1))
		{
			throw new IllegalArgumentException("Position out of range");
		}
		else if(this.size() > MAX_CARDS - 1)
		{
			throw new FullCollectionException(); 
		}
		else 
		{
			for(int i = this.size() - 1; i > position - 1; i--)
			{
				collection[i + 1] = (BaseballCard)collection[i].clone();
			}
			collection[position - 1] = (BaseballCard)newCard.clone();
			cardCount++;
		}
	}
	/*
	 * Adds a baseball card to the collection without an assigned position
	 *   which moves it at the end of the collection
	 *  
	 * Precondition: (size() <= MAX_CARDS). newCard is of type BaseballCard
	 * Postcondition: none
	 *  
	 * @param newCard
	 *   a baseball card object
	 *   
	 * @throws FullCollectionException
	 *   Indicates that collection has no more room for additional baseball 
	 *   cards
	 * 	*/
	public void addCard(BaseballCard newCard) throws Exception
	{
		this.addCard(newCard, this.size() + 1);
	}
	/*
	 * Removes a card at the position that was provided by the user 
	 *   in the collection	
	 *   
	 * Precondition: position is an integer and doesn't exceed the maximum 
	 *   space of type int. It is also in the collection range (>=1 and <size()).
	 * Postcondition: none
	 *   
	 * @param position
	 *   The position in the Collection of the baseBall card that will be removed
	 * 
	 * @throws IllegalArgumentException
	 *   Indicates that the position entered by the user is out of the range
	 *   */
	public void removeCard(int position) throws Exception
	{
		if(!(position >= 1 && position <= this.size()))
		{
			throw new IllegalArgumentException("Position out of range");
		}
		else
		{
			for(int i = position; i < this.size(); i++)
			{
				collection[i - 1] = (BaseballCard)collection[i].clone();
			}
			this.cardCount--;
		}
	}
	/*	
	 * Returns a baseball card (containing the values of its attributes)
	 *   at a position provided by the user.
	 *   
	 * Precondition: position is an integer and doesn't exceed the maximum 
	 *   space of type int. It is also in the collection range (>=1 and <size()).
	 * Postcondition: none  
	 *   
	 * @param position
	 *   The position in the collection of the desired baseball card
	 *   
	 * @return
	 *   The baseball card object at the position in the collection 
	 *   provided by the user
	 *     
	 * @throws IllegalArgumentException
	 *   Indicates that the position entered by the user is out of the range
	 * */
	public BaseballCard getCard(int position) throws Exception
	{
		if(!(position >= 1 && position <= this.size()))
		{
			throw new IllegalArgumentException("Position out of range");
		}
		else
		{
			return collection[position - 1];
		}
	}
	/*	
	 * Trades a card from one baseball card in a given position with
	 *   another baseball card in the current collection based on
	 *   another given position
	 *   
	 * Precondition: other is of type CardCollection,and myPosition along with 
	 *   theirPosition are integers and don't exceed the maximum space of type int.
	 *   They are also in the collection range (>=1 and <size()).
	 * Postcondition: none
	 *   
	 * @param other
	 *   the collection containing the desired baseball card
	 * @param myPosition
	 *   the position of the baseball card in the current collection 
	 *   that will be traded
	 * @param theirPosition
	 *   the position of the baseball card in the other collection 
	 *   that will be traded to the current collection
	 *   
	 * @throws IllegalArgumentException
	 *   Indicates that the position entered by the user is out of the range
	 *  
	 * */
	public void trade(CardCollection other, int myPosition, int theirPosition)
	  throws Exception{
		if(!(myPosition >= 1 && myPosition <= this.size()))
		{
			throw new IllegalArgumentException("Position out of range");
		}
		else if(!(theirPosition >= 1 && theirPosition <= other.size()))
		{
			throw new IllegalArgumentException("Position out of range");
		}
		else 
		{
			BaseballCard temporaryCard = 
			  (BaseballCard)this.collection[myPosition - 1].clone();
			this.collection[myPosition - 1] = 
			  (BaseballCard)other.collection[theirPosition - 1].clone();
			other.collection[theirPosition - 1] = 
			  (BaseballCard)temporaryCard.clone();
		}
	}
	/*	
	 * Checks whether or not a baseball card is in the collection
	 * 
	 * @param card
	 *   a Baseball card object that will be checked for whether 
	 *   or not it is in the collection
	 *   
	 * Precondition: card is of type BaseballCard
	 * Postcondition: Returns true or false depending on whether or not a baseball 
	 *   card is in the collection
	 *   
	 * @return
	 *   true or false depending on whether or not a baseball 
	 *   card is in the collection
	 * */
	public boolean exists(BaseballCard card) {
		boolean returnValue = false;
		for(int i = 0; i < this.size(); i++)
		{
			if(card.equals(this.collection[i]))
			{
				returnValue = true;
			}
		}
		return returnValue;
	}
	/*	
	 * Prints out all of the attributes of all of the baseball cards in the 
	 *   collection as well as their positions in the collection
	 * 
	 * Precondition: none
	 * Postcondition: Prints all of the attributes of 
	 *   all of the baseball cards in the collection as well as 
	 *   their positions in the collection
	 * */
	public void printAllCards() {
		System.out.println(this.toString());
	}
	/*	
	 * Returns a string of all of the attributes of all of the 
	 *   baseball cards in the collection as well as their positions 
	 *   in the collection
	 *   
	 * Precondition: none
	 * Postcondition: Returns a string of all of the attributes of 
	 *   all of the baseball cards in the collection as well as 
	 *   their positions in the collection
	 *   
	 * @return
	 *   A string of all of the attributes of all of the baseball cards in the 
	 *   collection as well as their positions in the collection
	 * */
	public String toString()
	{
		String output = "#  Name                 Manufacturer  Year  Price   " +
	      "Size\r\n";
		output += "-- ----                 ------------  ----  -----   ----\r\n";
		for(int i = 0; i < this.size(); i++)
		{
			output += String.format("%-3d%-21s%-14s%-6d%-8.2f%dx%d\n", (i + 1), 
	          this.collection[i].getName(), this.collection[i].getManufacturer(), 
	          this.collection[i].getYear(), this.collection[i].getPrice(), 
			  this.collection[i].getSizeX(), this.collection[i].getSizeY());
		}
		return output;
	}	
}