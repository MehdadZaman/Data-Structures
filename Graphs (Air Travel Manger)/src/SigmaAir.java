import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import latlng.LatLng; 
/*	
 * The SigmaAir class instantiates a graph of city objects. It 
 *   represents a set of airports in certain cities. There is also
 *   a two dimensional array that contains the distance of the
 *   connections. It allows the addition of cities and connections.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #7
 * CSE 214 Rectitation #1
 * */
public class SigmaAir implements Serializable{
	/* 
	 * A constant representing the maximum number of cities
	 *   that can be represented.
	 * */
	public static final int MAX_CITIES = 100;
	/* 
	 * The ArrayList and 2D array where the city objects
	 *   and connections are stored.
	 * */
	private ArrayList<City> cities;
	private double[][] connections;
	/* 
	 * 2D arrays that are used to find the shortest path between
	 *   cities.
	 * */
	double[][] dist;
	City[][] next;
	/*	
	 * Constructs a generic HashedLibrary object and creates
	 *   an arraylist and a 2D array to store connections
	 *   and city objects.
	 * */
	public SigmaAir()
	{
		cities = new ArrayList<>();
		connections = new double[100][100];
		for(int i = 0; i < 100; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				if(i == j)
				{
					connections[i][j] = 0;
				}
				else
				{
					connections[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
		dist = new double[cities.size()][cities.size()];
		next = new City[cities.size()][cities.size()];
	}
	/* 
	 * Adds a new book city to the connection
	 * 
	 * Precondition: city must be a string
	 * Postcondition: none
	 * 
	 * @param city
	 *   The name of the desired city
	 * */
	public void addCity(String city)
	{
		boolean cityExists = false;
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getName().equals(city))
			{
				cityExists = true;
			}
		}
		if(!cityExists)
		{
			try
			{
				City tempCity = new City(city);
				cities.add(tempCity);
				System.out.println(city + " has been added: (" + 
				  tempCity.getLocation().getLat() + ", " + 
				  tempCity.getLocation().getLng() + ")");
			}
			catch(Exception e)
			{
				System.out.println("The city does not exist");
			}
		}
		else
		{
			System.out.println("City is already in the system");
		}
	}
	/* 
	 * Adds a new connection between two cities in the 2D array
	 * 
	 * Precondition: cityFrom and cityTo must be Strings
	 * Postcondition: none
	 * 
	 * @param cityFrom
	 *   the source of the connection
	 * @param cityTo
	 *  the destination of the connection
	 * 
	 * @throws Exception
	 *   Indicates that the connection does not exist
	 * */
	public void addConnection(String cityFrom, String cityTo) throws Exception
	{
		int cityFromIndex = 0;
		int cityToIndex = 0;
		boolean cityFromExists = false;
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getName().equals(cityFrom))
			{
				cityFromExists = true;
				cityFromIndex = i;
			}
		}
		boolean cityToExists = false;
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getName().equals(cityTo))
			{
				cityToExists = true;
				cityToIndex = i;
			}
		}
		if(!(cityFromExists && cityToExists))
		{
			throw new Exception();
		}
		LatLng cityFromLatLng = cities.get(cityFromIndex).getLocation();
		LatLng cityToLatLng = cities.get(cityToIndex).getLocation();
		double distance = LatLng.calculateDistance(cityFromLatLng,
		  cityToLatLng);
		
		if((connections[cities.get(cityFromIndex).getIndexPos()]
		  [cities.get(cityToIndex).getIndexPos()] == 0) ||
		  (connections[cities.get(cityFromIndex).getIndexPos()]
		  [cities.get(cityToIndex).getIndexPos()]
		  == Double.POSITIVE_INFINITY))
		{}
		else
		{
			System.out.println("Connection already exists");
			return;
		}
		connections[cities.get(cityFromIndex).getIndexPos()]
		  [cities.get(cityToIndex).getIndexPos()] = distance;
		String outPut = String.format(cities.get(cityFromIndex).getName() +
		  " ----> " + cities.get(cityToIndex).getName() + 
		  " added: %12f", connections[cities.get(cityFromIndex).getIndexPos()]
		  [cities.get(cityToIndex).getIndexPos()]);
		System.out.println(outPut);
	}
	/* 
	 * Removes a connection between two cities in the 2D array
	 * 
	 * Precondition: cityFrom and cityTo must be Strings
	 * Postcondition: none
	 * 
	 * @param cityFrom
	 *   the source of the connection
	 * @param cityTo
	 *  the destination of the connection
	 * 
	 * @throws Exception
	 *   Indicates that the connection does not exist
	 * */
	public void removeConnection(String cityFrom, String cityTo) 
	  throws Exception
	{
		int cityFromIndex = 0;
		int cityToIndex = 0;
		boolean cityFromExists = false;
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getName().equals(cityFrom))
			{
				cityFromExists = true;
				cityFromIndex = i;
			}
		}
		boolean cityToExists = false;
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getName().equals(cityTo))
			{
				cityToExists = true;
				cityToIndex = i;
			}
		}
		if(!(cityFromExists && cityToExists))
		{
			throw new Exception();
		}
		
		connections[cities.get(cityFromIndex).getIndexPos()]
		  [cities.get(cityToIndex).getIndexPos()] = Double.POSITIVE_INFINITY;
		System.out.println("Connection from " + cityFrom + " to " +
		  cityTo + " has been removed!");
	}
	/* 
	 * Finds and returns the shortest path between two cities
	 * 
	 * Precondition: cityFrom and cityTo must be Strings
	 * Postcondition: none
	 * 
	 * @param cityFrom
	 *   the source of the connection
	 * @param cityTo
	 *  the destination of the connection
	 *  
	 *  @return
	 *    the shortest path between two cities in the form of a String
	 * 
	 * @throws Exception
	 *   Indicates that the connection does not exist
	 * */
	public String shortestPath(String cityFrom, String cityTo)
	{
		int cityFromIndex = 0;
		int cityToIndex = 0;
		boolean cityFromExists = false;
		boolean cityToExists = false;
		
		this.shortestPathHelper();
		
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getName().equals(cityFrom))
			{
				cityFromExists = true;
				cityFromIndex = i;
			}
		}
		
		for(int i = 0; i < cities.size(); i++)
		{
			if(cities.get(i).getName().equals(cityTo))
			{
				cityToExists = true;
				cityToIndex = i;
			}
		}
		
		if(!(cityFromExists && cityToExists))
		{
			return "Shortest path from " + cityFrom + " to " + 
			  cityTo + " does not exist!";
		}
		
		if(next[cityFromIndex][cityToIndex] == null)
		{
			return "Shortest path from " + cityFrom + " to " +
			  cityTo + " does not exist!";
		}
		
		String path = cities.get(cityFromIndex).getName();
		
		double totalDistanceCovered = dist[cityFromIndex][cityToIndex];
		
		while(cityFromIndex != cityToIndex)
		{	
			cityFromIndex = next[cityFromIndex][cityToIndex].getIndexPos();
			path = path + " ------> " + cities.get(cityFromIndex).getName();	
		}
		return path + ": " + totalDistanceCovered;
	}
	/* 
	 * Uses the floyd-warshall algorithim to find the
	 *   set up 2D arrays to find the shortest path.
	 * 
	 * Precondition: the 2D arrays must exist
	 * Postcondition: none
	 * */
	public void shortestPathHelper()
	{
		dist = new double[cities.size()][cities.size()];
		next = new City[cities.size()][cities.size()];
		
		for(int i = 0; i < dist.length; i++)
		{
			for(int j = 0; j < dist.length; j++)
			{
				dist[i][j] = Double.POSITIVE_INFINITY;
				next[i][j] = null;
			}
		}
		
		for(int i = 0; i < dist.length; i++)
		{
			for(int j = 0; j < dist.length; j++)
			{
				if(!((connections[i][j] == 0) || 
				  (connections[i][j] == Double.POSITIVE_INFINITY)))
				{
					dist[i][j] = this.connections[i][j];
					next[i][j] = this.cities.get(j);
				}
			}
		}
		
		for(int k = 0; k < cities.size(); k++)
		{
			for(int i = 0; i < cities.size(); i++)
			{
				for(int j = 0; j < cities.size(); j++)
				{
					if (dist[i][k] + dist[k][j] < dist[i][j])
					{
						dist[i][j] = dist[i][k] + dist[k][j];
						next[i][j] = next[i][k];
					}
				}
			}
		}
	}
	/* 
	 * Prints the cities in a desired order
	 * 
	 * Precondition: comp must of type Comparator<City>
	 * Postcondition: Prints the cities in a desired order.
	 * 
	 * @param comp
	 *   the desired comparator for desired printing
	 * */
	public void printAllCities(Comparator<City> comp)
	{
		ArrayList<City> printCities = new ArrayList<>(); 
		for(int i = 0; i < cities.size(); i++)
		{
			printCities.add(cities.get(i));
		}
		Collections.sort(printCities, comp);
		for(int i = 0; i < printCities.size(); i++)
		{
			System.out.print(printCities.get(i).toString());
		}
		System.out.println();
	}
	/* 
	 * Prints all the connections and distances.
	 * 
	 * Precondition: none
	 * Postcondition: Prints all the connections and distances.
	 * */
	public void printAllConnections()
	{
		City.setCityCount(cities.size());
		String outPut = "";
		for(int i = 0; i < 100; i++)
		{
			for(int j = 0; j < 100; j++)
			{
				if((connections[i][j] == Double.POSITIVE_INFINITY) ||
				  (connections[i][j] == 0))
				{
					continue;
				}
				if(i == j)
				{
					j += 1;
				}
				if(j >= City.getCityCount())
				{
					break;
				}
				outPut = String.format("%-35s %12f", cities.get(i).getName() +
				  " ----> " + cities.get(j).getName(), connections[i][j]);
				System.out.println(outPut);
			}
		}
	}
	/*
	 * Reads a file and parses it in order to process and add all
	 *   of the cities in the file
	 * 	
	 * Precondition: fileName is a string and exists as a file
	 * Postcondition: none
	 * 
	 * @param filename
	 *   the name of the fileName to read.
	 *
	 * @throws IOException
	 *   fileName is invalid or does not exist
	 * */
	public void loadAllCities(String fileName) throws Exception
	{
		if((fileName == null) || (fileName.equals("")))
		{
			throw new IllegalArgumentException();
		}
		File readFile = new File(fileName);
		Scanner file = new Scanner(readFile);
		String nextCity = "";
		while(file.hasNextLine())
		{
			nextCity = file.nextLine().trim();
			this.addCity(nextCity);
		}
	}
	/*
	 * Reads a file and parses it in order to process and add all
	 *   of the connections between cities.
	 * 	
	 * Precondition: fileName is a string and exists as a file
	 * Postcondition: none
	 * 
	 * @param filename
	 *   the name of the fileName to read.
	 *
	 * @throws IOException
	 *   fileName is invalid or does not exist
	 * */
	public void loadAllConnections(String fileName) throws Exception
	{
		if((fileName == null) || (fileName.equals("")))
		{
			throw new IllegalArgumentException();
		}
		File readFile = new File(fileName);
		Scanner file = new Scanner(readFile);
		String cityConnect = "";
		String[] split = new String[2];
		String cityFrom = "";
		String cityTo = "";
		while(file.hasNextLine())
		{
			cityConnect = file.nextLine().trim();
			split = cityConnect.split(",");
			cityFrom = split[0];
			cityTo = split[1];
			try
			{
				this.addConnection(cityFrom, cityTo);
			}
			catch(Exception e)
			{
				System.out.println("Connection does not exist");
			}
		}
	}
	/*	
	 * Returns the ArrayList of cities of the sigma airline.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the ArrayList of cities 
	 *   of the sigma airline.
	 * 
	 * @return 
	 *   the ArrayList of cities of the sigma airline.
	 * */
	public ArrayList<City> getCities()
	{
		return this.cities;
	}
	/*
	 * Assigns an ArrayList of cities to the sigma airline
	 * 	
	 * Precondition: cities must be an arraylist
	 * Postcondition: none
	 * 
	 * @param cities
	 *   the arraylist representing the city objects.
	 * */
	public void setCities(ArrayList<City> cities)
	{
		this.cities = cities;
	}
	/*	
	 * Returns the 2D array of connections of the Sigma airline.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the 2D array of connections 
	 *   of the Sigma airline.
	 * 
	 * @return 
	 *   the 2D array of connections of the Sigma airline
	 * */
	public double[][] getConnections()
	{
		return this.connections;
	}
	/*
	 * Assigns a 2D array of connections to the Sigma airline
	 * 	
	 * Precondition: connections must be a 2D array
	 * Postcondition: none
	 * 
	 * @param connections
	 *   the 2D array representing the connections.
	 * */
	public void setConnections(double[][] connections)
	{
		this.connections = connections;
	}
}