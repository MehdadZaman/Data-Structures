/*	
 * The HTMLLinkNode class represents an html file object, which has
 *   attributes that represent the file name, title, 
 *   and link name of the html file. There are methods which allow
 *   the access and manipulation of these attributes. 
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #5
 * CSE 214 Rectitation #1
 * */
public class HTMLLinkNode {
	/* 
	 * The attributes of the html file object.
	 * */
	private String fileName;
	private String pageTitle;
	private String linkName;
	
	private int numberOfChildLinks;
	/* 
	 * The parent link of the current html node.
	 * */
	private HTMLLinkNode parentLink;
	/* 
	 * The child links of the current html node.
	 * */
	private HTMLLinkNode[] links = new HTMLLinkNode[3];
	
	private LinkType linkType;
	/*	
	 * Creates an HTMLLink Node object (bus stop). 
	 * 
	 * @param fileName
	 *   the fileName of the html node.
	 * @param pageTitle
	 *  the pageTitle of the html node.
	 * @param linkName
	 *   the linkName of the html node.
	 * @param linkType
	 *   the linkType of the html node.
	 * */
	public HTMLLinkNode(String fileName, String pageTitle, String linkName,
	  LinkType linkType)
	{
		this.fileName = new String(fileName);
		this.pageTitle = new String(pageTitle);
		this.linkName = new String(linkName);
		this.linkType = linkType;
	}
	/*	
	 * Constructs a generic HTMLLinkNode object.
	 * */
	public HTMLLinkNode()
	{
		this("no file name","no title", "no link text", LinkType.DEAD);
	}
	/*	
	 * Returns the fileName of the HTMLLinkNode.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the fileName of the HTMLLinkNode.
	 * 
	 * @return 
	 *   the fileName of the HTMLLinkNode
	 * */
	public String getFileName()
	{
		return this.fileName;
	}
	/*
	 * Assigns a fileName to the HTMLLinkNode.
	 * 	
	 * Precondition: fileName must be a String.
	 * Postcondition: none
	 * 
	 * @param fileName
	 *   The fileName of the HTMLLinkNode.
	 * */
	public void setFileName(String fileName)
	{
		this.fileName = new String(fileName);
	}
	/*	
	 * Returns the pageTitle of the HTMLLinkNode.
	 * 
	 * Precondition: pageTitle
	 * Postcondition: Returns the pageTitle of the HTMLLinkNode.
	 * 
	 * @return 
	 *   the pageTitle of the HTMLLinkNode
	 * */
	public String getPageTitle()
	{
		return this.pageTitle;
	}
	/*
	 * Assigns a pageTitle to the HTMLLinkNode.
	 * 	
	 * Precondition: pageTitle must be a String.
	 * Postcondition: none
	 * 
	 * @param pageTitle
	 *   The pageTitle of the HTMLLinkNode.
	 * */
	public void setPageTitle(String pageTitle)
	{
		this.pageTitle = new String(pageTitle);
	}
	/*	
	 * Returns the linkName of the HTMLLinkNode.
	 * 
	 * Precondition: linkName
	 * Postcondition: Returns the linkName of the HTMLLinkNode.
	 * 
	 * @return 
	 *   the linkName of the HTMLLinkNode
	 * */
	public String getLinkName()
	{
		return this.linkName;
	}
	/*
	 * Assigns a linkName to the HTMLLinkNode.
	 * 	
	 * Precondition: linkName must be a String.
	 * Postcondition: none
	 * 
	 * @param linkName
	 *   The linkName of the HTMLLinkNode.
	 * */
	public void setLinkName(String linkName)
	{
		this.linkName = new String(linkName);
	}
	/*	
	 * Returns the linkType of the HTMLLinkNode.
	 * 
	 * Precondition: linkType
	 * Postcondition: Returns the linkType of the HTMLLinkNode.
	 * 
	 * @return 
	 *   the linkType of the HTMLLinkNode
	 * */
	public LinkType getLinkType()
	{
		return this.linkType;
	}
	/*
	 * Assigns a linkType to the HTMLLinkNode.
	 * 	
	 * Precondition: linkType must be of type LinkType.
	 * Postcondition: none
	 * 
	 * @param linkType
	 *   The linkType of the HTMLLinkNode.
	 * */
	public void setLinkType(LinkType linkType)
	{
		this.linkType = linkType;
	}
	/*	
	 * Returns the parentLink of the HTMLLinkNode.
	 * 
	 * Precondition: linkType
	 * Postcondition: Returns the parent link of the HTMLLinkNode.
	 * 
	 * @return 
	 *   the parent link of the HTMLLinkNode
	 * */
	public HTMLLinkNode getParentLink()
	{
		return this.parentLink;
	}
	/*
	 * A new HTMLLinkNode object to add as the parent of the node.
	 * 	
	 * Precondition: parentLink is not null and of type HTMLLinkNode.
	 * Postcondition: none
	 * 
	 * @param parentLink
	 *   A new HTMLLinkNode object to add as the parent of the node.
	 * */
	public void setParentLink(HTMLLinkNode parentLink)
	{
		this.parentLink = parentLink;
	}
	/*	
	 * Returns the number of child links of the html node object.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of child links of 
	 *   the html node object.
	 * 
	 * @return 
	 *   Returns the number of child links of the html node object.
	 * */
	public int getNumberOfChildLinks()
	{
		return this.numberOfChildLinks;
	}
	/*
	 * Assigns a new number of child links to the html node object.
	 * 	
	 * Precondition: numberOfPassengers must be an integer 
	 *   and should not exceed the maximum size of an int.
	 *   Its should be positive and less than three.
	 * Postcondition: none
	 * 
	 * @param numberOfChildLinks
	 *   The number of numberOfChildLinks of the html node object.
	 * */
	public void setNumberOfChildLinks(int numberOfChildLinks)
	{
		this.numberOfChildLinks = numberOfChildLinks;
	}
	/*	
	 * Returns an array of child links of the HTMLLinkNode.
	 * 
	 * Precondition: linkType
	 * Postcondition: Returns an array of child links 
	 *   of the HTMLLinkNode.
	 * 
	 * @return 
	 *   an array of child links of the HTMLLinkNode
	 * */
	public HTMLLinkNode[] getLinks()
	{
		return this.links;
	}
	/* 
	 * Returns whether or not the LinkType of the HTMLLinkNode
	 *   is dead.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not the LinkType 
	 *   of the HTMLLinkNode is dead.
	 * 
	 * @return
	 *   true or false depending on whether or not the LinkType 
	 *   of the HTMLLinkNode is dead.
	 *  */
	public boolean isDeadLink()
	{
		if (this.linkType == LinkType.DEAD)
		{
			return true;
		}
		return false;
	}
	/* 
	 * Returns whether or not the LinkType of the HTMLLinkNode
	 *   is circular.
	 * 
	 * Precondition: none
	 * Postcondition: Returns whether or not the LinkType 
	 *   of the HTMLLinkNode is circular.
	 * 
	 * @return
	 *   true or false depending on whether or not the LinkType 
	 *   of the HTMLLinkNode is circular.
	 *  */
	public boolean isCircularLink()
	{
		if (this.linkType == LinkType.CIRCULAR)
		{
			return true;
		}
		return false;
	}
	/*
	 * Adds newLink as a child of this node in the first open position of
	 *   the links array.
	 * 	
	 * Precondition: There is at least one open position in the links 
	 *   array, and newLink is not null.
	 * Postcondition: newLink has been added as a child of this node.
	 * 
	 * @param newLink
	 *   A new HTMLLinkNode object to add as a child of the node.
	 *
	 * @throws FullNodeException
	 *   All positions in the links array are currently full
	 * @throws IllegalArgumentException
	 *   newLink is not of type HTMLLinkNode
	 * */
	public void addLink(HTMLLinkNode newLink) throws FullNodeException, 
	  IllegalArgumentException
	{
		if(this.numberOfChildLinks > this.links.length)
		{
			throw new FullNodeException();
		}
		else if(newLink == null)
		{
			throw new IllegalArgumentException();
		}
		this.links[this.numberOfChildLinks] = newLink;
		this.numberOfChildLinks++;
	}
	/*
	 * Returns the HTMLLinkNode located at index in the links array.
	 * 	
	 * Precondition: index is within the bounds of the links array.
	 * Postcondition:the child link at the desired index.
	 * 
	 * @param index
	 *   The index of the link to return from the links array.
	 *
	 * @return
	 *   the HTMLLinkNode located at index in the links array
	 *   
	 * @throws IllegalArgumentException
	 *   index is not within the bounds of zero and the number 
	 *   of child links.
	 * */
	public HTMLLinkNode getLinkAt(int index) throws IllegalArgumentException
	{
		if((index < 0) || (index > this.links.length))
		{
			throw new IllegalArgumentException();
		}
		return this.links[index];
	}
	/*
	 * Returns true or false if object o is equal to the current node.
	 * 	
	 * Precondition: An object of type HTMLLinkNode
	 * Postcondition: true or false
	 * 
	 * @param o
	 *   An object of type HTMLLinkNode
	 *
	 * @return
	 *   true or false if object o is equal to the current node.
	 * */
	public boolean equals(Object o)
	{
		if(!(o instanceof HTMLLinkNode))
		{
			return false;
		}
		
		String fileOnePageTitle = this.getPageTitle().toLowerCase();
		String fileTwoPageTitle = 
		  ((HTMLLinkNode)o).getPageTitle().toLowerCase();
		
		if(this.getFileName().equals(((HTMLLinkNode)o).getFileName()) 
		  && (fileOnePageTitle.equals(fileTwoPageTitle)))
		{
			return true;
		}
		return false;
	}
	/*	
	 * Returns a string of the attributes of the HTML node.
	 * 
	 * Precondition: none
	 * Postcondition: Returns a string of the attributes 
	 *   of the HTML node.
	 * 
	 * @return 
	 *   Returns a string of the attributes of the HTML node.
	 * */
	public String toString()
	{
		String returnString = "|-";
		
		returnString += this.fileName;
		if(this.linkType == LinkType.CIRCULAR)
		{
			returnString += "* ";
		}
		else
		{
			returnString += " ";
		}
		returnString += "(" + this.linkName + ") ";
		
		if(this.linkType == LinkType.DEAD)
		{
			returnString += "[ *** ]";
		}
		else
		{
			returnString += "[ " + this.pageTitle + " ]";
		}
		
		return returnString;
	}
}