/*	
 * The FullNodeException class is a custom exception 
 *   class which is thrown when the number of child links
 *   is more than three.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #5
 * CSE 214 Rectitation #1
 * */
public class FullNodeException extends Exception{
	/*
	 * Constructs a FullNodeException object with a message
	 * 
	 * @param message
	 *   A message associated with FullNodeException object
	 * 	*/
	public FullNodeException(String message)
	{
		super(message);
	}
	/*	
	 * Constructs a FullNodeException object without a message
	 * */
	public FullNodeException()
	{
		this("Number of children has exceeded maximum.");
	}
}