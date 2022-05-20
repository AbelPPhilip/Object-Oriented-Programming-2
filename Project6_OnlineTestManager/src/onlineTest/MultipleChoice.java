/*
 * Name: Abel P. Philip
 * Class: MultipleChoice
 * Functionality: The multiple choice extends the ExamQ abstract class and provides the format of a multiple choice question
 */
package onlineTest;
import java.io.Serializable;
import java.util.*;


public class MultipleChoice extends ExamQ implements Serializable{
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
	public MultipleChoice(int questionNo, String text, double points, String[]answer) {
		super(questionNo, text, points);//Calls the constructor of super class
		answers =Arrays.asList(answer);
		Collections.sort(answers);
		this.answer = answers.toString();
	}

	/*
	 * Method that returns points received based on student's answer
	 */
	public double isCorrect(String [] choices) {
		double point=0.0;
		List<String> mcqchoices = Arrays.asList(choices);
		if (answers.equals(mcqchoices)) {//if the answer provided matches with the question answer
			point+=getPoints();
		}
		return point;
	}
	
	/*
	 * Overridden method that returns the answer of this question. 
	 */
    @Override
	public String getAnswer() {
		return answer;
	}
    
    /*
     * Overriden method that compares the exam questions based on the question Number. 
     */
    @Override
	public int compareTo(ExamQ o) {
		return (int) this.getQuestionNo()-o.getQuestionNo();
	}
}
