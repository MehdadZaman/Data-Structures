/*	
 * The Card class creates a Card 
 *   object, which has attributes that are generic specifications 
 *   of a specific card in a game of stackotaire. There are methods
 *   which allow the access and manipulation of the attributes. 
 *   There are also attributes and methods which give access to the
 *   next card in a stack.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #3
 * CSE 214 Rectitation #1
 * */
public class Card {
	/* 
	 * The link to the next card in the stack
	 * */
	private Card next;
	/* 
	 * The different attributes of each Card in the stack
	 *  */
	private int cardValue;
	private int cardSuit;
	private boolean isFaceUp;
	/* 
	 * The arrays of suits and values that can be accessed
	 *   through indexes of each card
	 * */
	private String[] values = {" ","A","2","3","4","5","6","7","8","9"
	  ,"10","J","Q","K"};
	private char[] suits  = {' ', '\u2666', '\u2663','\u2665', '\u2660'};
	/* 
	 * Constructs a new Card with specified attributes
	 * 
	 * @param cardValue
	 *   the value of the card associated with the index of the values array
	 * @param cardSuit
	 *   the suit of the card associated with the index of the suit array
	 * @param isFaceUp
	 *   whether the card is faced up or not
	 * */
	public Card(int cardValue, int cardSuit, boolean isFaceUp)
	{
		this.cardValue = cardValue;
		this.cardSuit = cardSuit;
		this.isFaceUp = isFaceUp;
	}
	/*	
	 * Constructs a new Card without assigned attributes
	 * */
	public Card() {}
	/*	
	 * Returns the index of the value of the card
	 * 
	 * Precondition: none
	 * Postcondition: Returns the index of the value of the card.
	 * 
	 * @return 
	 *   Returns the index of the value of the card.
	 * */
	public int getCardValue() 
	{
		return this.cardValue;
	}
	/*
	 * Assigns a value to the index of the card's value
	 * 	
	 * Precondition: cardValue must be an integer, should not 
	 *   exceed the maximum size of an int, and should be an
	 *   index within the bounds of the size of the array
	 * Postcondition: none
	 * 
	 * @param cardValue
	 *   The index of the value of the card
	 * */
	public void setCardValue(int cardValue)
	{
		this.cardSuit = cardValue;
	}
	/*	
	 * Returns the index of the suit of the card.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the index of the suit of the card.
	 * 
	 * @return 
	 *   Returns the index of the suit of the card.
	 * */
	public int getCardSuit() 
	{
		return this.cardSuit;
	}
	/*
	 * Assigns a value to the index of the card's suit
	 * 	
	 * Precondition: cardSuit must be an integer, should not 
	 *   exceed the maximum size of an int, and should be an
	 *   index within the bounds of the size of the array
	 * Postcondition: none
	 * 
	 * @param cardSuit
	 *   The index of the suit of the card
	 * */
	public void setCardSuit(int cardSuit)
	{
		this.cardSuit = cardSuit;
	}
	/*	
	 * Returns whether or not the card is facing upwards.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not the card is facing upwards.
	 * 
	 * @return 
	 *   Returns whether or not the card is facing upwards.
	 * */
	public boolean isFaceUp()
	{
		return this.isFaceUp;
	}
	/*
	 * Sets up whether or not the card is facing up.
	 * 	
	 * Precondition: faceUp must be either true or false
	 * Postcondition: none
	 * 
	 * @param faceUp
	 *   Whether or not the card is facing up.
	 * */
	public void setFaceUp(boolean faceUp)
	{
		this.isFaceUp = faceUp;
	}
	/*	
	 * Returns whether or not the card is red (black).
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not the card is red (black).
	 * 
	 * @return 
	 *   Returns whether or not the card is red (black).
	 * */
	public boolean isRed()
	{
		return (this.cardSuit % 2 == 1);
	}
	/*	
	 * Returns a string of the attributes (value and suit) of the card.
	 * 
	 * Precondition: none
	 * Postcondition: Returns a string of the attributes
	 *   (value and suit) of the card.
	 * 
	 * @return 
	 *   Returns a string of the attributes (value and suit) of the card.
	 * */
	public String toString()
	{
		if(!this.isFaceUp)
		{
			return "[XX]";
		}
		else
		{
			return "[" + this.values[this.cardValue] + 
			  this.suits[this.cardSuit] + "]";
		}
	}
	/*	
	 * Returns the link of the next card in the stack.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the link of the next card in the stack.
	 * 
	 * @return 
	 *   Returns the link of the next card in the stack.
	 * */
	public Card getNext()
	{
		return this.next;
	}
	/*
	 * Assigns a new Card that links to the current card.
	 * 	
	 * Precondition: next must be an object of type Card
	 * Postcondition: none
	 * 
	 * @param next
	 *   A new Card that links to the current card.
	 * */
	public void setNext(Card next)
	{
		this.next = next;
	}
}