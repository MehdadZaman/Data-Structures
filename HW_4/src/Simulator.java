import java.util.Scanner;
/*	
 * The Simulator class tests the Bus class, the Passenger class and the 
 *   PassengerQueue class by asking the user for to enter attributes that
 *   would simulate an actual bus simulation. The user would be asked
 *   for the duration of the simulation, and the probability of a passenger
 *   entering a stop. This class contains several methods including the main 
 *   method, that allows the user to interact with the simulation. There is 
 *   also a method that simulates the game and a random stop and boolean
 *   generator.
 * 
 * @author Mehdad Zaman
 * @id 112323211
 * Assignment #4
 * CSE 214 Rectitation #1
 * */
public class Simulator {
	/* 
	 * The number of busses, minimum and maximum group size, the capacity
	 *   of a bus, and the probability that a passenger would enter a stop.
	 * */
	private int numInBusses;
	private int numOutBusses;
	private int minGroupSize;
	private int maxGroupSize;
	private int capacity;
	private double arrivalProb;
	/*	
	 * Creates a simulation method.
	 * 
	 * @param numInBusses
	 *   the number of in route busses.
	 * @param numOutBusses
	 *   the number of out route busses.
	 * @param minGroupSize
	 *   the minimum group size.
	 * @param maxGroupSize
	 *   the maximum group size.
	 * @param capacity
	 *   the capacity of the bus.
	 * @param arrivalProb
	 *   the probability that a passenger would enter a bus stop.
	 * */
	public Simulator(int numInBusses, int numOutBusses, int minGroupSize, 
	  int maxGroupSize, int capacity, double arrivalProb)
	{
		this.numInBusses = numInBusses;
		this.numOutBusses = numOutBusses;
		this.minGroupSize = minGroupSize;
		this.maxGroupSize = maxGroupSize;
		this.capacity = capacity;
		this.arrivalProb = arrivalProb;
	}
	/*
	 * Simulates the bus simulation for a given amount of time.
	 * 	
	 * Precondition: duration must be an integer and should not 
	 *   exceed the maximum size of an int.
	 * Postcondition: Returns an array of the total wait time
	 *   and the number of passengers served.
	 * 
	 * @param duration
	 *   The duration of the simulation.
	 *   
	 * @return
	 *   the an array of the total wait time and the number of 
	 *   passengers served.
	 * */
	public double[] simulate(int duration) 
	{
		PassengerQueue[] busStops = new PassengerQueue[8];
		String inRoute[]  = {"South P", "West", "SAC", "Chapin"};
		String outRoute[] = {"South P", "PathMart", "Walmart", "Target"};
		int groupsServed = 0;
		int totalTimeWaited = 0;
		Bus[] inBusses = new Bus[numInBusses];
		Bus[] outBusses = new Bus[numOutBusses];
		
		for(int i = 0; i < inBusses.length; i++)
		{
			inBusses[i] = new Bus("In Route", 0 , 0, (i * 30), capacity,
			  true, false, false);
		}
		
		for(int i = 0; i < outBusses.length; i++)
		{
			outBusses[i] = new Bus("Out Route", 0 , 0, (i * 30), capacity,
			  true, false, false);
		}
		
		for(int i = 0; i < busStops.length; i++) 
		{
			busStops[i] = new PassengerQueue();
		}
		
		int destination = 0;
		int numOfPassengersinGroup = 0;
		
		int durStart = 1;
		
		int[] droppedOffPassengers = new int[numInBusses + numOutBusses];
		int[] pickedUpPassengers = new int[numInBusses + numOutBusses];
		
		while(durStart <= duration)
		{
			System.out.println("Minute " + (durStart));
			droppedOffPassengers = new int[numInBusses + numOutBusses];
			pickedUpPassengers = new int[numInBusses + numOutBusses];
			for(int i = 0; i < 8; i++) 
			{
				int[] destinations = new int[1];
				if(i == 0 || i == 4) 
				{
					destinations = new int[3];
					destinations[0] = 1;
					destinations[1] = 2;
					destinations[2] = 3;
				}
				else if(i == 1 || i == 5) 
				{
					destinations = new int[3];
					destinations[0] = 2;
					destinations[1] = 3;
					destinations[2] = 0;
				}
				else if(i == 2 || i == 6) 
				{
					destinations = new int[2];
					destinations[0] = 3;
					destinations[1] = 0;
				}
				else if(i == 3 || i == 7) 
				{
					destinations = new int[1];
					destinations[0] = 0;
				}
				destination = destinations[randInt(0, destinations.length - 1)];
				int numOfPassangersinGroup = this.randInt(this.minGroupSize,
				  this.maxGroupSize);
				if(booleanSource())
				{
					Passenger temp = new Passenger(numOfPassangersinGroup,
					  destination, durStart);
					busStops[i].enqueue(temp);
					if(i < inRoute.length)
					{
						System.out.println("A group of " + temp.getSize() +
						  " passengers arrived at " + inRoute[i] + " heading to " + 
						  inRoute[temp.getDestination()] + ".");
					}
					else
					{
						System.out.println("A group of " + temp.getSize() + 
						  " passengers arrived at " + outRoute[i - 4] + " heading to " + 
						  outRoute[temp.getDestination()] + ".");
					}
				}
			}
			for(int i = 0; i < inBusses.length; i++)
			{
				if(inBusses[i].getTimeToRest() > 0 && inBusses[i].getIsResting())
				{
					inBusses[i].setTimeToRest(inBusses[i].getTimeToRest() - 1);
				}
				else if(inBusses[i].getToNextStop() > 0 && !(inBusses[i].getIsResting()))
				{
					inBusses[i].setToNextStop(inBusses[i].getToNextStop() - 1);
				}
				
				if(inBusses[i].getTimeToRest() == 0 && inBusses[i].getIsResting())
				{
					inBusses[i].setIsAtFirstStop(true);
				}
				else if(
						(inBusses[i].getNextStop() == 0) && 
						!(inBusses[i].getIsAtFirstStop()) && 
						!(inBusses[i].getIsResting()) && 
						(inBusses[i].getToNextStop() == 0)
						)
				{
					inBusses[i].setIsAtLastStop(true);
				}
				
				if(inBusses[i].getIsAtFirstStop())
				{
					inBusses[i].setIsResting(false);
					int previousSize = inBusses[i].getNumberOfPassengers();
					droppedOffPassengers[i] = inBusses[i].unload(inBusses[i].getNextStop());
					int currentSize = inBusses[i].getNumberOfPassengers();
					int droppedPassengers = previousSize - currentSize;

					while(!(busStops[inBusses[i].getNextStop()].isEmpty()))
					{
						int tempHolder = busStops[inBusses[i].getNextStop()].peek().getSize();
						int currentPassengerIndex = 0;
						if((inBusses[i].size() + tempHolder) > this.capacity)
						{
							if(busStops[inBusses[i].getNextStop()].getNumberOfGroups() < 1)
							{
								break;
							}
							for(int j = 1; j < busStops[inBusses[i].getNextStop()].getNumberOfGroups(); j++)
							{
								if(j >= busStops[inBusses[i].getNextStop()].getNumberOfGroups())
								{
									break;
								}
								Passenger tempPasengerHolder = busStops[inBusses[i].getNextStop()].get(j);
								if(tempPasengerHolder == null)
								{
									break;
								}
								int tempHolder2 = busStops[inBusses[i].getNextStop()].get(j).getSize();
								if((inBusses[i].size() + tempHolder2) <= this.capacity)
								{
									Passenger temp = busStops[inBusses[i].getNextStop()].get(j);
									busStops[inBusses[i].getNextStop()].removeAtPosition(j);
									inBusses[i].enqueue(temp);
									pickedUpPassengers[i] += temp.getSize();
									groupsServed++;
									totalTimeWaited += durStart - temp.getArrivalTime();
								}
							}
							break;
						}
						Passenger temp = busStops[inBusses[i].getNextStop()].dequeue();
						inBusses[i].enqueue(temp);
						pickedUpPassengers[i] += temp.getSize();
						groupsServed++;
						totalTimeWaited += durStart - temp.getArrivalTime();
					}
					inBusses[i].setNextStop(0);
					inBusses[i].setIsAtFirstStop(false);
					inBusses[i].setToNextStop(0);
				}
				else if(inBusses[i].getIsAtLastStop())
				{
					int previousSize = inBusses[i].getNumberOfPassengers();
					droppedOffPassengers[i] = inBusses[i].unload(inBusses[i].getNextStop());
					int currentSize = inBusses[i].getNumberOfPassengers();
					inBusses[i].setIsResting(true);
					inBusses[i].setTimeToRest(30);
					int droppedPassengers = previousSize - currentSize;
				}
				else if(inBusses[i].getToNextStop() == 0 && !(inBusses[i].getIsResting()))
				{
					int previousSize = inBusses[i].getNumberOfPassengers();
					droppedOffPassengers[i] = inBusses[i].unload(inBusses[i].getNextStop());
					int currentSize = inBusses[i].getNumberOfPassengers();
					int droppedPassengers = previousSize - currentSize;

					while(!(busStops[inBusses[i].getNextStop()].isEmpty()))
					{
						int tempHolder = busStops[inBusses[i].getNextStop()].peek().getSize();
						if((inBusses[i].size() + tempHolder) > this.capacity)
						{
							if(busStops[inBusses[i].getNextStop()].getNumberOfGroups() < 2)
							{
								break;
							}
							for(int j = 1; j < busStops[inBusses[i].getNextStop()].getNumberOfGroups(); j++)
							{
								if(j >= busStops[inBusses[i].getNextStop()].getNumberOfGroups())
								{
									break;
								}
								Passenger tempPasengerHolder = busStops[inBusses[i].getNextStop()].get(j);
								if(tempPasengerHolder == null)
								{
									break;
								}	
								int tempHolderSize = busStops[inBusses[i].getNextStop()].get(j).getSize();
								if((inBusses[i].size() + tempHolderSize) <= this.capacity)
								{
									Passenger temp = busStops[inBusses[i].getNextStop()].get(j);
									busStops[inBusses[i].getNextStop()].removeAtPosition(j);
									inBusses[i].enqueue(temp);
									pickedUpPassengers[i] += temp.getSize();
									groupsServed++;
									totalTimeWaited += durStart - temp.getArrivalTime();
								}
							}
							break;
						}
						Passenger temp = busStops[inBusses[i].getNextStop()].dequeue();
						inBusses[i].enqueue(temp);
						pickedUpPassengers[i] += temp.getSize();
						groupsServed++;
						totalTimeWaited += durStart - temp.getArrivalTime();
					}
				}	
			}
			for(int i = 0; i < outBusses.length; i++)
			{
				if(outBusses[i].getTimeToRest() > 0 && outBusses[i].getIsResting())
				{
					outBusses[i].setTimeToRest(outBusses[i].getTimeToRest() - 1);
				}
				else if(outBusses[i].getToNextStop() > 0 && !(outBusses[i].getIsResting()))
				{
					outBusses[i].setToNextStop(outBusses[i].getToNextStop() - 1);
				}
				
				if(outBusses[i].getTimeToRest() == 0 && outBusses[i].getIsResting())
				{
					outBusses[i].setIsAtFirstStop(true);
				}
				else if(
						(outBusses[i].getNextStop() == 0) && 
						!(outBusses[i].getIsAtFirstStop()) && 
						!(outBusses[i].getIsResting()) && 
						(outBusses[i].getToNextStop() == 0)
						)
				{
					outBusses[i].setIsAtLastStop(true);
				}
				
				if(outBusses[i].getIsAtFirstStop())
				{
					outBusses[i].setIsResting(false);
					int previousSize = outBusses[i].getNumberOfPassengers();
					droppedOffPassengers[i + numInBusses] = outBusses[i].unload(outBusses[i].getNextStop());
					int currentSize = outBusses[i].getNumberOfPassengers();
					int droppedPassengers = previousSize - currentSize;

					while(!(busStops[outBusses[i].getNextStop() + 4].isEmpty()))
					{
						int tempHolder = busStops[outBusses[i].getNextStop()+4].peek().getSize();
						if((outBusses[i].size() + tempHolder) > this.capacity)
						{
							if(busStops[outBusses[i].getNextStop() + 4].getNumberOfGroups() < 2)
							{
								break;
							}
							for(int j = 1; j < busStops[outBusses[i].getNextStop() + 4].getNumberOfGroups(); j++)
							{
								if(j >= busStops[outBusses[i].getNextStop() + 4].getNumberOfGroups())
								{
									break;
								}
								Passenger tempPasengerHolder = busStops[inBusses[i].getNextStop() + 4].get(j);
								if(tempPasengerHolder == null)
								{
									break;
								}
								int tempHolder2 = busStops[outBusses[i].getNextStop() + 4].get(j).getSize();
								if((outBusses[i].size() + tempHolder2) <= this.capacity)
								{
									Passenger temp = busStops[outBusses[i].getNextStop() + 4].get(j);
									busStops[outBusses[i].getNextStop()].removeAtPosition(j);
									outBusses[i].enqueue(temp);
									pickedUpPassengers[i + numInBusses] += temp.getSize();
									groupsServed++;
									totalTimeWaited += durStart - temp.getArrivalTime();
								}
							}
							break;
						}
						Passenger temp = busStops[outBusses[i].getNextStop() + 4].dequeue();
						outBusses[i].enqueue(temp);
						pickedUpPassengers[i + numInBusses] += temp.getSize();
						groupsServed++;
						totalTimeWaited += durStart - temp.getArrivalTime();
					}
					outBusses[i].setNextStop(0);
					outBusses[i].setIsAtFirstStop(false);
					outBusses[i].setToNextStop(0);
				}
				else if(outBusses[i].getIsAtLastStop())
				{
					int previousSize = outBusses[i].getNumberOfPassengers();
					droppedOffPassengers[i + numInBusses] = outBusses[i].unload(outBusses[i].getNextStop());
					int currentSize = outBusses[i].getNumberOfPassengers();
					outBusses[i].setIsResting(true);
					outBusses[i].setTimeToRest(30);
					int droppedPassengers = previousSize - currentSize;
				}
				else if(outBusses[i].getToNextStop() == 0 && !(outBusses[i].getIsResting()))
				{
					int previousSize = outBusses[i].getNumberOfPassengers();
					droppedOffPassengers[i + numInBusses] = outBusses[i].unload(outBusses[i].getNextStop());
					int currentSize = outBusses[i].getNumberOfPassengers();
					int droppedPassengers = previousSize - currentSize;
					
					while(!(busStops[outBusses[i].getNextStop() + 4].isEmpty()))
					{
						int tempHolder = busStops[outBusses[i].getNextStop()+4].peek().getSize();
						if((outBusses[i].size() + tempHolder) > this.capacity)
						{
							if(busStops[outBusses[i].getNextStop() + 4].getNumberOfGroups() < 2)
							{
								break;
							}
							for(int j = 1; j < busStops[outBusses[i].getNextStop() + 4].getNumberOfGroups(); j++)
							{
								if(j >= busStops[outBusses[i].getNextStop() + 4].getNumberOfGroups())
								{
									break;
								}
								Passenger tempPasengerHolder = busStops[inBusses[i].getNextStop() + 4].get(j);
								if(tempPasengerHolder == null)
								{
									break;
								}
								int tempHolder2 = busStops[outBusses[i].getNextStop() + 4].get(j).getSize();
								if((outBusses[i].size() + tempHolder2) <= this.capacity)
								{
									Passenger temp = busStops[outBusses[i].getNextStop() + 4].get(j);
									busStops[outBusses[i].getNextStop()].removeAtPosition(j);
									outBusses[i].enqueue(temp);
									pickedUpPassengers[i + numInBusses] += temp.getSize();
									groupsServed++;
									totalTimeWaited += durStart - temp.getArrivalTime();
								}
							}
							break;
						}
						Passenger temp = busStops[outBusses[i].getNextStop()+4].dequeue();
						outBusses[i].enqueue(temp);
						pickedUpPassengers[i + numInBusses] += temp.getSize();
						groupsServed++;
						totalTimeWaited += durStart - temp.getArrivalTime();
					}
				}	
			}

			for(int i = 0; i < inBusses.length; i++)
			{
				if(inBusses[i].getIsAtLastStop())
				{
					System.out.println("Out Route Bus " + (i+1) + " arrived at " 
					  + "South P.");
					inBusses[i].setIsAtLastStop(false);
				}
				else if(inBusses[i].isResting)
				{
					System.out.print("In Route bus " + (i+1) + " is resting at "
					  + "South P for " + inBusses[i].getTimeToRest() + " minutes.");
				}
				else if(inBusses[i].getToNextStop() == 0)
				{
					System.out.print("In Route Bus " + (i+1) + " arrived at " 
					  + inRoute[inBusses[i].getNextStop()] + ".");
					if(droppedOffPassengers[i] > 0)
					{
						System.out.print(" Drops off " + droppedOffPassengers[i] +
						  " passengers.");
					}
					if(pickedUpPassengers[i] > 0)
					{
						System.out.print(" Picks up " + pickedUpPassengers[i] +
						  " passengers.");
					}
					inBusses[i].setToNextStop(20);
					inBusses[i].setNextStop((inBusses[i].getNextStop() + 1) % inRoute.length);
				}
				else if(inBusses[i].getToNextStop() != 0)
				{
					System.out.print("In Route Bus " + (i+1) + " moving towards " +
					  inRoute[inBusses[i].getNextStop()] + ". Arrives in " + 
					  inBusses[i].getToNextStop() + " minutes.");
				}
				System.out.println();
			}
			
			for(int i = 0; i < outBusses.length; i++)
			{
				if(outBusses[i].getIsAtLastStop())
				{
					System.out.print("Out Route Bus " + (i+1) + " arrived at " 
					  + "South P.");
					outBusses[i].setIsAtLastStop(false);
				}
				else if(outBusses[i].isResting)
				{
			    	System.out.print("Out Route bus " + (i+1) + " is resting at "
					  + "South P for " + outBusses[i].getTimeToRest() + " minutes.");
				}
				else if(outBusses[i].getToNextStop() == 0)
				{
					System.out.print("Out Route Bus " + (i+1) + " arrived at  " 
					  + outRoute[outBusses[i].getNextStop()] + ".");
					if(droppedOffPassengers[i + numInBusses] > 0)
					{
						System.out.print(" Drops off " + 
						  droppedOffPassengers[i + numInBusses] + 
						  " passengers.");
					}
					if(pickedUpPassengers[i + numInBusses] > 0)
					{
						System.out.print(" Picks up " + 
						  pickedUpPassengers[i + numInBusses] + " passengers.");
					}
					outBusses[i].setToNextStop(20);
					outBusses[i].setNextStop((outBusses[i].getNextStop()+1)%outRoute.length);
				}
				else if(outBusses[i].getToNextStop() != 0)
				{
					System.out.print("Out Route Bus " + (i+1) + " moving towards " +
					  outRoute[outBusses[i].getNextStop()] + ". Arrives in " + 
					  outBusses[i].getToNextStop() + " minutes.");
				}
				System.out.println();
			}
			
			for(int i = 0; i < busStops.length; i++) 
			{
				System.out.print((i) + " (" + 
				  ((i < inRoute.length) ? inRoute[i] : outRoute[i - inRoute.length]) +
				  "):");
				int j = 0;
				while(j < busStops[i].getNumberOfGroups())
				{
					System.out.print("[" + busStops[i].get(j).getSize() + ", " + 
					  busStops[i].get(j).getDestination() +
					  " (");
					if(i < inRoute.length)
					{
						System.out.print(inRoute[busStops[i].get(j).getDestination()]);
					}
					else
					{
						System.out.print(outRoute[busStops[i].get(j).getDestination()]);
					}
					System.out.print("), " + busStops[i].get(j).getArrivalTime() + "]");
					j++;
					System.out.print(" ");
				}
				System.out.println();
			}		
			durStart++;
			System.out.println();
		}
		double[] arrayReturn = {groupsServed, totalTimeWaited};
		return arrayReturn;
	}
	/*	
	 * The main driver method that tests the other classes. 
	 *   The method acquires user input about attributes for the simulation
	 *   This method uses the simulate() method for a given duration.
	 *   
	 * Precondition: none
	 * Postcondition: Prints out the simulation for the user.
	 * */
	public static void main(String[] args)
	{
		char noTerminate = 'Y';
		do
		{
			Scanner stdin = new Scanner(System.in);
			
			int numInBusses = -1;
			do
			{
				System.out.print("Enter the number of In Route busses: ");
				numInBusses = stdin.nextInt();
				if(numInBusses < 0)
				{
					System.out.println("Invalid input. Should be greater than" +
					  " or equal to 0.");
				}
			}
			while(numInBusses < 0);
			
			int numOutBusses = -1;
			do
			{
				System.out.print("Enter the number of Out Route busses: ");
				numOutBusses = stdin.nextInt();
				if(numOutBusses < 0)
				{
					System.out.println("Invalid input. Should be greater " +
					  "than or equal to 0.");
				}
			}
			while(numOutBusses < 0);
			
			int minGroupSize = -1;
			do
			{
				System.out.print("Enter the minimum group size of passengers: ");
				minGroupSize = stdin.nextInt();
				if(minGroupSize < 0)
				{
					System.out.println("Invalid input. Should be greater than" +
					  " or equal to 0.");
				}
			}
			while(minGroupSize < 0);
				
			int maxGroupSize = -1;
			do
			{
				System.out.print("Enter the maximum group size of passengers: ");
				maxGroupSize = stdin.nextInt();
				if(maxGroupSize < minGroupSize)
				{
					System.out.println("Invalid input. Should be greater than or" +
					  " equal to min group size.");
				}
			}
			while(maxGroupSize < minGroupSize);
			
			int capacity = -1;
			do
			{
				System.out.print("Enter the capacity of a bus: ");
				capacity = stdin.nextInt();
				if(capacity < maxGroupSize)
				{
					System.out.println("Invalid input. Should be greater than or" +
					  " equal to max group size.");
				}
			}
			while(capacity < maxGroupSize);
			
			double arrivalProb = -1;
			do
			{
				System.out.print("Enter the arrival probability: ");
				arrivalProb = stdin.nextDouble();
				if((arrivalProb < 0) || (arrivalProb > 1))
				{
					System.out.println("Invalid input. Should be greater than or" +
					  " equal to 0 and less than or equal to 1.");
				}
			}
			while((arrivalProb < 0) || (arrivalProb > 1));
			
			int duration = -1;
			do
			{
				System.out.print("Enter the duration of the simulation: ");
				duration = stdin.nextInt();
				if(duration < 0)
				{
					System.out.println("Invalid input. Should be greater than" +
					  " or equal to 0.");
				}
			}
			while(duration < 0);
					
			Simulator userSimulation = new Simulator(numInBusses, numOutBusses,
			  minGroupSize, maxGroupSize, capacity, arrivalProb); 
			
			double[] userSim = userSimulation.simulate(duration);
			
			double averageWaitTime = userSim[1] / userSim[0];
			if(userSim[0] > 0)
			{
				System.out.println(userSim[0] + " groups of passengers served." +
				  " Average wait time is " + averageWaitTime + " minutes.");
			}
			else
			{
				System.out.println("0 groups of passengers served. Average wait" +
				  " time is NaN.");
			}
			System.out.print("Perform another simulation(Y/N):");
			noTerminate = stdin.next().charAt(0);
		}
		while(noTerminate == 'y' || noTerminate == 'Y');
		
		System.out.println("Program terminating...");
	}
	/*	
	 * A random number generator.
	 * 
	 * Precondition: min and max must be integers
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: Returns a random number.
	 * 
	 * @param min
	 *   the minimum number in the range.
	 * @param max
	 *   the maximum number in the range.
	 *   
	 * @return 
	 *   a random number.
	 * */
	private int randInt(int min, int max)
	{
		int randomValue = min + (int)(Math.random() * ((max - min) + 1));
		return randomValue;
	}
	/*	
	 * A random boolean generator.
	 * 
	 * Precondition: none
	 * Postcondition: Returns true or false.
	 * 
	 * @return 
	 *   Returns true or false.
	 * */
	private boolean booleanSource()
	{
		double randomValue = Math.random();
		return randomValue < this.arrivalProb; 
	}
	/*	
	 * Returns the number of in route busses.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of in route busses.
	 * 
	 * @return 
	 *   Returns the number of in route busses.
	 * */
	public int getNumInBusses()
	{
		return this.numInBusses;
	}
	/*
	 * Assigns the number of in route buses.
	 * 	
	 * Precondition: numInBusses must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param numInBusses
	 *   The number of in route buses.
	 * */
	public void setNumInBusses(int numInBusses)
	{
		this.numInBusses = numInBusses;
	}
	/*	
	 * Returns the number of out route busses.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the number of out route busses.
	 * 
	 * @return 
	 *   Returns the number of out route busses.
	 * */
	public int getNumOutBusses()
	{
		return this.numOutBusses;
	}
	/*
	 * Assigns the number of out route buses.
	 * 	
	 * Precondition: numOutBusses must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param numOutBusses
	 *   The number of out route buses.
	 * */
	public void setNumOutBusses(int numOutBusses)
	{
		this.numOutBusses = numOutBusses;
	}
	/*	
	 * Returns the minimum number of individuals per group.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the minimum number of individuals 
	 *   per group.
	 * 
	 * @return 
	 *   Returns the minimum number of individuals per group.
	 * */
	public int getMinGroupSize()
	{
		return this.minGroupSize;
	}
	/*
	 * Assigns the minimum number of individuals per group.
	 * 	
	 * Precondition: minGroupSize must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param minGroupSize
	 *   The minimum number of individuals per group.
	 * */
	public void setMinGroupSize(int minGroupSize)
	{
		this.minGroupSize = minGroupSize;
	}
	/*	
	 * Returns the maximum number of individuals per group.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the maximum number of individuals 
	 *   per group.
	 * 
	 * @return 
	 *   Returns the maximum number of individuals per group.
	 * */
	public int getMaxGroupSize()
	{
		return this.maxGroupSize;
	}
	/*
	 * Assigns the maximum number of individuals per group.
	 * 	
	 * Precondition: minGroupSize must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param maxGroupSize
	 *   The maximum number of individuals per group.
	 * */
	public void setMaxGroupSize(int maxGroupSize)
	{
		this.maxGroupSize = maxGroupSize;
	}
	/*	
	 * Returns the capacity of the busses.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the capacity of the busses.
	 * 
	 * @return 
	 *   Returns the capacity of the busses.
	 * */
	public int getCapacity()
	{
		return this.capacity;
	}
	/*
	 * Assigns a new capacity
	 * 	
	 * Precondition: capacity must be an integer 
	 *   and should not exceed the maximum size of an int.
	 * Postcondition: none
	 * 
	 * @param capacity
	 *   The capacity of the bus.
	 * */
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	/*	
	 * Returns the probability of a passenger entering a stop.
	 * 
	 * Precondition: none
	 * Postcondition: Returns the probability of a passenger entering
	 *   a stop.
	 * 
	 * @return 
	 *   Returns the probability of a passenger entering a stop.
	 * */
	public double getArrivalProb()
	{
		return this.arrivalProb;
	}
	/*
	 * Assigns a probability of a passenger entering a stop.
	 * 	
	 * Precondition: arrivalProb should not exceed 
	 *   the maximum size of a double.
	 * Postcondition: none
	 * 
	 * @param arrivalProb
	 *   The probability of a passenger entering a stop.
	 * */
	public void setArrivalProb(double arrivalProb)
	{
		this.arrivalProb = arrivalProb;
	}
}