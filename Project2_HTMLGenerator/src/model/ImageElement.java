package model;

/**
 * Represents an &lt;img&gt; tag.  For this project
 * you can assume we will not update any of the attributes associated with
 * this tag.
 * @author UMCP
 *
 */
public class ImageElement extends TagElement {
	private String imageURL,alt;
	private int width, height;
	
	/**
	 * The constructor 
	 * @param imageURL string literal of the location of the image
	 * @param width int value 
	 * @param height int value
	 * @param alt String literal 
	 * @param attributes String literal
	 */
	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
		super("img", true, null, attributes);
		this.imageURL=imageURL;
		this.setWidth(width);
		this.setHeight(height);
		this.setAlt(alt);
	}
	/**
	 * The getter method
	 * @return imageURL string literal of the image location
	 */
	public String getImageURL() {
		return imageURL;
	}
	/**
	 * The getter method to return the string value of the start tag for the Image element class
	 * @return str String literal of the start tag
	 */
	public String getStartTag() {
		String str="";
		if(super.isChoice()) {
			str = "<img id="+"\""+this.getStringId()+"\" src=\""+getImageURL()+"\" width=\""+getWidth()+"\" height=\""+getHeight()+"\" alt=\""+getAlt()+"\">";
		}
		else {
			str = "<img src=\""+getImageURL()+"\" width=\""+getWidth()+"\" height=\""+getHeight()+"\" alt=\""+getAlt()+"\">";
		}	
	    return str;
	}
    /**
     * the getter method of the getAlt method
     * @return alt String literal of alt
     */
	public String getAlt() {
		return alt;
	}
    /**
     * The setter method of the 
     * @param alt String literal to update the alt value
     */
	public void setAlt(String alt) {
		this.alt = alt;
	}
    /**
     * the getter method that returns the width
     * @return width the int value of width
     */
	public int getWidth() {
		return width;
	}
    /**
     * The setter method that updates the setWidth
     * @param width int value that updates the class's width
     */
	public void setWidth(int width) {
		this.width = width;
	}
    /**
     * The getter method that returns the height
     * @return height int value of the class' height value
     */
	public int getHeight() {
		return height;
	}
    /**
     * the setter method that updates the the height
     * @param height int value that updates the height value 
     */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return str the string literal of the html
	 */
	public String genHTML(int indentation) {
		String str = Utilities.spaces(indentation)+getStartTag();
		return str; 
	}

}
