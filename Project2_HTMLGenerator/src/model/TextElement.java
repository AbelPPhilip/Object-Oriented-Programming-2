package model;

/**
 * 
 * Represents text that can appear in an HTML document
 * @author UMCP
 *
 */
public class TextElement implements Element {
	private String text;
	//Constructor 
	public TextElement(String text) {
		super();
		this.setText(text);
	}

	@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String str = "";
	    str+= Utilities.spaces(indentation)+getText();
		return str;
	}
    //Getter method
	public String getText() {
		return text;
	}
    //Setter method
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
