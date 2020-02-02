/*	
 * The FullCollectionException class is used for when additions
 *  to a card collection are trying to be made but the collection 
 *  is full (at its maximum capacity.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #1
 * CSE 214 Rectitation #1
 * */
public class FullCollectionException  extends Exception {
	/*
	 * Constructs a FullCollectionException object with a message
	 * 
	 * @param ex_message
	 *   A message associated with FullCollectionException object
	 * 	*/
	public FullCollectionException (String ex_message)
	{
		super(ex_message);
	}
	/*	
	 * Constructs a FullCollectionException object without a message
	 * */
	public FullCollectionException ()
	{
		this("Full Collection Exception ");
	}
}