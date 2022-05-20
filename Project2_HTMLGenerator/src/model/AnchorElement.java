package model;

/**
 * Represents the &lt;a&gt; tag.
 * @author UMCP
 *
 */
public class AnchorElement extends TagElement {
	private String linkText;
	private String url;
	/**
	 * COnstructor 
	 * @param url
	 * @param linkText
	 * @param attributes
	 */
	public AnchorElement(String url, String linkText, String attributes) {
		super("a",true,new TextElement(linkText),attributes==null?"href=\""+url+"\"":"href=\""+url+"\" "+attributes);
		this.setLinkText(linkText);
		this.setUrl(url);
	}
	/**
	 * Generates the HTML code 
	 */
	public String genHTML(int indentation) {
		String str = Utilities.spaces(indentation)+getStartTag()+linkText+getEndTag();
		return str;
	    
	}
    /**
     * The getter method to retreive the linked text
     * @return linkText 
     */
	public String getLinkText() {
		return linkText;
	}
    /**
     * Setter method to update the linkText string literal 
     * @param linkText String literal
     */
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
    /**
     * Getter Method to retrieve the url
     * @return url String literal
     */
	public String getUrl() {
		return url;
	}
    /**
     * Setter method to update the url 
     * @param url String literal
     */
	public void setUrl(String url) {
		this.url = url;
	}
}
