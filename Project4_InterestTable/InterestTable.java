/**
 * 
 * @author AbelP. & Evan K. 
 * Abel Philip, UID: 117868234
 * Evan Kushinoff, UID: 117886429
 * Date:2/28/2022
 * Class:CMSC132
 * Instructor: Professor Pedram Sadeghian
 * 
 * Description: 
 *
 */
import java.text.*;
public class InterestTable {
    //Declaring private variables 
	private double principal; 
	private double rate;
	private int years;
	String table;
	//Constructor for Interest Table
	public InterestTable(double principal, double rate, int years) {
		this.principal = principal;
		this.rate = rate;
		this.years = years; 
		table = "";
		
			// TODO Auto-generated constructor stub
	}
	//Simple Interest Method 
	public double simpleInterest(int years) {
		return principal + (principal * (rate/100) * years);
		 
	}
	//Compound Interest 	
	public double compoundInterest(int years) {
		return principal * Math.pow((1+rate/100),years);
	}
	
	
	//getString Method that releases the string representation
	public String getString(int x)
	{
		String s = "";
		for (int i = 0; i <= years; i ++) {
			if (x == 1) {//If only simple interest button is clicked
				s += i + "-->" + NumberFormat.getCurrencyInstance().format(simpleInterest(i));
			}
			else if (x == 2) {//if only compound interest button is clicked 
				s += i + "-->" + NumberFormat.getCurrencyInstance().format(compoundInterest(i));
			}
			else if(x == 3) {//if only both interest button is clicked. 
				s += i + "-->" + NumberFormat.getCurrencyInstance().format(simpleInterest(i)) + "-->" + NumberFormat.getCurrencyInstance().format(compoundInterest(i));
			}
			s += "\n";//New line
		}
		return s;
		
	}
    //Solo method that returns the principal
	public double getPrincipal() {
		// TODO Auto-generated method stub
		return principal;
	}
    //Solo method that returns the Rate. 
	public double getRate() {
		// TODO Auto-generated method stub
		return rate;
	}

}


