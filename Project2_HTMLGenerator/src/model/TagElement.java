package model;

/**
 * 
 * Represents an HTML tag element (<&lt;p&gt;, &lt;ul&gt;, etc.).
 * Each tag has an id (ids start at 1).  By default the start tag
 * will have an id (e.g., <&lt;p id="a1"&gt;&lt;/p&gt;) when
 * the HTML for the tag is generated.  This can be disabled by
 * using enableId.
 * @author UMCP
 *
 */
public class TagElement implements Element {
	private String tagName;
	private boolean endTag;
	private static boolean choice  = true;
	Element content;
	private String attributes;
	protected static int id=0;
	
	/*
	 * TagElement construcor taking in 4 important parameters that allow for creation of the code. 
	 */
	public TagElement(String tagName,boolean endTag,Element content, String attributes) {
		this.setTagName(tagName);
		this.setEndTag(endTag);
		this.content=content;
		this.setAttributes(attributes);
        id++;
	}
    /**
     * 
     * @return tagName string
     */
	public String getTagName() {
		return tagName;
	}
    /**
     * The setTagName method
     * @param tagName string that changes the tag name
     */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
    /**
     * The boolean isEndTag method
     * @return endTag boolean value wether an endTag 
     */
	public boolean isEndTag() {
		return endTag;
	}
    /**
     * The setter method, 
     * @param endTag sets wether an endTag exists or not
     */
	public void setEndTag(boolean endTag) {
		this.endTag = endTag;
	}
    /**
     * The method that return the string value of the Attributes
     * @return attributes string literal
     */
	public String getAttributes() {
		return attributes;
	}
    /**
     * Setter method that updates the attributes
     * @param attributes string literal
     */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
    
	@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String str = Utilities.spaces(indentation)+getStartTag()+content.genHTML(0)+getEndTag();
		return str;
	}
	/**
	 * The static method that enables id 
	 * @param choice the boolean that sets the id to show or not 
	 */
	public static void enableId(boolean choice) {
		TagElement.setChoice(choice);
	}
	/**
	 * The getter method that returns the endTag
	 * @return the string value of the endTag
	 */
	public String getEndTag() {
		String str ="";
		if (endTag==false) {
			return str;
		}
		str+="</"+tagName+">";
		return str;
	} 
	/**
	 * The static method that returns the numerical value of the id
	 * @return id int value
	 */
	public static int getId() {
		return id;
	}
	/**
	 * The getter method that retrieves an element
	 * @return element on which the method is called. 
	 */
	public Element getElement() {
		return content;
	}
	/**
	 * The getter method that retrieves the string start tag
	 * @return str literal that contains the start tag
	 */
	public String getStartTag() {
		String str = "<"+tagName;
		attributes=(attributes==null?"":attributes);
		attributes=(attributes.isEmpty()?"":" "+attributes);
		if(!choice) {
			str+=attributes+">";
			return str;
		}
		str+=" id=\""+getStringId()+"\""+attributes+">";
		return str;
	}
	/**
	 * The getter method that retrieves the String literal value of the id
	 * @return tagName+getID() literal value 
	 */
	public String getStringId() {
		return tagName + getId();
	}
	/**
	 * The get static method that resets the whole id;
	 */
	public static void resetIds() {
		id=0;
	}
	/**
	 * The boolean method that returns the choice value. 
	 * @return
	 */
	public static boolean isChoice() {
		return choice;
	}
	/**
	 * The setter method for choice value
	 * @param choice boolean value.
	 */
	public static void setChoice(boolean choice) {
		TagElement.choice = choice;
	}	
}