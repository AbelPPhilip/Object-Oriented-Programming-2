/*
 * Abel P. 
 */
package processor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;

public class OrdersProcessor {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		/*
		 * Local variables to keep track of the data. 
		 */
		ArrayList<Item> itemsData;
		TreeMap<Integer,TreeMap<String, Integer>> calculatedMap;
		TreeSet<Order>orders;
		//Scanner object 
		Scanner scanner = new Scanner(System.in);
		//Initializing Map
		calculatedMap = new TreeMap<Integer, TreeMap<String, Integer>>();
		
		String fileData;
		String file;
		System.out.println("Enter item's data file name:");
		file = scanner.nextLine();
	
	    //Retreiving data of each item 	
		itemsData = new ArrayList<Item>();
		try {
			fileData = Files.readString(Paths.get(file));
			
			//Creates an array consisting of lines 
			String[] items = fileData.split("\n");
			for (String item : items) {
				String[] line = item.split(" ");
				itemsData.add(new Item(line[0],Double.parseDouble(line[1])));
			}
		}
		catch (IOException e) {
			System.out.print(e.getMessage());
		}
		
		//Determining whether multi-threading needed
		boolean multiThread;
		System.out.println("Enter 'y' for multiple threads, any other character otherwise: ");
		if (scanner.nextLine().equals("y")) {
			multiThread = true;
		} else {
			multiThread = false;
		}
		
		//Receiving inputs from user
		int orderNo;
		String base, resultFile;
		System.out.println("Enter number of orders to process: ");
		orderNo = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter order's base filename: ");
		base = scanner.nextLine();
		System.out.println("Enter result's filename: ");
		resultFile = scanner.nextLine();
		scanner.close();
		
		//Starting the recording 
		long startTime = System.currentTimeMillis();
		
		
		orders = new TreeSet<Order>();
		Income totalIncome = new Income(0);
		//Creating an array of threads for execution
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 1; i <=orderNo;i++) {
			//threads.add(new Thread(new Order(i, new TreeMap<String, Integer>(),input, base)));
			threads.add(new Thread (new OrderThread(i,base, itemsData,totalIncome,orders,calculatedMap)));
		}
		
		//Running multithreads or single threads. 
		if (multiThread) {
			for (Thread thread : threads) {
				thread.start();
			}
			for (Thread thread : threads) {
				thread.join();
			}
		}else {	
			for (Thread t : threads) {
				t.start();
				t.join();
			}
		}
		
		//write to new file
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(resultFile));
		TreeMap<String, Integer> finalTable = new TreeMap<String, Integer>();//Map to combine all the orders
        
		//Filling in the final data into the Final table. 
		String summary = "";
		for (Order o : orders) {
			summary+=o.toString();
		}
		//Initializing Map values with 0 to add onto later
		for (int i :calculatedMap.keySet()) {
			for (String s : calculatedMap.get(i).keySet()) {
				finalTable.put(s,0);
			}
		}
		for (int i : calculatedMap.keySet()) {
			for (String s : calculatedMap.get(i).keySet()) {
				finalTable.put(s,finalTable.get(s)+calculatedMap.get(i).get(s));
			}
		}
		//Writing the summary of the orders. 
		summary += "***** Summary of all orders *****";
		summary += "\n";
		for(String s : finalTable.keySet()) {
			Item item = null;
			for (Item i : itemsData) {
				if (i.getItemName().equals(s)) {
					item = i; 
				}
			}
			summary += "Summary - Item's name: " + item.getItemName() 
					+ ", Cost per item: " + NumberFormat.getCurrencyInstance().format(item.getItemCost()) 
					+ ", Number sold: " + finalTable.get(s) + ", Item's Total: " + NumberFormat.getCurrencyInstance().format((double)(item.getItemCost() * finalTable.get(s))) 
					+"\n";
		}
		
		summary += "Summary Grand Total: " + NumberFormat.getCurrencyInstance().format(totalIncome.getValue())+"\n";
        
		
		
		
		fileWriter.append(summary);
		fileWriter.close();
		
		long endTime = System.currentTimeMillis();
		//Total Time Taken 
		long totalTime = endTime - startTime;
		
		System.out.println("Processing Time(msec):"+totalTime);
		System.out.println("Results are found in"+resultFile);
	}
}
