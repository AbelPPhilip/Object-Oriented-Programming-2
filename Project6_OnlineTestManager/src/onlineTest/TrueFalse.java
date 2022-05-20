package onlineTest;
import java.io.Serializable;
import java.util.ArrayList;

public class TrueFalse extends ExamQ implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String answer;

	public TrueFalse(int questionNo, String text, double points, boolean answer) {
		super(questionNo, text, points);
		this.answer = (answer? "True": "False");
		// TODO Auto-generated constructor stub
	}
    
	public double isCorrect(boolean answer) {
		if(this.answer.equals(answer ? "True" : "False")) {
			return this.getPoints();
		}
		return 0.0;
	}
	
	@Override
	public String getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}
	
	@Override
	public int compareTo(ExamQ o) {
		return (int) this.getQuestionNo()-o.getQuestionNo();
	}
	

	
	
	
	
	
	

}
