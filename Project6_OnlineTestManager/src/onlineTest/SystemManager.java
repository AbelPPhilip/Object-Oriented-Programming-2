/*
 * Name: Abel P. Philip 
 * CLass: SystemManager
 * Functionality: The Driver class that allows the user to add exams, add students, sort students, exams and received a comprehensive report 
 */
package onlineTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SystemManager implements Manager, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Instance variables 
	 */
	ArrayList<Exam> exams;
	ArrayList<Student> students;
	private String [] letters;
	private double [] cutOffs;
	
	/*
	 * Constructor
	 */
	public SystemManager() {
		// TODO Auto-generated constructor stub
		exams = new ArrayList<Exam>();
		students = new ArrayList<Student>();
		letters = null;
		cutOffs = null;
	}
	
    /*
     * Method that creates a Exam object and adds exam list
     */
	@Override
	public boolean addExam(int examId, String title) {
		Exam exam = new Exam(examId, title);
		exams.add(exam);
		Collections.sort(exams);//Sorting exams to make sure it is ordered based on id
		return true; 
	}
    /*
     * Method to add questions to a exam 
     */
	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		TrueFalse trueFalse = new TrueFalse(questionNumber, text, points, answer);
		if (getExam(examId)!=null) {
			getExam(examId).add(trueFalse);
		}
	}
    /*
     * Method to add multipleChoice to a exam
     */
	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {

		MultipleChoice mcq = new MultipleChoice(questionNumber, text, points, answer);
		if (getExam(examId)!=null) {
			getExam(examId).add(mcq);
		}
		
	}
    /*
     * Method to add Fill in the blank questions to a particular exam.
     */
	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
		String[] answer) {
		FillInTheBlank ftb = new FillInTheBlank(questionNumber, text, points, answer);
		if (getExam(examId) != null) {
			getExam(examId).add(ftb);
		}
	}
    /*
     * Method to get exam questions of a exam. 
     */
	@Override
	public String getKey(int examId) {
		Exam e = getExam(examId);
		String str = e.toString();
		return str;
	}
    
	/*
	 * Method to add student to the list by creating Student object
	 */
	@Override
	public boolean addStudent(String name) {
		Student std = new Student(name);
		students.add(std);
		return false;
	}
    /*
     * Method that allows a student in the list to answer true or false question that is found in a exam. 
     */
	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		Exam e = getExam(examId);
		if (getStudent(studentName) != null && e != null) {
			for (Student student : students) {
				if (student.getName().equals(studentName)) {
					e.addStudents(getStudent(studentName));
					student.answerTrueFalse(e, questionNumber, answer);
				}
			}
		}
		
	}
    /*
     * Method that allows a student in the list to answer multiple choice question that is found in a exam. 
     */
	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		
		Exam e = getExam(examId);
		if (getStudent(studentName) != null && e!=null) {
			for (Student student : students) {
				if (student.getName().equals(studentName)) {
					e.addStudents(getStudent(studentName));
					student.answerMCQ(e, questionNumber, answer);
				}
			}
		}
	}
    /*
     * Method that allows a student in the list to answer fill in the blank question that is found in a exam. 
     */
	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		Exam e = getExam(examId);
		if (getStudent(studentName) != null && e!=null) {
			for (Student student : students) {
				if (student.getName().equals(studentName)) {
					e.addStudents(getStudent(studentName));
					student.answerBlanks(e, questionNumber, answer);
				}
			}
		}
	}
    
	/*
	 * Methods that returns the exam score of a student for a particular exam. 
	 */
	@Override
	public double getExamScore(String studentName, int examId) {
		// TODO Auto-generated method stub
		if (getStudent(studentName) != null && getExam(examId)!=null) {
			for (Student student : students) {
				if (student.getName().equals(studentName)) {
					return student.getScore(examId);
				}
			}
		}
		return 0;
	}
    /*
     * Method that returns the grad report of a student for an exam. 
     */
	@Override
	public String getGradingReport(String studentName, int examId) {
		String str = "";
		Exam e = getExam(examId);
		if (e!=null && getStudent(studentName) != null) {
			for (Student s : e.getStudents()) {
				if (s.getName().equals(studentName)) {
					str+=s.gradingReport(e);
	                return str;
				}
			}
		}
		return str;	
	}
    /*
     * Methods sets the letter grading criteria 
     */
	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		// TODO Auto-generated method stub
		letters = letterGrades;
		cutOffs =  cutoffs;
	}
    
	/*
	 * Method that returns the course numeric grade of a student 
	 */
	@Override
	public double getCourseNumericGrade(String studentName) {
		// TODO Auto-generated method stub
		Student s  = getStudent(studentName);
		if (s != null) {
			return s.getCourseNumericGrade();
		}
		return 0.0;
	}
	
	
    /*
     * Method that returns the course letter grade of a student
     */
	@Override
	public String getCourseLetterGrade(String studentName) {
		// TODO Auto-generated method stub
		Student s  = getStudent(studentName);
		if (s != null) {
			return s.LetterGrade(letters,cutOffs);
		}
		return null;
	}

	/*
	 * Method that returns the whole course grade report for all the students. 
	 */
	@Override
	public String getCourseGrades() {
		// TODO Auto-generated method stub
		String str = "";
		ArrayList<Student> copy  = new ArrayList<Student>();
		for (Student s : students) {
			copy.add(s);
		}
        Collections.sort(copy);
		for (Student s : copy) {
			str+= (s.getName()+" "+s.getCourseNumericGrade()+" "+s.LetterGrade(letters, cutOffs)+"\n");
		}
		return str;
	}
    
	/*
	 * Method that returns the highest score for an exam. 
	 */
	@Override
	public double getMaxScore(int examId) {
		// TODO Auto-generated method stub
		if (getExam(examId) != null) {
			return getExam(examId).maxScore();
		}
		return 0.0;
	}
	
	/*
	 * Method that returns the lower score for an exam. 
	 */
	@Override
	public double getMinScore(int examId) {
		// TODO Auto-generated method stub
		if (getExam(examId) != null) {
			return getExam(examId).minScore();
		}
		return 0.0;
	}
    
	/*
	 * Method that returns the average score received for an exam. 
	 */
	@Override
	public double getAverageScore(int examId) {
		// TODO Auto-generated method stub
		if (getExam(examId) != null) {
			return getExam(examId).average();
		}
		return 0.0;
	}

	/*
	 * Serializing method that saves a Manager Object (SystemManager) to a file
	 */
	@Override
	public void saveManager(Manager manager, String fileName) {
		// TODO Auto-generated method stub
		try {
			File file = new File(fileName);
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
			output.writeObject(manager);
			output.close();
		} catch (IOException e) {//IO Eception handled.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/*
	 * De-serializing method that creates a manager object based on information given in the file. 
	 */
	@Override
	public Manager restoreManager(String fileName) {
		// TODO Auto-generated method stub
		SystemManager manager = null;
	     try {
	         FileInputStream fileIn = new FileInputStream(fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         manager = (SystemManager) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {//IO exception handled
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {//Class not found exception handles
	         c.printStackTrace();
	      }
	      return manager;	
	}
	
	/*
	 * Method that retrieves the exam from the list of exams.
	 */
	private Exam getExam(int examId) {
		for(Exam e : exams) {
			if(e.getExamId()==examId) {
				return e;
			}
		}
		return null;
	}
	
	/*
	 * Method that retrieves the student from the list of students. 
	 */
	private Student getStudent(String name) {
		for(Student s : students) {
			if(s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}

}
