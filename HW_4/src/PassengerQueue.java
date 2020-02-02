import java.util.LinkedList;
/*	
 * The PassengerQueue class instantiates a queue of
 *   Passenger objects. It represents a bus stop. This class 
 *   extends the linked list class. There are methods which allow 
 *   to add passengers to the end of the passenger queue, and to 
 *   remove from the beginning of the queue. There 
 * 	 are also methods to check the size (in terms of passengers 
 * 	 and individuals) of the queue, whether or not the its empty, 
 * 	 and to see the attributes of the next passenger.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #4
 * CSE 214 Rectitation #1
 * */
public class PassengerQueue extends LinkedList<Passenger>
{
	/* 
	 * The number of passengers and individuals in the passenger 
	 *   queue. The wait time of the passengers.
	 * */
	private int numberOfPassengers;
	private int waitTime;
	private int numberOfGroups;
	/*	
	 * Creates a PassengerQueue object (bus stop). 
	 * 
	 * @param numberOfPassengers
	 *   the number of passengers in the bus stop.
	 * @param waitTime
	 *   the waiting time of the passengers in the bus stop.
	 * */
	public PassengerQueue(int numberOfPassengers, int waitTime) 
	{
		this.numberOfPassengers = numberOfPassengers;
		this.waitTime = waitTime;
	}
	/*	
	 * Constructs a generic PassengerQueue object.
	 * */
	public PassengerQueue() {}
	/* 
	 * Adds a new passenger to the end of the queue.
	 * 
	 * Precondition: p must be of type Card
	 * Postcondition: none
	 * 
	 * @param p
	 *   a passenger object
	 *  */
	public void enqueue(Passenger p)
	{
		numberOfPassengers += p.getSize();
		numberOfGroups++;
		this.addLast(p);
	}
	/* 
	 * Removes and returns the passenger object at the end of
	 *   the queue.
	 * 
	 * Precondition: there is at least one passenger in the queue.
	 * Postcondition: Returns the passenger object that was removed.
	 * 
	 * @return
	 *   the passenger object that was removed
	 *  */
	public Passenger dequeue() 
	{
		Passenger temp = this.pop();
		numberOfPassengers -= temp.getSize();
		numberOfGroups--;
		return temp;
	}
	/*
	 * Removes the Passenger in the position given.
	 * 	
	 * Precondition: position must be an integer 
	 *   and should not exceed numberOfGroups
	 *   and should be greater than zero.
	 * Postcondition: none
	 * 
	 * @param position
	 *   The Passenger in the position given.
	 * */
	public void removeAtPosition(int position)
	{
		if(position < this.numberOfGroups) 
		{
			Passenger temp = this.get(position);
			numberOfPassengers -= temp.getSize();
			numberOfGroups--;
			this.remove(position);
		}
	}
	/* 
	 * Returns the passenger object at the end of the queue.
	 * 
	 * Precondition: there is at least one passenger object 
	 *   in the queue.
	 * Postcondition: Returns the passenger object at the end 
	 *   of the queue.
	 * 
	 * @return
	 *   the passenger object at the end of the queue.
	 *  */
	public Passenger peek() 
	{
		return (Passenger)super.peek();
	}
	/*	
	 * Returns the number of passengers in the PassengerQueue 
	 *   object.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of passengers in 
	 *   the PassengerQueue object.
	 * 
	 * @return 
	 *   Returns the number of passengers in the PassengerQueue 
	 *   object.
	 * */
	public int size()
	{
		return this.numberOfPassengers;
	}
	/* 
	 * Returns whether or not the queue is empty
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not the queue is empty
	 * 
	 * @return
	 *   true or false depending on whether or not the queue 
	 *   is empty
	 *  */
	public boolean isEmpty()
	{
		return super.isEmpty();
	}
	/*	
	 * Returns the number of individuals in the PassengerQueue object.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of individuals 
	 *   in the PassengerQueue object.
	 * 
	 * @return 
	 *   Returns the number of individuals in the PassengerQueue object.
	 * */
	public int getNumberOfPassengers() 
	{
		return this.numberOfPassengers;
	}
	/*
	 * Assigns a new number of individuals to the PassengerQueue 
	 *   object.
	 * 	
	 * Precondition: numberOfPassengers must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param numberOfPassengers
	 *   The number of individuals in the PassengerQueue object.
	 * */
	public void setNumberOfPassengers(int numberOfPassengers)
	{
		this.numberOfPassengers = numberOfPassengers;
	}
	/*	
	 * Returns the wait time of the PassengerQueue object.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the wait time of the PassengerQueue object.
	 * 
	 * @return 
	 *   Returns the wait time of the PassengerQueue object.
	 * */
	public int getWaitTime()
	{
		return this.waitTime;
	}
	/*
	 * Assigns a wait time to the PassengerQueue object.
	 * 	
	 * Precondition: waitTime must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param waitTime
	 *   The wait time of the PassengerQueue object.
	 * */
	public void setWaitTime(int waitTime)
	{
		this.waitTime = waitTime;
	}
	/*	
	 * Returns the number of passenger objects in the PassengerQueue object.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of passenger objects
	 *   in the PassengerQueue object.
	 * 
	 * @return 
	 *   Returns the number of passenger objects in the PassengerQueue object.
	 * */
	public int getNumberOfGroups() 
	{
		return this.numberOfGroups;
	}
	/*
	 * Assigns a new number of passenger objects to the 
	 *   PassengerQueue object.
	 * 	
	 * Precondition: numberOfGroups must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param numberOfGroups
	 *   The number of passenger objects in the PassengerQueue 
	 *   object.
	 * */
	public void setNumberOfGroups(int numberOfGroups)
	{
		this.numberOfGroups = numberOfGroups;
	}
}