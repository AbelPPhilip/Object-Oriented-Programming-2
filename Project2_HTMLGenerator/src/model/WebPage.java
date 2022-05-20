package model;

import java.util.*;

/**
 * Represents a web page.  Web page elements are
 * stored in an ArrayList of Element objects.  A title
 * is associated with every page.  This class implements
 * the Comparable interface.  Pages will be compared
 * based on the title.
 * @author UMCP
 *
 */
public class WebPage implements Comparable<WebPage> {
	private ArrayList<Element> elements;
	private String title;
	//Constructor for Webpage 
	/**
	 * The Webpage Constructor
	 * @param title String literal
	 */
	public WebPage(String title) {
		this.title = title;
		elements = new ArrayList<Element>();
	}
	/**
	 * The method that adds elements to the webpage 
	 * @param element objects 
	 * @return -1 if none are added
	 */
	public int addElement(Element element) {
		if (element instanceof TagElement) {
			elements.add(element);
		}
		
		return -1;
	}
	//The main code to received HTML code for the Webpage
	/**
	 * The method that generates the HTML code for the website 
	 * @param indentation int value 
	 * @return str literal of the whole HTML
	 */
	public String getWebPageHTML(int indentation) {
		String str = "<!doctype html>\n<html>\n";
		str+=getHeading(indentation);
	    str+=getBody(indentation);
	    str+="\n</html>";
		return str;
	}
	/**
	 * The method that generates the HTMl of the heading section of the webpage 
	 * @param indentation int value
	 * @return str literal of the heading section html code
	 */
	public String getHeading(int indentation) {
		String str = Utilities.spaces(indentation)+"<head>\n"+Utilities.spaces(indentation*2)+"<meta charset=\"utf-8\"/>\n";
		str+= Utilities.spaces(indentation*2)+"<title>"+title+"</title>\n";
		str+=Utilities.spaces(indentation)+"</head>\n";
		return str;
	}
	/**
	 * The method to receive the html of the body section of the webpage
	 * @param indentation int value
	 * @return str with the HTML code of the body 
	 */
	public String getBody(int indentation) {
		String str = Utilities.spaces(indentation)+"<body>\n";
		if (elements !=null || !(elements.isEmpty())) {
			for (int i = 0; i<elements.size();i++) {
				str+=elements.get(i).genHTML(indentation)+"\n";
			}
		}
		str+=Utilities.spaces(indentation)+"</body>";
		return str;
	}
	/**
	 * The method to write the generated html to the file given
	 * @param filename string file name 
	 * @param indentation int value 
	 */
	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename, this.getWebPageHTML(indentation));//method call from the Utilities class
	}
	//The method that returns the title of the Webpage
	public String getTitle() {
		return title;
	}
	//The method 
	public Element findElem(int id) {
		for (int i = 0; i<elements.size();i++) {
			TagElement t = (TagElement) elements.get(i);
			if (TagElement.getId()==id) { 
				return elements.get(i);
			}
		}
		return null;
	}
	/**
	 * The method that retreives the stats. 
	 * @return str The string literal of all the states
	 */
	public String stats() {
		int para=0,list=0,table=0;
		double tablecounters = 0.0;//The total of the percentages 
		for(int i = 0;i<elements.size();i++) {
			if (elements.get(i) instanceof ParagraphElement) {
				para++;
			}
			if(elements.get(i) instanceof ListElement) {
				list++;
			}
			if(elements.get(i) instanceof TableElement) {
				table++;
				TableElement e = (TableElement)(elements.get(i));//Creating an TableElement instance to retreive the method
			    tablecounters+=e.getTableUtilization();
			
			}
		}
		String str="";
		str+="List Count: "+list;
	    str+="\nParagraph Count: "+para;
		str+="\nTable Count: "+table;
		str+="\nTableElement Utilization: "+tablecounters/table;
		return str;
	}
	/**
	 * Compares the titles of the webpage
	 * @return true or false
	 */
	public int compareTo(WebPage webPage) {
		return this.getTitle().compareTo(webPage.getTitle());
	}
	
	public static void enableID(boolean choice) {
		TagElement.enableId(choice);
	}
	
	

}
