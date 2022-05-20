package model;

import java.util.ArrayList;

/**
 * 
 * Represents a paragraph (&lt;p&gt;) tag.  It relies on an
 * ArrayList in order to keep track of the set of Element objects
 * that are part of the paragraph.
 * @author UMCP
 *
 */
public class ParagraphElement extends TagElement {
	private ArrayList<Element> items;
	String attributes; 
	String str ="";
	/**
	 * Constructor
	 * @param attributes String literal
	 */
	public ParagraphElement(String attributes) {
		super("p",true,null,attributes);
		this.attributes = attributes;
		items = new ArrayList<Element>();
		
	}
	/**
	 * The method to add items to the paragraph
	 * @param item Element object 
	 */
	public void addItem(Element item) {
		if(items!=null) {
			items.add(item);
			if(item instanceof TagElement) {
				id--;//Backtracks the id so id doesnt update before the details are printed
			}
		}
	}
	/**
	 * The generator method of html for the paragraph element
	 */
	public String genHTML(int indentation) {
		String str = "";
		str = Utilities.spaces(indentation)+getStartTag()+"\n";
		for (int i = 0; i<items.size();i++) {
			if(items.get(i) instanceof TagElement) {
				id++;//The backtracked id is restored and the correct id numbers can be printed
			}
			str+= Utilities.spaces(indentation+3)+items.get(i).genHTML(0);
			str+="\n";
		}
		str+=Utilities.spaces(indentation)+getEndTag();
		return str;
	}
}
