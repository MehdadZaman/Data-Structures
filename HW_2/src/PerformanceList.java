/*	
 * The PerformanceList class instantiates a link list of
 *   PerformanceNode objects. This class contains the head and tail
 *   elements of the link list. There are methods which allow the addition/ 
 *   removal of PerformanceNodes, and to move through the link list. 
 *   There are also methods that allow the access of the attributes of 
 *   the PerformanceNodes and to see all of the PerformanceNodes in the 
 *   link list.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #2
 * CSE 214 Rectitation #1
 * */
public class PerformanceList {
	/* 
	 * The links to the first, last, and current PerformanceNodes in 
	 *   the linked list
	 * */
	private PerformanceNode head;
	private PerformanceNode tail;
	private PerformanceNode cursor;
	/* 
	 * The number of PerformanceNodes in the link list.
	 * */
	private int numberOfPerformances = 0;
	/*	
	 * Creates a PerformanceList object
	 * */
	public PerformanceList() {} 
	/* 
	 * Adds a new performance at the end of the PerformanceList.
	 * 
	 * Precondition: newPerformance must be of type PerformanceNode
	 * Postcondition: none
	 * 
	 * @param newPerformance
	 *   a PerformanceNode object
	 *  */
	public void addToEnd(PerformanceNode newPerformance)
	{
		if(head == null)
		{
			head = newPerformance;
			tail = newPerformance;
			cursor = newPerformance;
			numberOfPerformances++;
		}
		else
		{
			tail.setNext(newPerformance);
			newPerformance.setPrev(tail);
			cursor = newPerformance;
			tail = newPerformance;
			numberOfPerformances++;
			newPerformance.setPerformanceStartTime(
			  newPerformance.getPrev().getPerformanceStartTime() + 
			  newPerformance.getPrev().getPerformanceDuration());
		}
	}
	/* 
	 * Adds a new performance after the current PerformanceNode.
	 * 
	 * Precondition: newPerformance must be of type PerformanceNode
	 * Postcondition: none
	 * 
	 * @param newPerformance
	 *   a PerformanceNode object
	 *  */
	public void addAfterCurrent(PerformanceNode newPerformance)
	{
		if(cursor == null)
		{
			this.addToEnd(newPerformance);
			numberOfPerformances++;
		}
		else if(cursor.getNext() != null)
		{
			newPerformance.setNext(cursor.getNext());
			newPerformance.setPrev(cursor);
			cursor.getNext().setPrev(newPerformance);
			cursor.setNext(newPerformance);
			cursor = newPerformance;
			PerformanceNode tempCursor = cursor;
			while(tempCursor != null)
			{
				tempCursor.setPerformanceStartTime(
				  tempCursor.getPrev().getPerformanceStartTime() + 
				  tempCursor.getPrev().getPerformanceDuration());
				tempCursor = tempCursor.getNext();
			}
			numberOfPerformances++;
		}
		else
		{
			this.addToEnd(newPerformance);
			numberOfPerformances++;
		}
	}
	/* 
	 * Removes the current PerformanceNode.
	 * 
	 * Precondition: there is at least one PerformanceNode in the list
	 * Postcondition: Returns true or false whether or not the 
	 *   PerformanceNode was removed
	 * 
	 * @return
	 *   true or false whether or not the PerformanceNode was removed
	 *  */
	public boolean removeCurrentNode()
	{
		if(cursor == null)
		{
			return false;
		}
		else if(cursor.getNext() != null && cursor != head)
		{
			System.out.println(cursor.getPerformanceName() + " has been removed.");
			int removedDuration = cursor.getPerformanceDuration();
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			cursor = cursor.getNext();
			PerformanceNode tempCursor = cursor;
			while(tempCursor != null)
			{
				tempCursor.setPerformanceStartTime(tempCursor.getPerformanceStartTime()
				  - removedDuration);
				tempCursor = tempCursor.getNext();
			}
			numberOfPerformances--;
			return true;
		}
		else if(cursor == head)
		{
			if(head == tail)
			{
				System.out.println(cursor.getPerformanceName() + " has been removed.");
				head = null;
				cursor = null;
				tail = null;
				numberOfPerformances--;
				return true;
			}
			else
			{
				System.out.println(cursor.getPerformanceName() + " has been removed.");
				int removedDuration = cursor.getPerformanceDuration();
				cursor.getNext().setPrev(null);
				head = cursor.getNext();
				cursor = head;
				PerformanceNode tempCursor = cursor;
				while(tempCursor != null)
				{
					tempCursor.setPerformanceStartTime(tempCursor.getPerformanceStartTime()
					  - removedDuration);
					tempCursor = tempCursor.getNext();
				}
				numberOfPerformances--;
				return true;
			}
		}
		else if(cursor == tail)
		{
			System.out.println(cursor.getPerformanceName() + " has been removed.");
			tail = cursor.getPrev();
			tail.setNext(null);
			cursor = tail;
			numberOfPerformances--;
			return true;
		}
		else
		{
			return false;
		}
	}
	/* 
	 * Prints all of the attributes of the current PerformanceNode
	 * 
	 * Precondition: there is at least one PerformanceNode in the list
	 * Postcondition: Returns the data of the current PerformanceNode
	 *   and mentioning that there isn't one if there is none
	 *  */
	public void displayCurrentPerformance()
	{
		if(cursor != null)
		{
			PerformanceNode tempCursor = head;
			int nodeCounter = 1;
			while(tempCursor != cursor)
			{
				nodeCounter++;
				tempCursor = tempCursor.getNext();
			}
			String output = "Current No. Performance Name          Lead Performer Name" +
			  "       Participants Duration Start Time\n";
			output += "-----------------------------------------------------------------"
			  + "-------------------------------\n";
			output += "      * ";
			output += String.format("%2d  %-26s%-19s%19d%9d%11d\n", nodeCounter, 
			  cursor.getPerformanceName(), cursor.getLeadPerformer(), 
			  cursor.getTotalParticipants(), cursor.getPerformanceDuration(),
			  cursor.getPerformanceStartTime());
			System.out.println(output);
		}
		else
		{
			System.out.println("There is no node at this position");
		}
	}
	/* 
	 * Moves the current PerformanceNode forward by one.
	 * 
	 * Precondition: there is at least one PerformanceNode linked after 
	 *   the current one
	 * Postcondition: Returns true or false if the cursor was moved
	 *   forward to the next node.
	 * 
	 * @return
	 *   true or false if the cursor was moved
	 *   forward to the next node.
	 *   
	 * @throws IllegalArgumentException
	 *   Indicates that there is no current PerformanceNode
	 *  */
	public boolean moveCursorForward() throws Exception
	{
		if(cursor == null)
		{
			throw new CursorAtNullException("No node at cursor");
		}
		else if(cursor.getNext() == null)
		{
			return false;
		}
		else
		{
			cursor = cursor.getNext();
			return true;
		}
	}
	/* 
	 * Moves the current PerformanceNode backward by one.
	 * 
	 * Precondition: there is at least one PerformanceNode linked before 
	 *   the current one
	 * Postcondition: Returns true or false if the cursor was moved
	 *   backward to the previous node.
	 * 
	 * @return
	 *   true or false if the cursor was moved
	 *   backward to the previous node.
	 *   
	 * @throws IllegalArgumentException
	 *   Indicates that there is no current PerformanceNode in the 
	 *   linked list
	 *  */
	public boolean moveCursorBackward() throws Exception
	{
		if(cursor == null)
		{
			throw new CursorAtNullException("No node at cursor");
		}
		else if(cursor.getPrev() == null)
		{
			return false;
		}
		else
		{
			cursor = cursor.getPrev();
			return true;
		}
	}
	/* 
	 * Places the cursor/current PerformanceNode at a specified position
	 * 
	 * Precondition: there is at least one PerformanceNode in the list
	 *   and position is less than the number of PerformanceNodes in the
	 *   linked list and greater than 0.
	 * Postcondition: Returns true or false whether or not the current 
	 *   PerformanceNode was placed at the specified position
	 * 
	 * @return
	 *   true or false whether or not the current PerformanceNode was 
	 *   placed at the specified position
	 *  */
	public boolean jumpToPosition(int position)
	{
		if(position <= numberOfPerformances && position >= 1)
		{
			cursor = head;
			for(int i = 1; i < position; i++)
			{
				cursor = cursor.getNext();
			}
			return true;
		}		
		else
		{
			return false;
		}
	}
	/* 
	 * Returns a string of all of the attributes of all 
	 *   of the PerformanceNodes in the linked list or a message if 
	 *   there are no PerformanceNodes in the linked list.
	 * 
	 * Precondition: none
	 * Postcondition: Returns a string of all of the attributes of all 
	 *   of the PerformanceNodes in the linked list or a message if 
	 *   there are no PerformanceNodes in the linked list.
	 * 
	 * @return
	 *   a string of all of the attributes of all of the PerformanceNodes
	 *   in the linked list or a message if there are no PerformanceNodes
	 *   in the linked list.
	 *  */
	public String toString()
	{
		String output = "";
		if(head != null)
		{
			output = "Current No. Performance Name          Lead Performer Name" +
			  "       Participants Duration Start Time\n";
			output += "-----------------------------------------------------------------"
			  + "-------------------------------\n";
			PerformanceNode tempCursor = head;
			int nodeCounter = 0;
			while(tempCursor != null)
			{
				nodeCounter++;
				if(tempCursor == cursor)
				{
					output += "      * ";
					output += String.format("%2d  %-26s%-19s%19d%9d%11d\n", nodeCounter, 
				      tempCursor.getPerformanceName(), tempCursor.getLeadPerformer(), 
				      tempCursor.getTotalParticipants(), 
				      tempCursor.getPerformanceDuration(),
				      tempCursor.getPerformanceStartTime());
				}
				else
				{
					output += "        ";
					output += String.format("%2d  %-26s%-19s%19d%9d%11d\n", nodeCounter, 
				      tempCursor.getPerformanceName(), tempCursor.getLeadPerformer(), 
				      tempCursor.getTotalParticipants(), 
				      tempCursor.getPerformanceDuration(),
				      tempCursor.getPerformanceStartTime());
				}
				tempCursor = tempCursor.getNext();
			}
		}
		else
		{
			output = "There are no current nodes";
		}
		return output;
	}
}