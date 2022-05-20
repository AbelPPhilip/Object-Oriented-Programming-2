/*
 * Name: Abel P. Philip
 * Class: Abstract Class ExamQ 
 * Functionality: provides a layout of a typical exam question. 
 */
package onlineTest;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ExamQ implements Comparable<ExamQ>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Instance variables
	 */
	private int questionNo;
	private String text; 
	private double points;
	
	/*
	 * Constructor that have to be referred by super keyword in subclass constructors.
	 */
	public ExamQ(int questionNo, String text, double points) {
		this.questionNo = questionNo;
		this.text = text;
		this.points = points; 
	}
	
	/*
	 * Getter method that returns the question number
	 */
	public int getQuestionNo() {
		return questionNo;
	}

	/*
	 * Getter method that returns the text of the question
	 */
	public String getText() {
		return text;
	}
	
	/*
	 * Getter method that returns the points worth of the exam question
	 */
	public double getPoints() {
		return points;
	}
	/*
	 * Abstract methods that has to be implemented by the various exam questions
	 */
	public abstract String getAnswer();
	
	/**
	 * CompareTo method to compare exam based on the question number. 
	 */
	public int compareTo(ExamQ o) {
		// TODO Auto-generated method stub
		return (int) (this.getQuestionNo()-o.getQuestionNo());
	}
	
	

	
}
