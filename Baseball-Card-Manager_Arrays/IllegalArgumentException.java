/*	
 * The IllegalArgumentException class is a custom exception 
 *   class which is used for when inputs are out of range
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #1
 * CSE 214 Rectitation #1
 * */
public class IllegalArgumentException extends Exception {
	/*
	 * Constructs an IllegalArgumentException object with a message
	 * 
	 * @param ex_message
	 *   A message associated with IllegalArgumentException object
	 * 	*/
	public IllegalArgumentException(String ex_message)
	{
		super(ex_message);
	}
	/*	
	 * Constructs an IllegalArgumentException object without a message
	 * */
	public IllegalArgumentException()
	{
		this("Illegal Argument Exception");
	}
}