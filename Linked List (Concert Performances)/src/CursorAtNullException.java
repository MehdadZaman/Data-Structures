/*	
 * The CursorAtNullException class is a custom exception 
 *   class which is used for when the cursor is not pointing
 *   at a node.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #2
 * CSE 214 Rectitation #1
 * */
public class CursorAtNullException extends Exception{
	/*
	 * Constructs an CursorAtNullException object with a message
	 * 
	 * @param ex_message
	 *   A message associated with CursorAtNullException object
	 * 	*/
	public CursorAtNullException(String message)
	{
		super(message);
	}
	/*	
	 * Constructs an CursorAtNullException object without a message
	 * */
	public CursorAtNullException() 
	{
		this("Cursor At Null Exception");
	}
}