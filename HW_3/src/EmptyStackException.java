/*	
 * The EmptyStackException class is a custom exception 
 *   class which is thrown when the current stack is empty.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #3
 * CSE 214 Rectitation #1
 * */
public class EmptyStackException extends Exception{
	/*
	 * Constructs an EmptyStackException object with a message
	 * 
	 * @param message
	 *   A message associated with EmptyStackException object
	 * 	*/
	public EmptyStackException(String message)
	{
		super(message);
	}
	/*	
	 * Constructs an EmptyStackException object without a message
	 * */
	public EmptyStackException()
	{
		this("The stack is empty");
	}
}
