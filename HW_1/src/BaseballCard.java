/*	
 * The BaseballCard class creates a baseball card 
 *   object, which has attributes that are generic specifications 
 *   of a baseball card. There are methods which allow the access and 
 *   manipulation of the attributes. There also methods that allow a 
 *   baseball card to be cloned and to see if one baseball card object 
 *   is equal to another.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #1
 * CSE 214 Rectitation #1
 * */
public class BaseballCard implements Cloneable{
	/*	
	 * The different attributes of the baseball card object
	 * */
	private String name;
	private String manufacturer;
	private int year;
	private double price;
	private int[] imageSize = new int[2];
	/*
	 * Constructs a new baseball object with assigned attributes
	 * 
	 * @param name
	 *   the name of the baseball card object
	 * @param manufacturer
	 *   the name of the manufacturer of the baseball card object
	 * @param year
	 *   the year associated with baseball card object
	 * @param price
	 *   the price of the baseball card object	
	 *   */
	public BaseballCard(String name, String manufacturer,
	  int year, int[] imageSize, double price) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.year = year;
		this.imageSize = imageSize.clone();
		this.price = price;
	}
	/*	
	 * Constructs a new baseball object without assigned attributes
	 * 
	 * Creates a baseball object without assigning it parameters
	 * */
	public BaseballCard() {} 
	/*	
	 * Returns the name of the baseball card
	 * 
	 * Precondition: none
	 * Postcondition: Returns the name associated with the baseball card.
	 * 
	 * @return 
	 *   Returns the name associated with the baseball card.
	 * */
	public String getName()
	{
		return this.name;
	}
	/*
	 * Assigns a name to the baseball card.
	 * 
	 * Precondition: none
	 * Postcondition: none
	 * 	
	 * @param name
	 *   The name that will be used for the baseball card.
	 *  */
	public void setName(String name)
	{
		this.name = name;
	}
	/*
	 * Returns the name of the manufacturer the baseball card
	 * 
	 * Precondition: none
	 * Postcondition: returns the manufacturer associated with the baseball card	
	 * 
	 * @return 
	 *   Returns the name associated with the baseball card	
	 *   */
	public String getManufacturer()
	{
		return this.manufacturer;
	}
	/*	
	 * Assigns a manufacturer to the baseball card.
	 * 
	 * Precondition: none
	 * Postcondition: none
	 * 
	 * @param name
	 *   The name of the manufacturer that will be used for the baseball card.
	 *  */
	public void setManufacturer(String manufacturer)
	{
		this.manufacturer = manufacturer;
	}
	/*	 
	 * Returns the year associated with the baseball card
	 * 
	 * Precondition: none
	 * Postcondition: Returns the year associated with the card
	 * 
	 * @return 
	 *   Returns the year associated with the baseball card
	 *   */
	public int getYear() 
	{
		return this.year;
	}
	/*
	 * Assigns a value to the year associated with the baseball card.
	 * 	
	 * Precondition: year must be an integer and should not 
	 *   exceed the maximum size of an int
	 * Postcondition: none
	 * 
	 * @param year
	 *   The year of associated with the baseball card.
	 * */
	public void setYear(int year)
	{
		this.year = year;
	}
	/*	
	 * Returns the price of the baseball card
	 * 
	 * Precondition: none
	 * Postcondition: Returns the price associated with the baseball card.
	 * 
	 * @return 
	 *   Returns the price the baseball card
	 *   */
	public double getPrice()
	{
		return this.price;
	}
	/*	
	 * Assigns a value to the price of the baseball card.
	 *
	 *
	 * Precondition: Price should not exceed the maximum size of a double
	 * Postcondition: none
	 * 
	 * @param name
	 *   The new price of the baseball card.
	 *   
	 * @exception IllegalArgumentException
	 *   Indicates that the price should not be less than zero
	 *   */
	public void setPrice(double price) throws Exception
	{
		if(price < 0)
		{
			throw new IllegalArgumentException("Invalid Price");
		}
		this.price = price;
	}
	/*	
	 * Returns the X size of the baseball card
	 * 
	 * Precondition: none
	 * Postcondition: Returns the X size associated with the baseball card.
	 * 
	 * @return 
	 *   Returns the X size of the baseball card
	 *   */
	public int getSizeX() 
	{
		return this.imageSize[0];
	}
	/*	
	 * Assigns a value to the X size of the baseball card.
	 * 
	 * Precondition: SizeX must be an integer and should not 
	 *   exceed the maximum size of an int
	 * Postcondition: none
	 * 
	 * @param SizeX
	 *   The new X size of the baseball card.
	 *   */
	public void setSizeX(int SizeX) 
	{
		this.imageSize[0] = SizeX;
	}
	/*	
	 * Returns the Y size of the baseball card
	 * 
	 * Precondition: none
	 * Postcondition: Returns the Y size associated with the baseball card.
	 * 
	 * @return 
	 *   Returns the Y size of the baseball card
	 *   */
	public int getSizeY() 
	{
		return this.imageSize[1];
	}
	/*	
	 * Assigns a value to the Y size of the baseball card.
	 * 
	 * Precondition: SizeY must be an integer and should not 
	 *   exceed the maximum size of an int
	 * Postcondition: none
	 * 
	 * @param SizeY
	 *   The new Y size of the baseball card.
	 *   */
	public void setSizeY(int SizeY) 
	{
		this.imageSize[1] = SizeY;
	}
	/*
	 * Returns a clone of a baseball object by cloning 
	 *   all of its attributes.
	 * 
	 * Precondition: none
	 * Postcondition: Returns a clone of the baseball card.
	 * 
	 * @return 
	 *   a clone of the current baseball object	
	 * */
	public Object clone()
	{
		BaseballCard tempCard = new BaseballCard();
		try {
			tempCard = (BaseballCard)super.clone();
		}
		catch(CloneNotSupportedException CNSE){
			
		}
		tempCard.name = new String(this.name);
		tempCard.manufacturer = new String(this.manufacturer);
		tempCard.imageSize = this.imageSize.clone();
		return tempCard;
	}
	/*	
	 * Returns true or false depending on whether or not two baseball
	 *   cards are equal (share the same attributes) to each other.
	 * 
	 * Precondition: obj exists
	 * Postcondition: returns true or false depending on whether or 
	 *   not two baseball cards are equal (share the same attributes)
	 *   to each other.
	 * 
	 * @return 
	 *   true or false depending on whether or not two baseball 
	 *   objects have the same attributes
	 * */
	public boolean equals(Object obj)
	{
		if(obj instanceof BaseballCard)
		{
			return
			  (this.price == ((BaseballCard)obj).price) && 
			  (this.year == ((BaseballCard)obj).year) &&
			  (this.name.equals(((BaseballCard) obj).name)) &&
			  (this.manufacturer.equals(((BaseballCard)obj).manufacturer)) &&
			  (this.imageSize[0] == ((BaseballCard)obj).imageSize[0]) && 
			  (this.imageSize[1] == ((BaseballCard)obj).imageSize[1]);
		}
		else 
		{
			return false;
		}
	}
}