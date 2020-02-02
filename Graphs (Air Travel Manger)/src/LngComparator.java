import java.util.Comparator;
/*	
 * The LngComparator class is used to help sort the list of cities
 *   based on their longitudes
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #7
 * CSE 214 Rectitation #1
 * */
public class LngComparator implements Comparator<City> {
	/*
	 * Compares the longitudes of two city objects to return
	 *   which one is larger.
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
	 *   an integer indicating whether or not c1's longitude
	 *   is less than, equal to, or greater than c2's.
	 * */
	public int compare(City c1, City c2) 
	{
		if (c1.getLocation().getLng() == c2.getLocation().getLng())
		{
            return 0;
		}
        else if (c1.getLocation().getLng() > c2.getLocation().getLng())
        {
            return 1;
        }
        else
        {
            return -1;
        }
	}
}