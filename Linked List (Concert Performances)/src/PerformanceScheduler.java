import java.util.Scanner;
/*	
 * The PerformanceScheduler class tests the PerformanceList class and the 
 *   PerformanceNode class by asking the user for commands and asking for 
 *   user input in order to perform methods within the other two classes.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #2
 * CSE 214 Rectitation #1
 * */
public class PerformanceScheduler{
	/*	
	 * The main driver method that tests thePerformanceList class and the 
	 *   PerformanceNode classes. The method acquires user input and asks the
	 *   user for commands. After which methods from either class are used to
	 *   perform tasks
	 *   
	 * Precondition: none
	 * Postcondition: Prints out the program and asks user for tasks
	 * */
	public static void main(String[] args) throws Exception
	{
		Scanner stdin = new Scanner(System.in);
		PerformanceList userEvent = new PerformanceList();
		char userAction = ' ';
		do
		{
			System.out.println(
			  "A) Add to end\r\n" + 
			  "B) Move current node backward\r\n" + 
			  "C) Display current node\r\n" + 
			  "D) Display all nodes\r\n" + 
			  "F) Move current node forward\r\n" + 
			  "I) Insert after current node\r\n" + 
			  "J) Jump to position\r\n" + 
			  "R) Remove current node\r\n" + 
			  "Q) Quit\r\n\n");
			System.out.print("Choose an operation: ");
			userAction = stdin.next().charAt(0);
			System.out.println();
			if(userAction == 'A' || userAction == 'a')
			{
				PerformanceNode userNode = userNewNode();
				userEvent.addToEnd(userNode);
				System.out.println("New performance " + userNode.getPerformanceName() + 
				  " is added to the end of the list.\n");
			}
			else if(userAction == 'B'|| userAction == 'b')
			{
				try {
					boolean backwardReturn = userEvent.moveCursorBackward();
					if(backwardReturn)
					{
						System.out.println("Cursor has been moved backwards.\n");
					}
					else
					{
						System.out.println("The cursor is at the beginning of the list\n");
					}
				}
				catch(CursorAtNullException cane)
				{
					System.out.println("Cursor is at the beginning of the list\n");
				}
			}
			else if(userAction == 'C'|| userAction == 'c') 
			{
				userEvent.displayCurrentPerformance();
			}
			else if(userAction == 'D'|| userAction == 'd')
			{
				System.out.println(userEvent.toString());
			}
			else if(userAction == 'F'|| userAction == 'f')
			{
				try {
					boolean forwardReturn = userEvent.moveCursorForward();
					if(forwardReturn)
					{
						System.out.println("Cursor has been moved forwards.\n");
					}
					else
					{
						System.out.println("The cursor is at the end of the list\n");
					}
				}
				catch(CursorAtNullException cane)
				{
					System.out.println("Cursor is pointing at a null object\n");
				}
			}
			else if(userAction == 'I'|| userAction == 'i')
			{
				PerformanceNode userNode = userNewNode();
				userEvent.addAfterCurrent(userNode);
				System.out.println("New performance " + userNode.getPerformanceName() + 
				  " is added after the current performance.");
			}
			else if(userAction == 'J'|| userAction == 'j')
			{
				System.out.print("Enter the position: ");
				int userPosition = stdin.nextInt();
				boolean jumpReturn = userEvent.jumpToPosition(userPosition);
				if(jumpReturn)
				{
					System.out.println("Cursor has been moved to position " + 
					  userPosition + ".\n");
				}
				else
				{
					System.out.println("This position does not exist\n");
				}
			}			
			else if(userAction == 'R' || userAction == 'r')
			{
				boolean removalReturn = userEvent.removeCurrentNode();
				if(removalReturn)
				{
					continue;
				}
				else
				{
					System.out.println("There is no current node\n");
				}
			}
		}
		while(!(userAction == 'Q' || userAction == 'q'));
	}
	/*	
	 * Returns a PerformanceNode created by acquiring values from the user
	 *   
	 * Precondition: none
	 * Postcondition: a PerformanceNode object with specified values  
	 *     
	 * @return
	 *   A PerformanceNode object with specified values
	 * */
	public static PerformanceNode userNewNode() 
	{
		Scanner stdin = new Scanner(System.in);
		System.out.print("Enter name of performance: ");
		String performanceName = stdin.nextLine();
		System.out.print("Enter name of lead performer: ");
		String leadPerformer = stdin.nextLine();
		System.out.print("Enter the total participants: ");
		int totalParticipants = stdin.nextInt();
		System.out.print("Enter the duration of the performance: ");
		int performanceDuration = stdin.nextInt();
		PerformanceNode tempNode = new PerformanceNode(performanceName, 
		  leadPerformer, totalParticipants, performanceDuration, 0);
		return tempNode;	
	}
}