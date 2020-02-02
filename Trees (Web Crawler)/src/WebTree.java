import java.util.*;
import java.io.File;
import java.io.*;
/*	
 * The Webtree class contains the structure of a binary tree. It
 *   contains the number of types of links, and methods that allows
 *   the user to see the structure of the tree and create it based
 *   off of the links found. 
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #5
 * CSE 214 Rectitation #1
 * */
public class WebTree {
	/*	
	 * Attributes representing the webtree such as its root and the
	 *   number of types of links.
	 * */
	private HTMLLinkNode root;
	private int[] numberOfLinks = new int[4];
	private boolean deadLinkFound;
	private boolean keyWordFound;
	private boolean oneDeadLinkFound;
	private boolean oneKeyWordFound;
	/*
	 * Begins the recursive process of building the tree structure with
	 *   the user supplied filename as the root.
	 * 	
	 * Precondition: filename is a string and exists as a file
	 * Postcondition: the webtree that has been created after parsing
	 *   through the file.
	 * 
	 * @param filename
	 *   the name of the filename to parse and crawl.
	 *   
	 * @return
	 *   The webtree that is eventually structured.
	 *
	 * @throws IllegalArgumentException
	 *   filename is null or an empty String
	 * @throws FileNotFoundException
	 *   no file as specified by filename exists
	 * */
	public static WebTree crawlHTML(String fileName) throws 
	  IllegalArgumentException, FileNotFoundException
	{
		if((fileName == null) || (fileName.equals("")))
		{
			throw new IllegalArgumentException();
		}
		File systemFile = new File(fileName);
		String currentLine = "";
		Scanner file = new Scanner(systemFile);
		String title = "";
		String nextFileName = "";
		String nextLinkName = "";
		HTMLLinkNode rootLink = new HTMLLinkNode(fileName, title, "",
		  LinkType.ACTIVE);
		WebTree parentWebTree = new WebTree();
		parentWebTree.root = rootLink;
		
		while(file.hasNextLine())
		{
			currentLine = "";
			currentLine = file.nextLine();
			
			if(currentLine.contains("<title>"))
			{
				int titleBeginIndex = currentLine.indexOf("<title>") + 7;
				int titleEndIndex = currentLine.indexOf("</title");
				title = currentLine.substring(titleBeginIndex, 
				  titleEndIndex);
				currentLine = currentLine.substring(titleEndIndex, 
				  currentLine.length());
				rootLink.setPageTitle(title);
			}
				if(currentLine.contains("<a href"))
				{	
					String[] returnStrings = currentLine.split("href");
					for(int i = 1; i < returnStrings.length; i++)
					{
						int linkBeginIndex = 
						  returnStrings[i].indexOf("\"") + 1;
						int linkEndIndex = 
						  returnStrings[i].indexOf(".htm") + 5;
						nextFileName = returnStrings[i].substring(
						  linkBeginIndex, linkEndIndex);
						String linkNameString = 
						  returnStrings[i].substring(linkEndIndex,
						  returnStrings[i].length());
						int linkNameBeginIndex = 
						  linkNameString.indexOf(">") + 1;
						int linkNameEndIndex = 
						  linkNameString.indexOf("</a>");
						nextLinkName = linkNameString.substring(
						  linkNameBeginIndex, linkNameEndIndex);
						HTMLLinkNode nextLink = new HTMLLinkNode(
						  nextFileName, "", nextLinkName, LinkType.ACTIVE);
						try
						{
							rootLink.addLink(nextLink);
						}
						catch(Exception ex) {}
						nextLink.setParentLink(rootLink);
						crawlHTMLHelper(nextLink);
					}
				}
		}
		return parentWebTree;
	}
	/*
	 * Continues the recursive process of building the tree structure with
	 *   the previous file.
	 * 	
	 * Precondition: file is not null and of type HTMLLinkNode
	 * Postcondition: the next HTMLLinkNode object created
	 * 
	 * @param file
	 *   the previous file in the webtree
	 *   
	 * @return
	 *   The next html link node in the webtree
	 *
	 * @throws IllegalArgumentException
	 *   file is null or not an html node object.
	 * @throws FileNotFoundException
	 *   no file was found
	 * */
	public static HTMLLinkNode crawlHTMLHelper(HTMLLinkNode file) throws
	  IllegalArgumentException, FileNotFoundException
	{
		try
		{
			File nextSystemFile = new File(file.getFileName());
			Scanner nextFilereader = new Scanner(nextSystemFile);
			String nextFileTitle = "";
			String currentLine = "";
			while(nextFilereader.hasNextLine())
			{
				currentLine = nextFilereader.nextLine();
				if(currentLine.contains("<title>"))
				{
					nextFileTitle = currentLine.substring(
					  currentLine.indexOf("<title>") + 7,
					  currentLine.indexOf("</title>"));
					file.setPageTitle(nextFileTitle);
				}
			}
			nextFilereader.close();
			
			boolean existsAsAncestor = false;
			HTMLLinkNode temp = file;
			while(temp.getParentLink() != null)
			{
				if(temp.getParentLink().equals(file))
				{
					existsAsAncestor = true;
					break;
				}
				temp = temp.getParentLink();
			}
			
			if(existsAsAncestor)
			{
				file.setLinkType(LinkType.CIRCULAR);
				return file;
			}
		}
		catch(IOException ioe)
		{
			file.setLinkType(LinkType.DEAD);
			return file;
		}
		
		File systemFile = new File(file.getFileName());
		String currentLine = "";
		Scanner fileReader = new Scanner(systemFile);
		String nextfilename = "";
		String nextlinkname = "";
		
		while(fileReader.hasNextLine())
		{
			currentLine = "";
			currentLine = fileReader.nextLine();
			String[] returnStrings = currentLine.split("href");
			for(int i = 1; i < returnStrings.length; i++)
			{
				int linkBeginIndex = returnStrings[i].indexOf("\"") + 1;
				int linkEndIndex = returnStrings[i].indexOf(".htm") + 5;
				nextfilename = returnStrings[i].substring(linkBeginIndex,
				  linkEndIndex);
				String linkNameString = returnStrings[i].substring(
				  linkEndIndex, returnStrings[i].length());
				int linkNameBeginIndex = linkNameString.indexOf(">") + 1;
				int linkNameEndIndex = linkNameString.indexOf("</a>");
				nextlinkname = linkNameString.substring(
				  linkNameBeginIndex, linkNameEndIndex);
				HTMLLinkNode nextLink = new HTMLLinkNode(nextfilename, "",
				  nextlinkname, LinkType.ACTIVE);
				try
				{
					file.addLink(nextLink);
				}
				catch(Exception ex) {}
				
				nextLink.setParentLink(file);
				crawlHTMLHelper(nextLink);
			}
		}
		return file;	
	}
	/*
	 * Returns true or false if an equivalent object exists as one
	 *   of its ancestors.
	 * 	
	 * Precondition: An object of type HTMLLinkNode
	 * Postcondition: true or false
	 * 
	 * @param node
	 *   An object of type HTMLLinkNode
	 *
	 * @return
	 *   true or false if an equivalent object exists as one
	 *   of its ancestors.
	 * */
	public boolean existsAsAncestor(HTMLLinkNode node)
	{
		HTMLLinkNode temp = node;
		while(temp != null)
		{
			if(temp.getParentLink().equals(node))
			{
				return true;
			}
			temp = temp.getParentLink();
		}
		return false;
	}
	/*	
	 * Prints out all of the HTMLLinkNodes in order of which the
	 *   binary tree is structured (along with each node's
	 *   attributes).
	 * 
	 * Precondition: none
	 * Postcondition: Prints out all of the HTMLLinkNodes in order 
	 *   of which the binary tree is structured (along with each node's
	 *   attributes).
	 * */
	public void printWebTree()
	{
		if(root == null)
		{
			System.out.println("The tree is empty");
		}
		else
		{
			HTMLLinkNode cursor = root;
			String space = "";
			preOrderTraversalPrint(cursor, space);
		}
	}
	/*
	 * Continues the recursive process of printing out the
	 *   entire tree.
	 * 	
	 * Precondition: cursor is not null and of type HTMLLinkNode.
	 *   space is also not null.
	 * Postcondition: prints out the attributes of the current
	 *   HTMLLinkNode.
	 * 
	 * @param cursor
	 *   the file object in the webtree
	 * @param space
	 *   the number of spaces that should be printed before the
	 *   current HTMLLinkNode.
	 * */
	public void preOrderTraversalPrint(HTMLLinkNode cursor, String space)
	{
		System.out.print(space);
		System.out.println(cursor.toString());
		HTMLLinkNode[] childLinks = cursor.getLinks();
		if(childLinks[0] != null)
		{
			preOrderTraversalPrint(childLinks[0], space + "    ");
		}
		if(childLinks[1] != null)
		{
			preOrderTraversalPrint(childLinks[1], space + "    ");
		}
		if(childLinks[2] != null)
		{
			preOrderTraversalPrint(childLinks[2], space + "    ");
		}
	}
	/*	
	 * Prints out all of the dead HTMLLinkNodes and their paths 
	 *   from the root to that HTMLLinknode.
	 * 
	 * Precondition: none
	 * Postcondition: Prints out all of the dead HTMLLinkNodes and their
	 *   paths 
	 *   from the root to that HTMLLinknode.
	 * */
	public void printDeadLinks()
	{
		HTMLLinkNode cursor = root;
		this.deadLinkFound = false;
		this.oneDeadLinkFound = false;
		deadLinkPathTraversal(cursor);
		if(!this.deadLinkFound)
		{
			System.out.println("No dead links found.");
		}
	}
	/*
	 * Continues the recursive process of finding the deadlink.
	 *   It stops at a dead link and prints out the path to it
	 *   from the root.
	 * 	
	 * Precondition: cursor is not null and of type HTMLLinkNode.
	 * Postcondition: checks if the cursor is dead, and if it is,
	 *   the path to it is printed.
	 * 
	 * @param cursor
	 *   the file object in the webtree
	 * */
	public void deadLinkPathTraversal(HTMLLinkNode cursor)
	{
		if(cursor.getLinkType() == LinkType.DEAD)
		{
			if(!(this.oneDeadLinkFound))
			{
				System.out.println("Dead links found at:");
				this.oneDeadLinkFound = true;
			}
			this.deadLinkFound = true;
			HTMLLinkNode tempCursor = cursor.getParentLink();
			String currentPathTemp = " contains dead link \'" +
			  cursor.getLinkName() + "\' with target \'" + 
			  cursor.getFileName() + "\'";
			while(tempCursor != null)
			{
				if(tempCursor.getParentLink() == null)
				{
					currentPathTemp = tempCursor.getFileName() +
					  currentPathTemp;
				}
				else
				{
					currentPathTemp = " -> " + tempCursor.getFileName() +
					  currentPathTemp;
				}
				tempCursor = tempCursor.getParentLink();	
			}
			System.out.println(currentPathTemp);
		}
		HTMLLinkNode[] childLinks = cursor.getLinks();
				
		if(childLinks[0] != null)
		{
			deadLinkPathTraversal(childLinks[0]);
		}
		if(childLinks[1] != null)
		{
			deadLinkPathTraversal(childLinks[1]);
		}
		if(childLinks[2] != null)
		{
			deadLinkPathTraversal(childLinks[2]);
		}
	}
	/*
	 * Begins the recursive process of checking if the current node title
	 *   contains keyword.
	 * 	
	 * Precondition: keyword is not null.
	 * Postcondition: begins the recursive process of finding a keyword
	 *   in a tree.
	 * 
	 * @param keyword
	 *   the keyword that the user is looking for.
	 * */
	public void search(String keyWord) throws IllegalArgumentException
	{
		if(root == null)
		{
			System.out.println("No page found with keyword " + keyWord);
		}
		this.keyWordFound = false;
		this.oneKeyWordFound = false;
		HTMLLinkNode temp = root;
		searchHelper(keyWord, temp);
		if(!this.keyWordFound)
		{
			System.out.println("No page found with keyword " + "\'" +
			  keyWord + "\'");
		}
	}
	/*
	 * Continues the recursive process of printing out the
	 *   entire tree.
	 * 	
	 * Precondition: cursor is not null and of type HTMLLinkNode;
	 *   keyword is also not null.
	 * Postcondition: prints out the path to the current html link
	 *   node if it contains keyword.
	 * 
	 * @param keyword
	 *   the keyword that the user is looking for
	 * @param cursor
	 *   the file object in the webtree
	 * */
	public void searchHelper(String keyWord, HTMLLinkNode cursor)
	{
		boolean existsAsAncestor = false;
		HTMLLinkNode temp = cursor;
		while(temp.getParentLink() != null)
		{
			if(temp.getParentLink().equals(cursor))
			{
				existsAsAncestor = true;
				break;
			}
			temp = temp.getParentLink();
		}
		if(!existsAsAncestor)
		{
			String tempTitle = cursor.getPageTitle().toLowerCase();
			String keywordTemp = keyWord.toLowerCase();
			if(tempTitle.contains(keywordTemp))
			{
				if(!(this.oneKeyWordFound))
				{
					System.out.println("'" + keyWord + 
					  "' can be found at the following locations:");
					this.oneKeyWordFound = true;
				}
				this.keyWordFound = true;
				HTMLLinkNode tempCursor = cursor;
				String currentPathTemp = "";
				while(tempCursor != null)
				{
					if(tempCursor.getParentLink() == null)
					{
						currentPathTemp = tempCursor.getFileName() +
						  currentPathTemp;
					}
					else
					{
						currentPathTemp = " -> " + tempCursor.getFileName() +
						  currentPathTemp;
					}
					tempCursor = tempCursor.getParentLink();	
				}
				System.out.println(currentPathTemp);
			}
			HTMLLinkNode[] childLinks = cursor.getLinks();
				
			if(childLinks[0] != null)
			{
				searchHelper(keyWord, childLinks[0]);
			}
			if(childLinks[1] != null)
			{
				searchHelper(keyWord, childLinks[1]);
			}
			if(childLinks[2] != null)
			{
				searchHelper(keyWord, childLinks[2]);
			}
		}
	}
	/*
	 * Begins the recursive process of adding to the link
	 *   counter depending on its link type. Returns the array of 
	 *   the number of types of nodes.
	 * 	
	 * Precondition: the root is not null.
	 * Postcondition: Returns the array of the number of types of nodes.
	 *   
	 * @return
	 *   the array of the number of types of nodes.
	 * */
	public int[] nodeTypeCounter()
	{
		this.numberOfLinks = new int[4];
		HTMLLinkNode cursor = this.root;
		nodeTypeCounterHelper(cursor);
		if(this.numberOfLinks[0] > 0)
		{
			this.numberOfLinks[0]--;
			this.numberOfLinks[3]--;
		}
		return this.numberOfLinks;
	}
	/*
	 * Continues the recursive process of adding to the link
	 *   counter depending on its link type
	 * 	
	 * Precondition: cursor is not null and of type HTMLLinkNode;
	 * Postcondition: adds to the number of links depending on its
	 *   type.
	 * @param cursor
	 *   the file object in the webtree
	 * */
	public void nodeTypeCounterHelper(HTMLLinkNode cursor)
	{	
		HTMLLinkNode[] childLinks = cursor.getLinks();
		
		if(cursor.getLinkType() == LinkType.ACTIVE)
		{
			numberOfLinks[0]++;
		}
		else if(cursor.getLinkType() == LinkType.CIRCULAR)
		{
			numberOfLinks[1]++;
		}
		else if(cursor.getLinkType() == LinkType.DEAD)
		{
			numberOfLinks[2]++;
		}
		numberOfLinks[3]++;
		
		if(childLinks[0] != null)
		{
			nodeTypeCounterHelper(childLinks[0]);
		}
		if(childLinks[1] != null)
		{
			nodeTypeCounterHelper(childLinks[1]);
		}
		if(childLinks[2] != null)
		{
			nodeTypeCounterHelper(childLinks[2]);
		}
	}
	/*
	 * Resets the tree structure by setting the root equal to
	 *   null.
	 * 	
	 * Precondition: none
	 * Postcondition: Resets the tree structure.
	 * */
	public void resetTreeStructure()
	{
		this.root = null;
	}
	/*	
	 * Returns the current HTMLLinkNode.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the current HTMLLinkNode.
	 * 
	 * @return 
	 *   the current HTMLLinkNode
	 * */
	public HTMLLinkNode getRoot()
	{
		return this.root;
	}
	/*
	 * Assigns a HTMLLinkNode to the HTMLLinkNode.
	 * 	
	 * Precondition: root must be an object of type HTMLLinkNode.
	 * Postcondition: none
	 * 
	 * @param root
	 *   An HTMLLinkNode object.
	 * */
	public void setRoot(HTMLLinkNode root)
	{
		this.root = root;
	}
	/*	
	 * Returns whether or not a dead link was found.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not a dead link was found.
	 * 
	 * @return 
	 *    true or false depending on whether or not a dead link
	 *    was found.
	 * */
	public boolean getDeadLinkFound()
	{
		return this.deadLinkFound;
	}
	/*
	 * Sets up whether or not a dead link was found.
	 * 	
	 * Precondition: deadLinkFound must be either true or false
	 * Postcondition: none
	 * 
	 * @param deadLinkFound
	 *   whether or not a dead link was found
	 * */
	public void setDeadLinkFound(boolean deadLinkFound)
	{
		this.deadLinkFound = deadLinkFound;
	}
	/*	
	 * Returns whether or not a keyword was found.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not a keyword was found.
	 * 
	 * @return 
	 *   true or false depending on whether or not a keyword
	 *   was found.
	 * */
	public boolean getKeyWordFound()
	{
		return this.keyWordFound;
	}
	/*
	 * Sets up whether or not a keyword was found.
	 * 	
	 * Precondition: keyWordFound must be either true or false
	 * Postcondition: none
	 * 
	 * @param keyWordFound
	 *   whether or not a keyword was found
	 * */
	public void setKeyWordFound(boolean keyWordFound)
	{
		this.keyWordFound = keyWordFound;
	}
	/*	
	 * Returns whether or not exactly one dead link was found.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not exactly one dead link was found.
	 * 
	 * @return 
	 *    true or false depending on whether or not exactly one dead link
	 *    was found.
	 * */
	public boolean getOneDeadLinkFound()
	{
		return this.oneDeadLinkFound;
	}
	/*
	 * Sets up whether or not exactly one dead link was found.
	 * 	
	 * Precondition: oneDeadLinkFound must be either true or false
	 * Postcondition: none
	 * 
	 * @param oneDeadLinkFound
	 *   whether or not exactly one dead link was found
	 * */
	public void setOneDeadLinkFound(boolean oneDeadLinkFound)
	{
		this.oneDeadLinkFound = oneDeadLinkFound;
	}
	/*	
	 * Returns whether or not exactly one keyword was found.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not exactly one keyword was found.
	 * 
	 * @return 
	 *   true or false depending on whether or not exactly one keyword
	 *   was found.
	 * */
	public boolean getOneKeyWordFound()
	{
		return this.oneKeyWordFound;
	}
	/*
	 * Sets up whether or not one exactly keyword was found.
	 * 	
	 * Precondition: oneKeyWordFound must be either true or false
	 * Postcondition: none
	 * 
	 * @param oneKeyWordFound
	 *   whether or not exactly one keyword was found
	 * */
	public void setOneKeyWordFound(boolean oneKeyWordFound)
	{
		this.oneKeyWordFound = oneKeyWordFound;
	}
}