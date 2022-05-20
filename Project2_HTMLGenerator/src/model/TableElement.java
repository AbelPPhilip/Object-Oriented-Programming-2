package model;

/**
 * Represents the &lt;table&gt tag.
 * A two dimensional array is used to keep track of the Element objects of table.
 * @author UMCP
 *
 */
public class TableElement extends TagElement {
	private Element[][] items;
	/**
	 * Constructor
	 * @param rows int 
	 * @param cols int 
	 * @param attributes string
	 */
	public TableElement(int rows, int cols, String attributes) {
		super("table",true,null,attributes);
		items = new Element[rows][cols];
	}
	/**
	 * The method to add items to specific row and column indexes
	 * @param rowIndex int 
	 * @param colIndex int 
	 * @param item int 
	 */
	public void addItem(int rowIndex, int colIndex, Element item) {
		if(rowIndex<0||colIndex<0||item==null) {
			throw new IllegalArgumentException("Enter Valid Parameters");
		}
		if(rowIndex<items.length||colIndex<items[0].length) {
			items[rowIndex][colIndex]=item;
		}
		if(item instanceof TagElement) {
			id--;
		}
	}
	/**
	 * The method that generates the html code
	 */
	public String genHTML(int indentation) {
		String str=Utilities.spaces(indentation)+ super.getStartTag();
		for(int i=0;i<items.length;i++) {
			str+="\n"+Utilities.spaces(indentation+3)+"<tr>";
			for(int j=0; j<items[i].length;j++) {
				if(items[i][j]!=null) {
					if(items[i][j] instanceof TagElement) {
						id++;
					}
					str+="<td>"+items[i][j].genHTML(0)+"</td>";
				}
				else {
					str+="<td></td>";
				}
			}
			str+="</tr>";
		}
		str+="\n"+Utilities.spaces(indentation)+super.getEndTag();
		return str;
	}
	/**
	 * The table utilization method that returns the percentage of the table used
	 * @return utilization*100 the percentage of the table utilized. 
	 */
	public double getTableUtilization() {
		double counter=0.0;
		if(items==null || items.length<=0){ 
			return 0.0;
		}
		else {
			for(int i=0;i<items.length;i++) {
				for(int j =0;j<items[i].length;j++) {
					if (!(items[i][j]==null)) {
						counter++;
					}
				}
			}
		}
		double utilization = counter/(items.length*items[0].length);
		return utilization*100;
		
	}
		
}
