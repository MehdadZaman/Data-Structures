import java.util.Scanner;
import java.util.*;
import java.io.*;
/*	
 * The WebCrawler class tests the other classes by loading an html file
 *   acquired from the user, and traverses through the file, loading 
 *   all connected links. The program then serves commands asked for 
 *   by the user.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #5
 * CSE 214 Rectitation #1
 * */
public class WebCrawler {
	/* 
	 * The webtree for the user to access.
	 * */
	private WebTree webTree;
	/*	
	 * The main method that tests the other classes by loading an html file
	 *   acquired from the user, and traverses through the file, loading 
	 *   all connected links. The program then serves commands asked for 
	 *   by the user.
	 *   
	 * Precondition: none
	 * Postcondition: Prints out the program and asks user for tasks
	 * */
	public static void main(String[] args) throws Exception
	{
		Scanner stdin = new Scanner(System.in);
		char userAction = 'q';
		
		WebCrawler userTree = new WebCrawler();
		do
		{
			System.out.println(
					"[L] : Load HTML file\n" +
					"[P] : Print tree\n" +
					"[D] : Print dead links\n" +
					"[S] : Search for a page with a title within the tree\n" +
					"[R] : Reset tree structure\n" +
					"[Q] : Quit");
			System.out.println();
			System.out.print("Enter Selection: ");
			userAction = stdin.next().charAt(0);
			if(userAction == 'L' || userAction == 'l')
			{
				System.out.print("Enter HTML File: ");
				String fileName = stdin.next();
				
				try
				{
					userTree.webTree = WebTree.crawlHTML(fileName);
					int[] nodeCounter = userTree.webTree.nodeTypeCounter();
					System.out.println("'" + fileName + 
					  "' has been successfully crawled.");
					System.out.println(nodeCounter[0] + 
					  " active links followed");
					System.out.println(nodeCounter[1] + 
					  " circular links found");
					System.out.println(nodeCounter[2] + 
					  " dead links found");
					System.out.println(nodeCounter[3] + 
					  " total links found");
				}
				catch(FileNotFoundException fnfe)
				{
					System.out.println("No such file '" + fileName + "'.");
				}
				catch(IllegalArgumentException iae)
				{
					System.out.println("File is not available");
				}
			}
			else if(userAction == 'P'|| userAction == 'p')
			{
				try
				{
					userTree.webTree.printWebTree();
				}
				catch(NullPointerException npe)
				{
					System.out.println("The tree is empty");
				}
			}
			else if(userAction == 'D'|| userAction == 'd') 
			{
				try
				{
					userTree.webTree.printDeadLinks();
				}
				catch(NullPointerException npe)
				{
					System.out.println("The tree is empty");
				}
			}
			else if(userAction == 'S'|| userAction == 's')
			{
				try
				{
					System.out.print("Enter keyword of title to " +
				      "search for: ");
					String keyWord = stdin.next();
					userTree.webTree.search(keyWord);
				}
				catch(NullPointerException npe)
				{
					System.out.println("The tree is empty");
				}
			}
			else if(userAction == 'R'|| userAction == 'r')
			{
				try
				{
					userTree.webTree.resetTreeStructure();
					System.out.println("Information reset.");
				}
				catch(NullPointerException npe)
				{
					System.out.println("The tree is empty");
				}
			}
		}
		while(!(userAction == 'Q' || userAction == 'q'));
		System.out.println("Program terminating successfully...");
	}
}