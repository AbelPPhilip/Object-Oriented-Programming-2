package model;

/**
 * Represents a heading.  We can have levels 1 thru 6 (e.g., &lt;h1&gt;, &lt;h2&gt;, etc.)
 * @author UMCP
 *
 */
public class HeadingElement extends TagElement {
	/**
	 * @param content Element parameter 
	 * @param level int value to show the level of the heading
	 * @param attributes String value for the headElement attributes
	 */
    private Element content;
    private int level; 
	public HeadingElement(Element content, int level, String attributes) {
		super("h"+level,true,content,attributes);
		this.setContent(content);
		this.setLevel(level);
		super.setAttributes(attributes);
	}
	/**
	 * The getter method that returns the content of the header
	 * @return content Element value of the included element 
	 */
	public Element getContent() {
		return content;
	}
	/**
	 * the setter method that updates the content element
	 * @param content Element value that updates the class' 
	 */
	public void setContent(Element content) {
		this.content = content;
	}
	/**
	 * the getter method that returns the level
	 * @return level int value of the level of the header
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * The setter method that updates the level of the heading 
	 * @param level int value that updates the level of the heading
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * The overriden getAttribute method that returns the attributes
	 * @return attribute string literal of attributes
	 */
	public String getAttribute() {
		return super.getAttributes();
	}
	/**
	 * The setter method
	 * @param attributes updates the attributes. 
	 */
	public void setAttributes(String attributes) {
		super.setAttributes(attributes);
	}

	
	
}
