import java.util.Comparator;
/*	
 * The NameComparator class is used to help sort the list of cities
 *   based on their names
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #7
 * CSE 214 Rectitation #1
 * */
public class NameComparator implements Comparator<City> {
	/*
	 * Compares the names of two city objects to return
	 *   which one is alphabetically greater.
	 * 	
	 * Precondition: c1 and c2 must be City objects
	 * Postcondition: none
	 * 
	 * @param c1
	 *   One city object.
	 * @param c1
	 *   The other city object.
	 *   
	 * @return
	 *   an integer indicating whether or not c1's name
	 *   is alphabetically less than, equal to, or greater 
	 *   than c2's.
	 * */
	public int compare(City c1, City c2) 
	{
        return (c1.getName().compareTo(c2.getName()));
    }
}