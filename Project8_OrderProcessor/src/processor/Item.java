package processor;
/*
 * Item class that is found in each order and available items in the shop. 
 */
public class Item {
	private String name;
	private double cost;
	
	public Item(String name,double cost) {
		this. name = name; 
		this.cost = cost; 
	}
	public String getItemName() {
		return name;
	}
	
	public double getItemCost() {
		return cost;
	}
}
