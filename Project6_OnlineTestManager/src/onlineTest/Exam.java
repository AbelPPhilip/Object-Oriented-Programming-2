/*
 * Name: Abel P. Philip
 * Class: Exam
 * Functionality: The Exam class keeps is whole exam class where it stores ExamQ objects (Exam Questions)
 * and Student Objects (Students). Exam Class returns 
 */
package onlineTest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
public class Exam implements Comparable<Exam>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Instance Variables
	 */
	private int examId;//The unique exam id
	private String name;//Name of the Exam
	private ArrayList<ExamQ>examQuestions; //List of examQuestions in the exam
	private double totalPoints; //Total points of this exam
	private ArrayList <Student>students;//List of students who took this exam
	
	/*
	 * Exam class constructor
	 */
	public Exam(int examId, String name) {
		//Initializes the instance variables
		this.examId = examId; 
		this.name = name;
		examQuestions = new ArrayList<ExamQ>();
		students = new ArrayList<Student>();
	}
     
    /*
     * Returns unique exam id
     */
	public int getExamId() {
		return examId;
	}
	
	/*
	 * adds exam questions to the list
	 */
	public void add(ExamQ e) {
		for (int i = 0; i<examQuestions.size();i++) {//Checks if exam question exists, if yes it overwrites
			if (examQuestions.get(i).getText().equals(e.getText())){
				examQuestions.set(i, e);
				Collections.sort(examQuestions);
				totalPoints = getTotalPoints() + e.getPoints();
				return;
			}
		}//If exam question is not found, the examQ is added to the list
		examQuestions.add(e);
		Collections.sort(examQuestions);
		totalPoints = getTotalPoints() + e.getPoints();
	}
	
	/*
	 * Getter method that returns the question found in the ExamQuestions list. 
	 */
	public ExamQ getQuestion(int questionNo) {
		for(int i = 0; i < examQuestions.size(); i++ ) {
			if (examQuestions.get(i).getQuestionNo() == questionNo) {
				return examQuestions.get(i);
			}
		}
		return null;
	}
    /*
     * Getter method that returns the name. 
     */
	public String getName() {
		return name;
	}
	
	/*
	 * To String method that prints out the exam questions. 
	 */
	public String toString() {
		String str = "";
		for (int i = 0; i<examQuestions.size();i++) {
			str+= ("Question Text: "+ examQuestions.get(i).getText() +"\r\n"
					+ "Points: "+examQuestions.get(i).getPoints()+"\r\n"
					+ "Correct Answer: "+examQuestions.get(i).getAnswer()+"\r\n");
		}
		return str;
		
	}
    
	/*
	 * Getter method that returns the total points worth of the exam. 
	 */
	public double getTotalPoints() {
		return totalPoints;
	}
	
	/*
	 * Data method that returns the highest score received on this exam. 
	 */
	public double maxScore() {
		double max = 0.0;
		for (Student s : students) {//Regular find max for loop
			if(s.getExamGrade(examId)>max) {
				max = s.getExamGrade(examId);
			}
		}
		return max;
	}
	
	/*
	 * Data method that returns the lowest score received on this exam
	 */
	public double minScore() {
        //Regular find min for loop
		double min = 10000;
		for (Student s : students) {
			if(s.getExamGrade(examId)<min) {
				min = s.getExamGrade(examId);
			}
		}
		return min;
	}
	
	/*
	 * Data method that returns the average score received on this exam. 
	 */
	public double average() {
		double total=0; //Takes the total of exam scores and divides by number of students
		for (Student s : students) {
			total+=s.getExamGrade(examId);
		}
		return total/students.size();
	}
	
	/*
	 * Getter method that returns the arraylist of students
	 */
	public ArrayList<Student> getStudents(){
		return students;
	}
	
	/*
	 * Void method that adds students to the list of students that attempted this exam. 
	 */
	public void addStudents(Student student) {
		if (!(students.contains(student))) {
			students.add(student);
		}
	}
	
	/*
	 * Overridden method that compares exams based on exam id. 
	 */
	@Override
	public int compareTo(Exam o) {
		return (int)this.getExamId()-o.getExamId();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	

}
