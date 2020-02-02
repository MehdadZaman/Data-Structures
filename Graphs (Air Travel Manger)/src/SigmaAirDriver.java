import java.util.*;
import java.io.*;
import java.util.Scanner;
/*	
 * The SigmaAirDriver class acquires input from the user such as
 *   files and cities/connections in order to add to it to the
 *   database.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #7
 * CSE 214 Rectitation #1
 * */
public class SigmaAirDriver implements Serializable{
	/*	
	 * The main driver method that tests the other classes. 
	 *   The method acquires user input whether its files to read from
	 *   or city information to input. It also allows the user to see
	 *   the database.
	 *   
	 * Precondition: none
	 * Postcondition: Allows the user to interact with the database.
	 * */
	public static void main(String[] args)
	{
		SigmaAir sigma = null;
		Scanner stdin = new Scanner(System.in);
		char userAction = ' ';
		try
		{
			File f = new File("sigma_air.obj");
			if(f.exists())
			{
				FileInputStream fileinput = 
				  new FileInputStream("sigma_air.obj");
				ObjectInputStream inStream = 
				  new ObjectInputStream(fileinput);
				sigma = (SigmaAir)inStream.readObject();
				inStream.close();
				fileinput.close();
				System.out.println("Successfully loaded contents of " +
				  "sigma_air.obj.");
			}
			else
			{
				sigma = new SigmaAir();
				System.out.println("sigma_air.obj is not found. " +
				  "New SigmaAir object will be created.");
			}
		}
		catch (IOException ioe)
		{}
		catch (Exception ex)
		{}
		do
		{
			System.out.println(
					"(A) Add City\n" +
					"(B) Add Connection\n" +
					"(C) Load all Cities\n" +
					"(D) Load all Connections\n" +
					"(E) Print all Cities\n" +
					"(F) Print all Connections\n" +
					"(G) Remove Connection\n" +
					"(H) Find Shortest Path\n" +
					"(Q) Quit");
			System.out.println();
			System.out.print("Enter a selection: ");
			userAction = stdin.next().charAt(0);
			stdin.nextLine();
			if(userAction == 'A' || userAction == 'a')
			{
				System.out.print("Enter the name of the city: ");
				String cityInput = stdin.nextLine();
				sigma.addCity(cityInput);	
			}
			else if(userAction == 'B'|| userAction == 'b')
			{
				try
				{
					System.out.print("Enter source city: ");
					String sourceCity = stdin.nextLine();
					System.out.print("Enter destination city: ");
					String destCity = stdin.nextLine();
					sigma.addConnection(sourceCity, destCity);
				}
				catch(Exception e)
				{
					System.out.println("Connection does not exist");
				}
			}
			else if(userAction == 'C'|| userAction == 'c') 
			{
				try
				{
					System.out.print("Enter the file name: ");
					String fileName = stdin.next();
					sigma.loadAllCities(fileName);
				}
				catch(Exception e)
				{
					System.out.println("File not found or of " +
					  "incorrect format.");
				}
			}
			else if(userAction == 'D'|| userAction == 'd')
			{
				try
				{
					System.out.print("Enter the file name: ");
					String fileName = stdin.next();
					sigma.loadAllConnections(fileName);
				}
				catch(Exception e)
				{
					System.out.println("File not found or of " +
					  "incorrect format.");
					System.out.println();
				}
			}
			else if(userAction == 'E'|| userAction == 'e')
			{
				String comparetype = "";
				do
				{
					System.out.println(
							"(EA) Sort Cities by Name\n" +
							"(EB) Sort Cities by Latitude\n" +
							"(EC) Sort Cities by Longitude\n" +
							"(Q) Quit");
					System.out.println();
					System.out.print("Enter a selection:");
					comparetype = stdin.next();
					try
					{
						if(comparetype.charAt(0) == 'Q' ||
						  comparetype.charAt(0) == 'q')
						{
							break;
						}
						if(comparetype.charAt(1) == 'A' || 
						  comparetype.charAt(1) == 'a')
						{
							System.out.println("Cities:\r\n" + 
							  "City Name                   Latitude  " +
							  "      Longitude");
							System.out.println("---------------------------" +
							  "-------------------------------");
							sigma.printAllCities(new NameComparator());
						}
						else if(comparetype.charAt(1) == 'B' || 
						  comparetype.charAt(1) == 'b')
						{
							System.out.println("Cities:\r\n" + 
							  "City Name                   Latitude   " +
							  "     Longitude");
							System.out.println("-------------------------" +
							 "---------------------------------");
							sigma.printAllCities(new LatComparator());
						}
						else if(comparetype.charAt(1) == 'C' || 
						  comparetype.charAt(1) == 'c')
						{
							System.out.println("Cities:\r\n" + 
							  "City Name                   Latitude    " +
							  "    Longitude");
							System.out.println("--------------------------" +
							  "--------------------------------");
							sigma.printAllCities(new LngComparator());
						}
					}
					catch(Exception e)
					{}
				}
				while(comparetype.charAt(0) != 'Q' || 
				  comparetype.charAt(0) != 'q');
			}
			else if(userAction == 'F'|| userAction == 'f')
			{
				System.out.println("Connections:\r\n" + 
				  "Route                               Distance");
				System.out.println("--------------------------------" +
				  "--------------------------");
				sigma.printAllConnections();
			}
			else if(userAction == 'G'|| userAction == 'g')
			{
				try
				{
					System.out.print("Enter source city: ");
					String sourceCity = stdin.nextLine();
					System.out.print("Enter destination city: ");
					String destCity = stdin.nextLine();
					sigma.removeConnection(sourceCity, destCity);
				}
				catch(Exception e)
				{
					System.out.println("Connection does not exist");
				}
			}
			else if(userAction == 'H'|| userAction == 'h')
			{
				System.out.print("Enter source city: ");
				String sourceCity = stdin.nextLine();
				System.out.print("Enter destination city: ");
				String destCity = stdin.nextLine();
				System.out.println(sigma.shortestPath(sourceCity, destCity));
			}
			System.out.println();
		}
		while(!(userAction == 'Q' || userAction == 'q'));
		try
		{
			FileOutputStream file = new FileOutputStream("sigma_air.obj");
			ObjectOutputStream fout = new ObjectOutputStream(file);
			fout.writeObject(sigma);
			fout.flush();
			fout.close();
		}
		catch (IOException e)
		{}
		System.out.println("SigmaAir object saved into file sigma_air.obj.");
		System.out.println("Program terminating...");
	}
}