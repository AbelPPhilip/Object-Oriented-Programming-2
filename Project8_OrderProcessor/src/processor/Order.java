package processor;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Order implements Comparable<Order>{
	/*
	 * Instance variables for Order Class
	 */
	private int clientId;
	private TreeMap<String, Integer> items;
	private ArrayList<Item> itemsData; 
	
	/*
	 * Constructor
	 */
	public Order(int clientId, TreeMap<String, Integer> orders, ArrayList<Item> items2) {
		this.clientId = clientId;
		this.itemsData = items2;
		this.items = orders;	
	}
	
	/*
	 * retrive clientID
	 */
	public int getClientId() {
		return clientId;
	}
	
    /*
     * Calculating the total cost of one order. 
     */
	public double totalCost() {
		double cost = 0;
		for (Map.Entry<String,Integer> entry : items.entrySet()) //using map.entrySet() for iteration  
		{  
			for (Item i : itemsData) {
				if (i.getItemName().equals(entry.getKey())){
					cost+=i.getItemCost()*entry.getValue();
				}
			}
		}    
		return cost; 
	}
	
	/*
	 * The summary for one order. 
	 */
	public String toString() {
		String result = "";
		result += "----- Order details for client with Id: " + (clientId) + " -----"+"\n";
		for (String s : items.keySet()) {
			for (Item i : itemsData) {
				if (i.getItemName().equals(s)){
					result += "Item's name: " + s + ", Cost per item: " + NumberFormat.getCurrencyInstance().format(i.getItemCost())
							+ ", Quantity: " + items.get(s) + ", Cost: "
							+ NumberFormat.getCurrencyInstance().format((double)(i.getItemCost() * items.get(s)));
					result += "\n";
				}
			}
		}
		result += "Order Total: " + NumberFormat.getCurrencyInstance().format(totalCost())+"\n";
		return result; 

	}
    
	/*
	 * CompareTo method inorder to use TreeSet
	 */
	@Override
	public int compareTo(Order o) {
		// TODO Auto-generated method stub
		return (int)(this.clientId)-(o.clientId);
	}
	

}

