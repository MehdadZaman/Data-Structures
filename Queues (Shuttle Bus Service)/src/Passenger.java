/*	
 * The Passenger class represents a passenger object, which has
 *   attributes that represent the size, destination, 
 *   and arrival time of a group of passengers. There are methods
 *   which allow the access and manipulation of these attributes. 
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #4
 * CSE 214 Rectitation #1
 * */
public class Passenger {
	/* 
	 * The different attributes that represent the size, destination
	 *   of the group of passengers.
	 *  */
	private int size;
	private int destination;
	private int arrivalTime;
	/* 
	 * Constructs a new Passenger object with specified attributes
	 * 
	 * @param size
	 *   The number of individuals in the passenger object.
	 * @param destination
	 *   the number bus stop representing the destination 
	 *   of the passenger. 
	 * @param arrivalTime
	 *   The arrival time of the passenger(s).
	 * */
	public Passenger(int size, int destination, int arrivalTime)
	{
		this.size = size;
		this.destination = destination;
		this.arrivalTime = arrivalTime;
	}
	/*	
	 * Constructs a new passenger object without assigned attributes
	 * */
	public Passenger()
	{}
	/*	
	 * Returns the number of passengers in the passenger object.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of passengers 
	 *   in the passenger object.
	 * 
	 * @return 
	 *   Returns the number of passengers in the passenger object.
	 * */
	public int getSize() 
	{
		return this.size;
	}
	/*
	 * Assigns a new number of individuals to the passenger object
	 * 	
	 * Precondition: size must be an integer, should not 
	 *   exceed the maximum size of an int, and should be an
	 *   within the bounds of the user's desired number of
	 *   minimum passenger size and maximum passenger size.
	 * Postcondition: none
	 * 
	 * @param size
	 *   The number of individuals in the passenger object.
	 * */
	public void setSize(int size)
	{
		this.size = size;
	}
	/*	
	 * Returns the destination of the passenger(s).
	 * 
	 * Precondition: none
	 * Postcondition: Returns the destination of the passenger(s).
	 * 
	 * @return 
	 *   Returns the destination of the passenger(s).
	 * */
	public int getDestination()
	{
		return this.destination;
	}
	/*
	 * Assigns a destination to the passenger(s).
	 * 	
	 * Precondition: destination must be an integer and should not 
	 *   exceed the maximum size of an int. It should represent
	 *   an actual bus stop.
	 * Postcondition: none
	 * 
	 * @param destination
	 *   The destination of the passenger(s).
	 * */
	public void setDestination(int destination)
	{
		this.destination = destination;
	}
	/*	
	 * Returns the arrival time of the passenger(s).
	 * 
	 * Precondition: none
	 * Postcondition: Returns the arrival time of the passenger(s).
	 * 
	 * @return 
	 *   Returns the arrival time of the passenger(s).
	 * */
	public int getArrivalTime() 
	{
		return this.arrivalTime;
	}
	/*
	 * Assigns an arrival time to the passenger(s).
	 * 	
	 * Precondition: arrivalTime must be an integer, should not 
	 *   exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param arrivalTime
	 *   The arrival time of the passenger(s).
	 * */
	public void setArrivalTime(int arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
}