/*
 * Name: Abel P. Philip
 * Class: FillInTheBlank Class
 * Functionality: Class that provides the format of the fill in the blank questions. 
 */
package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FillInTheBlank extends ExamQ implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Instance variables
	 */
	private List<String> answers;
	private String answer;
	
	/*
	 * Constructor
	 */
	public FillInTheBlank(int questionNo, String text, double points, String [] answer) {
		super(questionNo, text, points);
		answers = Arrays.asList(answer);
		Collections.sort(answers);
		this.answer = answers.toString();
		// TODO Auto-generated constructor stub
	}
	
    /*
     * Overriden method that compares the exam questions based on question number
     */
	@Override
	public int compareTo(ExamQ o) {
		return (int) this.getQuestionNo()-o.getQuestionNo();
	}
	
	
	/*
	 * Method that returns the points received if student's answer was contained in the question answer 
	 */
	public double isCorrect(String [] choices) {
		// TODO Auto-generated method stub
		double point=0.0;
		double pointPerIncrease = (this.getPoints()/answers.size());//The point received for every correct question 
		for(String s : choices) {
			if (answers.contains(s)) {
				point+=pointPerIncrease;
			}
		}
		return point;
	}
	//Overridden method that returns the answer of the question. 
    @Override
	public String getAnswer() {
		return answer;
	}

	
	
	
	

}
