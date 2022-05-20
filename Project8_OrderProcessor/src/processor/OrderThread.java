package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.text.NumberFormat;

public class OrderThread implements Runnable{
	/*
	 * Instance variables
	 */
	private String base; 
	private int num; 
	private TreeSet <Order> orders;
	private TreeSet <Order> totalOrders;
	private ArrayList <Item> items;
	private TreeMap <String, Integer> orderItems;  
	private TreeMap<Integer, TreeMap<String, Integer>> calculatedMap;//Map of Number of order and a treeMap of Items and their quantity
	
	private Income totalIncome;
	
	/*
	 * Constructor for OrderThread
	 */
	public OrderThread(int num, String fileName,ArrayList<Item> itemsData, Income totalIncome, TreeSet<Order> totalOrders,TreeMap<Integer,TreeMap<String, Integer>>calculatedMap ) {
		this.num = num; 
		this.orderItems = new TreeMap<String, Integer>();
		orders = new TreeSet<Order>();
		this.base = fileName; 
		this.items = itemsData;
		this.totalIncome = totalIncome;
		//The following variables are to update the sets and maps in the OrderProcessor
		this.totalOrders = totalOrders;
		this.calculatedMap = calculatedMap;
	}
	
	
	/*
	 * The readFile() method that reads a file and returns treeSet of Orders
	 */
	private TreeSet<Order> readFile() throws IOException{
        TreeSet<Order> clientOrders = new TreeSet<Order>();
        //Buffered Reader to read input from a file.
		BufferedReader bufferedReader = new BufferedReader(new FileReader(base + num +".txt"));
		String line = bufferedReader.readLine();
		//Retrieving a clientId
		int clientId = Integer.parseInt(line.split(":")[1].trim());
		System.out.println("Reading order for client with id: "+clientId);
		//While loop to read the whole file. 
		line = bufferedReader.readLine();
		while (line != null) {
			String key = line.split(" ")[0];
			if (orderItems.containsKey(key)) {
				int price = orderItems.get(key);
				orderItems.put(key, price+1);
			}
			else { 
				orderItems.put(key,1);
			}
			line = bufferedReader.readLine();
		}
	    //Adding Orders to the set of Orders. 
		clientOrders.add(new Order(clientId,orderItems,items));
		bufferedReader.close();
		return clientOrders;
	}
	
	//Returns the Total income of the Order
	public double totalIncome() {
		return totalIncome.getValue();
	}
		
	
	/*
	 * Overriden run file that allows multithreading. 
	 */
	@Override
	public void run() {
		double cost=0;
		try {
			this.orders = this.readFile();
			//The Lock in this program is the orders in the OrderProcessor folder. 
			synchronized (totalOrders) {
				for (Order o : orders) {
					totalOrders.add(o);
					calculatedMap.put(num, orderItems);
				}
			}
			//Calculating total cost. 
			for (Order o : orders) {
				cost+=o.totalCost();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
		//Another lock is the commonly shared TotalIncome 
		synchronized(totalIncome) {
			totalIncome.add(cost);
		}
	}
	
}
