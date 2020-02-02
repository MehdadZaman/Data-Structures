/*	
 * The Bus class instantiates a queue of Passenger objects. It 
 *   represents a bus. This class extends the linked list class 
 *   and the PassengerQueue. There are methods which allow 
 *   to add passengers to the end of the passenger queue, and to 
 *   remove passenger objects from the bus. There 
 * 	 are also methods to check what stop the bus is at, and its state
 * 	 (location and whether or not it is active).
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #4
 * CSE 214 Rectitation #1
 * */
public class Bus extends PassengerQueue{
	/* 
	 * The capacity, route, next stop of the bus, and other attributes 
	 *   that represent its state.
	 * */
	private static int capacity;
	private String route;
	private int nextStop;
	private int toNextStop;
	private int timeToRest;
	boolean isResting;
	boolean isAtFirstStop;
	boolean isAtLastStop;
	/* 
	 * The number of individuals and passengers in the bus.
	 * */
	private int numberOfGroups;
	private int numberOfPassengers;
	/*	
	 * Creates a bus object.
	 * 
	 * @param route
	 *   the route of the bus.
	 * @param nextStop
	 *   the next stop of the bus.
	 * @param toNextStop
	 *   the time until the next stop of the bus.
	 * @param timeToRest
	 *   the time of resting of the bus.
	 * @param capacity
	 *   the capacity of the bus.
	 * @param isResting
	 *   whether or not the bus is resting.
	 * @param isAtFirstStop
	 *   whether or not the bus is at the first stop.
	 * @param isAtLastStop
	 *   whether or not the bus is at the last stop.
	 * */
	public Bus(String route, int nextStop, int toNextStop, int timeToRest,
	  int capacity, 
	  boolean isResting, boolean isAtFirstStop, boolean isAtLastStop)
	{
		this.route = route;
		this.nextStop = nextStop;
		this.toNextStop = toNextStop;
		this.timeToRest = timeToRest;
		Bus.capacity = capacity;
		this.isResting = isResting;
		this.isAtFirstStop = isAtFirstStop;
		this.isAtLastStop = isAtLastStop;
	}
	/*	
	 * Constructs a generic bus object.
	 * */
	public Bus()
	{}
	/*
	 * Removes all the passenger objects if their destination
	 *   corresponds with the current stop.
	 * 	
	 * Precondition: destination must be an integer and should not 
	 *   exceed the maximum size of an int. It should represent
	 *   an actual bus stop.
	 * Postcondition: none
	 * 
	 * @param destination
	 *   The stop that the bus is currently in.
	 *   
	 * @return
	 *   the number of individuals that were dropped off at the
	 *   current bus stop.
	 * */
	public int unload(int destination)
	{
		int numberOfRemovedPassengers = 0;
		for(int i = 0; i < this.getNumberOfGroups(); i++)
		{
			if(this.get(i).getDestination() == destination)
			{
				numberOfRemovedPassengers += this.get(i).getSize();
				this.setNumberOfPassengers(this.size() - this.get(i).getSize());
				this.remove(this.get(i));
				this.setNumberOfGroups(this.getNumberOfGroups() - 1);
			}
		}
		return numberOfRemovedPassengers;
	}	
	/*	
	 * Returns the capacity of the bus.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the capacity of the bus.
	 * 
	 * @return 
	 *   Returns the capacity of the bus.
	 * */
	public static int getCapacity()
	{
		return Bus.capacity;
	}
	/*
	 * Assigns a new capacity to the bus object.
	 * 	
	 * Precondition: capacity must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param capacity
	 *   The capacity of the bus object.
	 * */
	public static void setCapacity(int capacity)
	{
		Bus.capacity = capacity;
	}
	/*	
	 * Returns the route of the bus.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the route of the bus.
	 * 
	 * @return 
	 *   Returns the route of the bus.
	 * */
	public String getRoute()
	{
		return this.route;
	}
	/*
	 * Assigns a route to the bus object.
	 * 	
	 * Precondition: route must be a String.
	 * Postcondition: none
	 * 
	 * @param route
	 *   The route of the bus object.
	 * */
	public void setRoute(String route)
	{
		this.route = route;
	}
	/*	
	 * Returns the next stop of the bus.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the next stop of the bus.
	 * 
	 * @return 
	 *   Returns the next stop of the bus.
	 * */
	public int getNextStop() 
	{
		return this.nextStop;
	}
	/*
	 * Assigns a next stop to the bus object.
	 * 	
	 * Precondition: nextStop must be an integer and should not 
	 *   exceed the maximum size of an int. It should represent
	 *   an actual bus stop.
	 * Postcondition: none
	 * 
	 * @param nextStop
	 *   The next stop of the bus object.
	 * */
	public void setNextStop(int nextStop)
	{
		this.nextStop = nextStop;
	}
	/*	
	 * Returns the time until the next stop of the bus.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the time until the next 
	 *   stop of the bus.
	 * 
	 * @return 
	 *   Returns the time until the next stop of the bus.
	 * */ 
	public int getToNextStop() 
	{
		return this.toNextStop;
	}
	/*
	 * Assigns a new time until the next stop to the bus object.
	 * 	
	 * Precondition: toNextStop must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param toNextStop
	 *   The time until the next stop of the bus object.
	 * */
	public void setToNextStop(int toNextStop)
	{
		this.toNextStop = toNextStop;
	}
	/*	
	 * Returns the time of resting of the bus.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the time of resting 
	 *   of the bus.
	 * 
	 * @return 
	 *   Returns the time of resting of the bus.
	 * */
	public int getTimeToRest() 
	{
		return this.timeToRest;
	}
	/*
	 * Assigns a new time to rest to the bus object.
	 * 	
	 * Precondition: timeToRest must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param timeToRest
	 *   The time to rest of the bus object.
	 * */
	public void setTimeToRest(int timeToRest)
	{
		this.timeToRest = timeToRest;
	}
	/*	
	 * Returns whether or not the bus is resting.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not the bus is resting.
	 * 
	 * @return 
	 *   Returns whether or not the bus is resting.
	 * */
	public boolean getIsResting() 
	{
		return this.isResting;
	}
	/*
	 * Sets whether or not the bus is resting.
	 * 	
	 * Precondition: isResting must be either true or false
	 * Postcondition: none
	 * 
	 * @param isResting
	 *   Whether or not the bus is resting.
	 * */
	public void setIsResting(boolean isResting)
	{
		this.isResting = isResting;
	}
	/*	
	 * Returns whether or not the bus is at the first stop.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not the bus is at 
	 *  the first stop.
	 * 
	 * @return 
	 *   Returns whether or not the bus is at the first stop.
	 * */
	public boolean getIsAtFirstStop()
	{
		return this.isAtFirstStop;
	}
	/*
	 * Sets whether or not the bus is at the first stop.
	 * 	
	 * Precondition: isAtFirstStop must be either true or false
	 * Postcondition: none
	 * 
	 * @param isAtFirstStop
	 *   Whether or not the bus is at the first stop.
	 * */
	public void setIsAtFirstStop(boolean isAtFirstStop)
	{
		this.isAtFirstStop = isAtFirstStop;
	}
	/*	
	 * Returns whether or not the bus is at the last stop.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not the bus is at 
	 *  the last stop.
	 * 
	 * @return 
	 *   Returns whether or not the bus is at the last stop.
	 * */
	public boolean getIsAtLastStop()
	{
		return this.isAtLastStop;
	}
	/*
	 * Sets whether or not the bus is at the last stop.
	 * 	
	 * Precondition: isAtLastStop must be either true or false
	 * Postcondition: none
	 * 
	 * @param isAtLastStop
	 *   Whether or not the bus is at the last stop.
	 * */
	public void setIsAtLastStop(boolean isAtLastStop)
	{
		this.isAtLastStop = isAtLastStop;
	}
}