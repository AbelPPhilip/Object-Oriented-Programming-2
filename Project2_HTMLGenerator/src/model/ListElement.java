package model;

import java.util.ArrayList;

/**
 * Represents the &lt;ul&gt and the &lt;ol&gt; tags.
 * An ArrayList is used to keep track of the Element objects of the list.
 * @author UMCP
 *
 */
public class ListElement extends TagElement {
	private ArrayList<Element> items;
	/**
	 * Constructor
	 * @param ordered boolean
	 * @param attributes String literal
	 */
	public ListElement(boolean ordered, String attributes) {
          super(ordered?"ol":"ul",true,null,attributes);
          items = new ArrayList<Element>();
	}
	/**
	 * the method to add items to the list. 
	 * @param item Element to be added to the list
	 */
	public void addItem(Element item) {
		if(items!=null) {
			items.add(item);
		}
	    if(item instanceof TagElement) {
	    	id--;
	    }
	}
	/**
	 * The generator method for ListElement HTMl code 
	 */
	public String genHTML(int indentation) {
		String str = Utilities.spaces(indentation)+getStartTag();
		for(int i= 0;i<items.size();i++) {
			if(items.get(i) instanceof TagElement) {
				id++;
			}
			str+="\n"+Utilities.spaces(indentation+3)+"<li>\n"+items.get(i).genHTML(indentation+6)+"\n"+Utilities.spaces(indentation+3)+"</li>";
		}
		str+="\n"+Utilities.spaces(indentation)+getEndTag();
		return str;
	}
}
