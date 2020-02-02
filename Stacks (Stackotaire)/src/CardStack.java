/*	
 * The CardStack class instantiates a stack of
 *   Card objects. This class contains the top of the 
 *   stack of cards. There are methods which allow to push/pop 
 *   a card, and to move through stack. There are also methods
 *   to check the size of the stack, whether or not the stack is 
 *   empty, and to print a stack a certain stack depending on its
 *   type
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #3
 * CSE 214 Rectitation #1
 * */
public class CardStack{
	/* 
	 * The links to the top Card of the stack
	 * */
	private Card top;
	/* 
	 * The type of stack and the number of cards
	 *   in the stack
	 * */
	private char type;
	private int numberOfCards;
	/*	
	 * Creates a card stack from an assigned stack type.
	 * 
	 * @param type
	 *   the type of stack in stackotaire.
	 * */
	public CardStack(char type)
	{
		this.type = type;
	}
	/*	
	 * Constructs a generic Cardstack that is of type waste.
	 * */
	public CardStack()
	{
		this('w');
	}
	/* 
	 * Adds a newCard to the top of the card stack.
	 * 
	 * Precondition: newCard must of type Card
	 * Postcondition: none
	 * 
	 * @param newCard
	 *   a newCard object
	 *  */
	public void push(Card newCard)
	{
		newCard.setNext(top);
		top = newCard;
		this.numberOfCards++;
	}
	/* 
	 * Removes and return the card at the top of the stack
	 * 
	 * Precondition: there is at least one card in the stack
	 * Postcondition: Returns the card that was removed
	 * 
	 * @return
	 *   the card that was removed
	 *  */
	public Card pop() throws EmptyStackException
	{
		if(this.isEmpty())
		{
			throw new EmptyStackException();
		}
		Card temp = top;
		top = top.getNext();
		this.numberOfCards--;
		return temp;
	}
	/* 
	 * Returns the card at the top of the stack
	 * 
	 * Precondition: there is at least one card in the stack
	 * Postcondition: Returns the card at the top of the stack
	 * 
	 * @return
	 *   the card at the top of the stack
	 *  */
	public Card peek() throws EmptyStackException
	{
		return top;
	}
	/* 
	 * Returns whether or not the stack is empty
	 * 
	 * Precondition: none
	 * Postcondition: Returns true or false depending on
	 *   whether or not the stack is empty
	 * 
	 * @return
	 *   true or false depending on whether or not the stack 
	 *   is empty
	 *  */
	public boolean isEmpty()
	{
		return this.size() == 0;
	}
	/* 
	 * Returns the number of cards in the stack
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of cards in the stack
	 * 
	 * @return
	 *   Returns the number of cards in the stack
	 *  */
	public int size()
	{
		return this.numberOfCards;
	}
	/* 
	 * Returns the card at a given position
	 * 
	 * Precondition: (position > 0) and (position <= size)
	 * Postcondition: Returns the card at the given position
	 * 
	 * @param position
	 *   the position of the desired card in the stack
	 * 
	 * @return
	 *   the card at the given position
	 *  */
	public Card get(int position)
	{
		Card tempNode = top;
		for(int i = 0; i < position; i++)
		{
			tempNode = tempNode.getNext();
		}
		return tempNode;
	}
	/* 
	 * Prints the stack cards depending on the type of 
	 *   stack it is.
	 * 
	 * Precondition: type is a character and one of 
	 *   the types of stacks.
	 * Postcondition: Prints the stack cards depending 
	 *   on the type of stack it is.
	 * 
	 * @param type
	 *   the type of the current stack
	 *  */
	public void printStack(char type) throws EmptyStackException
	{
		if(this.type == 's')
		{
			if(this.isEmpty())
			{
				System.out.print("[  ]");
			}
			else
			{
				peek().setFaceUp(false);
				System.out.print(peek().toString());
			}
		}
		else if(this.type == 'w')
		{	
			if(this.isEmpty())
			{
				System.out.print("[  ]");
			}
			else 
			{	
				peek().setFaceUp(true);
				System.out.print(this.peek().toString());
			}
		}
		else if(this.type == 'f')
		{
			if(this.isEmpty())
			{
				System.out.print("[  ]");
			}
			else
			{
				this.peek().setFaceUp(true);
				System.out.print(this.peek().toString());
			}
		}
		else if(this.type == 't')
		{
			if(this.isEmpty())
			{
				System.out.print("[  ]");
			}
			else
			{
				for(int i = this.size() - 1; i >= 0; i--)
				{
					System.out.print(this.get(i).toString());
				}
			}
		}
	}
}