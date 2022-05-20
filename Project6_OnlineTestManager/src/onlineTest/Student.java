/*
 * Name: Abel P. Philip
 * Class: Student Class
 * Functionality: The Student class is a complex class that creates an object of a student, his/her info
 * Exams they took and returns the report card, percentages and letter grades. 
 */
package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Student implements Comparable<Student>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Instance Variables 
	 */
	private String name;
	private double score;  
	private Map<Integer,ArrayList<Double>> examScores;//Map to store individual question scores for each exam. 
	private Map<Integer,Double> finalScores;//Map to store the final score of each exam 
	private Map<Integer, Double> examTotals;//Map to store the total score worth of exam. 

	/*
	 * Constructor
	 */
	public Student(String name) {
		this.name = name;
		score = 0;
		examScores = new LinkedHashMap<Integer,ArrayList<Double>>();
		finalScores = new LinkedHashMap<Integer,Double>();
		examTotals = new LinkedHashMap<Integer,Double>();	
	}
	
	/*
	 * GetMethod that returns the name of the student. 
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Get method that returns the score of particular exam. 
	 */
	public double getScore(int examId) {
		ArrayList <Double> s = examScores.get(examId);
		Double score=0.0;
		for (Double pt : s) {
			score += pt;
		}
		return score;
	}
	
	/*
	 * Method that allows student to answer a particular exam question. 
	 */
	public void answerTrueFalse(Exam exam, int QuestionNo, boolean answer) {
		int examId = exam.getExamId();
		double points = 0.0;
		TrueFalse question = (TrueFalse) exam.getQuestion(QuestionNo);
		//Checks if that exam exist
		if(!(examScores.containsKey(exam.getExamId()))) {
			examScores.put(examId, new ArrayList<Double>());
		}
		//The points are added if the answer is correct
	    points = question.isCorrect(answer);
	    
	    //Updates the ExamScores map for each question
	    ArrayList <Double> s = examScores.get(examId);
	    s.add(points);
	    examScores.put(examId,s);
	    
	    //Updates the FinalScores. 
	    if (finalScores.containsKey(examId)) {
			Double pts = finalScores.get(examId);
			pts+=points;
			finalScores.put(exam.getExamId(),pts);
		}else {
			finalScores.put(exam.getExamId(),points);
		}
	    
	    //Updates the examTotals map, to receive the total score that can be attained.
	    if (examTotals.containsKey(examId)) {
			examTotals.put(exam.getExamId(),exam.getTotalPoints());
		}else {
			examTotals.put(exam.getExamId(),exam.getTotalPoints());
		}
	      
	}
	
	public void answerMCQ(Exam exam, int questionNo, String[]choices) {
		int examId = exam.getExamId();
		double points = 0.0;
		MultipleChoice question = (MultipleChoice) exam.getQuestion(questionNo);
		
		//Updates the examScores Map for each question
		if(!(examScores.containsKey(examId))) {
			examScores.put(examId, new ArrayList<Double>());
		}
		//Adds points if the answer is correct
	    points = question.isCorrect(choices);
	    
		//Updates the examScores Map
		ArrayList<Double> s = examScores.get(examId);
		s.add(points);
		examScores.put(examId,s);
		
		//Updates the final Score Map. 
		if (finalScores.containsKey(examId)) {
			Double pts = finalScores.get(examId);
			pts+=points;
			finalScores.put(exam.getExamId(),pts);
		}else {
			finalScores.put(exam.getExamId(),points);
		}	
		
		//Updates the examTotal Map for total score that can be attained
		if (examTotals.containsKey(examId)) {
			examTotals.put(exam.getExamId(),exam.getTotalPoints());
		}else {
			examTotals.put(exam.getExamId(),exam.getTotalPoints());
		}
	}
	
	public void answerBlanks(Exam exam, int questionNo,String []answer) {
		int examId = exam.getExamId();
		double points = 0.0;
		FillInTheBlank question = (FillInTheBlank) exam.getQuestion(questionNo);
		
		//Checks if exam exists in map
		if(!(examScores.containsKey(examId))) {
			examScores.put(examId, new ArrayList<Double>());
		}
        //Adds points based on the answers. 
		points+= question.isCorrect(answer);
		
		//Updates the ExamScores for each question 
		ArrayList<Double> s = examScores.get(examId);
		s.add(points);
		examScores.put(examId,s);
		
		//Updates the FinalScores
		if (finalScores.containsKey(examId)) {
			Double pts = finalScores.get(examId);
			pts+=points;
			finalScores.put(exam.getExamId(),pts);
		}else {
			finalScores.put(exam.getExamId(),points);
		}	
		//Updates the examTotal Map for total score that can be attained
		if (examTotals.containsKey(examId)) {
			examTotals.put(exam.getExamId(),exam.getTotalPoints());
		}else {
			examTotals.put(exam.getExamId(),exam.getTotalPoints());
		}
	}
	
	/*
	 * Method that returns the string representation of the grading report
	 */
	public String gradingReport(Exam e) {
		String str="";
		ArrayList<Double> set = examScores.get(e.getExamId());
		int i = 1;
		for (Double s : set) {
			str+= ("Question #"+i+" ")+(s+ " points out of " + e.getQuestion(i).getPoints()+"\n");
			i++;
		}
		str+= "Final Score: " + getScore(e.getExamId()) + " out of " + e.getTotalPoints();
		return str;	
	}
	
	/*
	 * Method that calculates the letter grade for the student
	 */
	public String LetterGrade(String [] s, double [] cutOffs) {
		double percentage = getPercentage();//Receives percentage.
		for (int i =0; i<cutOffs.length;i++) {
			if (percentage >= cutOffs[i]) {
				return s[i];
			}
		}
		return null;
	}

	/*
	 * Method that calculates the numericGrade for the course 
	 */
	public double getCourseNumericGrade() {
		double total =0;
		for(Double score : finalScores.values()) {
			total+=score;
		}
		return total/(finalScores.keySet().size());
	}
	
	/*
	 * Method that returns the percentage received for the course. 
	 * 
	 * Note: This method can be implemented efficiently, but the goal was to use the formula provided
	 * in the project 6 design brief. 
	 */
	private double getPercentage() {
		//Creation of arraylist to convert the map values to arraylist. 
		ArrayList<Double> totals = new ArrayList<Double>();
		ArrayList<Double> ExamTotals = new ArrayList<Double>();
		for(Double score : finalScores.values()) {
			totals.add(score);
		}
	    for (Double score : examTotals.values()) {
	    	ExamTotals.add(score);
	    }
	    
	    //Creation of ArrayList to store percents for each exam. 
	    ArrayList<Double>percents = new ArrayList<Double>();
	    for (int i =0;i<totals.size();i++) {
	    	percents.add((totals.get(i)/ExamTotals.get(i)));//Formula Provided 
	    }
	    //Calculates total decimal percentage of each each. 
	    double percent =0.0; 
	    for (double s : percents) {
	    	percent+=s;
	    }
	    //Returns the final percentage. 
	    return (((percent)/percents.size())*100);	
	}
    
	/*
	 * Overriden method that compares student based on student numeric grade 
	 */
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return (int) ((int)o.getCourseNumericGrade()-this.getCourseNumericGrade());
	}
	
	/*
	 * Returns the exam grade for a particular exam. 
	 */
	public double getExamGrade(int examId) {
		Double score = finalScores.get(examId);
		return score;
	}
	
	
	
	
	
	
	
   
	
}

