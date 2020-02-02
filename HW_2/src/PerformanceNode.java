/*	
 * The PerformanceNode class creates a PerformanceNode 
 *   object, which has attributes that are generic specifications 
 *   of a PerformanceNode. There are methods which allow the access 
 *   and manipulation of the attributes. There are also attributes 
 *   and methods which give access to the next and previous 
 *   links in the list.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #2
 * CSE 214 Rectitation #1
 * */
public class PerformanceNode {
	/* 
	 * The links to the next and previous PerformanceNodes in the list
	 * */
	private PerformanceNode next;
	private PerformanceNode prev;
	/* 
	 * The different attributes of the PerformanceNode object
	 *  */
	private String performanceName;
	private String leadPerformer;
	private int totalParticipants;
	private int performanceDuration;
	private int performanceStartTime;
	/* 
	 * Constructs a new PerformanceNode object with specified attributes
	 * 
	 * @param performanceName
	 *   the name of the performance
	 * @param leadPerformer
	 *   the name of the lead performer of the performance
	 * @param totalParticipants
	 *   the total participants at the performance
	 * @param performanceDuration
	 *   the length of the performance
	 * @param performanceStartTime
	 *   the starting time of the performance
	 * */
	public PerformanceNode(String performanceName,String leadPerformer,
	  int totalParticipants, int performanceDuration,int performanceStartTime) 
	{
		this.performanceName = performanceName;
		this.leadPerformer = leadPerformer;
		this.totalParticipants = totalParticipants;
		this.performanceDuration = performanceDuration;
		this.performanceStartTime = performanceStartTime;
	}
	/*	
	 * Constructs a new PerformanceNode without assigned attributes
	 * */
	public PerformanceNode() 
	{
		this("no name performance", "no lead performer", 0, 0, 0);
	}
	/*
	 * Assigns a name to the PerformanceNode.
	 * 
	 * Precondition: none
	 * Postcondition: none
	 * 	
	 * @param performanceName
	 *   The name that will be used for the PerformanceNode.
	 *  */
	public void setPerformanceName(String performanceName)
	{
		this.performanceName = performanceName;
	}
	/*	
	 * Returns the name of the performance
	 * 
	 * Precondition: none
	 * Postcondition: Returns the name of the performance.
	 * 
	 * @return 
	 *   Returns the name of the performance.
	 * */
	public String getPerformanceName()
	{
		return this.performanceName;
	}
	/*
	 * Assigns a name to the lead performer of the PerformanceNode.
	 * 
	 * Precondition: none
	 * Postcondition: none
	 * 	
	 * @param leadPerformer
	 *   The name that will be used for the lead performer of the 
	 *   PerformanceNode.
	 *  */
	public void setLeadPerformer(String leadPerformer)
	{
		this.leadPerformer = leadPerformer;
	}
	/*	
	 * Returns the name of the performance lead performer 
	 * 
	 * Precondition: none
	 * Postcondition: Returns the name of the performance lead performer.
	 * 
	 * @return 
	 *   Returns the name of the performance lead performer.
	 * */
	public String getLeadPerformer()
	{
		return this.leadPerformer;
	}
	/*
	 * Assigns a value to the total number of participants at the performance
	 * 	
	 * Precondition: totalParticipants must be an integer and should not 
	 *   exceed the maximum size of an int
	 * Postcondition: none
	 * 
	 * @param totalParticipants
	 *   The total participants at the performance
	 * */
	public void setTotalParticipants(int totalParticipants)
	{
		this.totalParticipants = totalParticipants;
	}
	/*	
	 * Returns the total number of attendees at the performance
	 * 
	 * Precondition: none
	 * Postcondition: Returns the total number of attendees at the performance.
	 * 
	 * @return 
	 *   Returns the name of the total number of attendees at the performance.
	 * */
	public int getTotalParticipants()
	{
		return this.totalParticipants;
	}
	/*
	 * Assigns a value to the duration of the performance
	 * 	
	 * Precondition: performanceDuration must be an integer and should not 
	 *   exceed the maximum size of an int
	 * Postcondition: none
	 * 
	 * @param performanceDuration
	 *   The duration of the performance
	 * */
	public void setPerformanceDuration(int performanceDuration)
	{
		this.performanceDuration = performanceDuration;
	}
	/*	
	 * Returns the duration of the performance
	 * 
	 * Precondition: none
	 * Postcondition: Returns the duration of the performance.
	 * 
	 * @return 
	 *   Returns the duration of the performance.
	 * */
	public int getPerformanceDuration()
	{
		return this.performanceDuration;
	}
	/*
	 * Assigns a value to the starting time of the performance
	 * 	
	 * Precondition: performanceStartTime must be an integer and should not 
	 *   exceed the maximum size of an int
	 * Postcondition: none
	 * 
	 * @param performanceStartTime
	 *   The starting time of the performance
	 * */
	public void setPerformanceStartTime(int performanceStartTime)
	{
		this.performanceStartTime = performanceStartTime;
	}
	/*	
	 * Returns the starting time of the performance
	 * 
	 * Precondition: none
	 * Postcondition: Returns the starting time of the performance.
	 * 
	 * @return 
	 *   Returns the starting time of the performance.
	 * */
	public int getPerformanceStartTime()
	{
		return this.performanceStartTime;
	}
	/*
	 * Assigns an address to the next PerformanceNode
	 * 	
	 * Precondition: next must be of type PerformanceNode
	 * Postcondition: none
	 * 
	 * @param next
	 *   The address to the next PerformanceNode
	 * */
	public void setNext(PerformanceNode next)
	{
		this.next = next;
	}
	/*	
	 * Returns the address to the next PerformanceNode
	 * 
	 * Precondition: none
	 * Postcondition: Returns the address to the next PerformanceNode.
	 * 
	 * @return 
	 *   Returns the address to the next PerformanceNode.
	 * */
	public PerformanceNode getNext()
	{
		return this.next;
	}
	/*
	 * Assigns an address to the previous PerformanceNode
	 * 	
	 * Precondition: prev must be of type PerformanceNode
	 * Postcondition: none
	 * 
	 * @param prev
	 *   The address to the previous PerformanceNode
	 * */
	public void setPrev(PerformanceNode prev)
	{
		this.prev = prev;
	}
	/*	
	 * Returns the address to the previous PerformanceNode
	 * 
	 * Precondition: none
	 * Postcondition: Returns the address to the previous PerformanceNode.
	 * 
	 * @return 
	 *   Returns the address to the previous PerformanceNode.
	 * */
	public PerformanceNode getPrev()
	{
		return this.prev;
	}
	/*	
	 * Returns a string of the attributes of the PerformanceNode
	 * 
	 * Precondition: none
	 * Postcondition: Returns a string of the attributes of the PerformanceNode.
	 * 
	 * @return 
	 *   Returns a string of the attributes of the PerformanceNode.
	 * */
	public String toString()
	{
		return "Performance: " + performanceName +
		  ", performer: " + leadPerformer +
		  ", total particpants: " + totalParticipants + 
		  ", duration: " + performanceDuration +
		  ", start time:" + performanceStartTime;
	}
}