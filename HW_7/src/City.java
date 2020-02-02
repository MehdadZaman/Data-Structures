import com.cse214.geocoder.GeocodeResponse;
import com.cse214.geocoder.Geocoder;
import latlng.LatLng; 
import java.util.*;
import java.io.*;
/*	
 * The City class represents an city object, which has
 *   attributes that represent the city name, location, 
 *   and index in the array of connections. There are methods 
 *   which allow the access and manipulation of these attributes. 
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #7
 * CSE 214 Rectitation #1
 * */
public class City implements Serializable{
	/* 
	 * The attributes of the city object.
	 * */
	private String name;
	private LatLng location;
	private int indexPos;
	/* 
	 * The number of cities.
	 * */
	private static int cityCount;
	/*	
	 * Creates a city object. 
	 * 
	 * @param name
	 *   The name of the city
	 * 
	 * @throws Exception
	 *   Indicates that the city is not in the database.
	 * */
	public City(String name) throws Exception
	{
		if(cityCount < 100)
		{
			try 
			{
			    Geocoder geocoder = new Geocoder();
			    GeocodeResponse geocodeResponse;
			    
			    geocodeResponse = geocoder.geocode(name);
			    
			    this.location = new LatLng(geocodeResponse.getLat(), 
			      geocodeResponse.getLng());
			    
			    this.name = new String(name);
				indexPos = cityCount;
				cityCount++;
			} 
			catch (Exception e) 
			{
			    throw new Exception();
			}
		}
		else
		{
			System.out.println("Array is full");
		}
	}
	/*	
	 * Creates a generic City object.
	 * 
	 * @throws Exception
	 *   Indicates that the city is not in the database.
	 * */
	public City() throws Exception
	{
		this("empty city");
	}
	/*	
	 * Returns the name of the city.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the name of the city.
	 * 
	 * @return 
	 *   the name of the city
	 * */
	public String getName()
	{
		return this.name;
	}
	/*
	 * Assigns a name to the city.
	 * 	
	 * Precondition: name must be a String.
	 * Postcondition: none
	 * 
	 * @param name
	 *   the name of the city.
	 * */
	public void setName(String name)
	{
		this.name = new String(name);
	}
	/*	
	 * Returns the LatLng object associated with the city.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the LatLng object associated with the city.
	 * 
	 * @return 
	 *   the LatLng object associated with the city
	 * */
	public LatLng getLocation()
	{
		return this.location;
	}
	/*
	 * Assigns a LatLng object to the city.
	 * 	
	 * Precondition: location must be of type LatLng.
	 * Postcondition: none
	 * 
	 * @param location
	 *   the LatLng object of the city representing
	 *   latitude and longitude.
	 * */
	public void setLocation(LatLng location)
	{
		this.location = location;
	}
	/*	
	 * Returns the index of the city in the array.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the index of the city in the array.
	 * 
	 * @return 
	 *   the index of the city in the array
	 * */
	public int getIndexPos()
	{
		return this.indexPos;
	}
	/*
	 * Assigns a number to the index of the city in the array.
	 * 	
	 * Precondition: indexPos must be an integer and must be
	 *   within the bounds of the array.
	 * Postcondition: none
	 * 
	 * @param indexPos
	 *   the position of the city in the array.
	 * */
	public void setIndexPos(int indexPos)
	{
		this.indexPos = indexPos;
	}
	/*	
	 * Returns the number of cities.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of cities.
	 * 
	 * @return 
	 *   the the number of cities
	 * */
	public static int getCityCount()
	{
		return cityCount;
	}
	/*
	 * Assigns a number to the number of cities.
	 * 	
	 * Precondition: cityCountValue must be an integer and must be
	 *   less than 100.
	 * Postcondition: none
	 * 
	 * @param cityCountValue
	 *   the number of cities.
	 * */
	public static void setCityCount(int cityCountValue)
	{
		cityCount = cityCountValue;
	}
	/*	
	 * Returns a string of the attributes of the city.
	 * 
	 * Precondition: none
	 * Postcondition: Returns a string of the 
	 *   attributes of the city.
	 * 
	 * @return 
	 *   a string of the attributes of the city
	 * */
	public String toString()
	{
		String outPut = String.format("%-24s    %-14f  %-14f\n", 
		  this.name, this.getLocation().getLat(), 
		  this.getLocation().getLng());
				
		return outPut;
	}
}