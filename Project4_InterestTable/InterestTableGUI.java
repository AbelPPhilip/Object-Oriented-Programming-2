/**
 * @author Abel P. & Evan K.
 * @author AbelP. & Evan K. 
 * Abel Philip, UID: 117868234
 * Evan Kushinoff, UID: 117886429
 * Date:2/28/2022
 * Class:CMSC132
 * Instructor: Professor Pedram Sadeghian
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.*;

import java.io.IOException;
import java.text.NumberFormat;

public class InterestTableGUI extends Application{
	//Declaration and Initialization of GUI elements
	Button b1,b2,b3,b4,b5,extraButton;
	HBox main = new HBox();
	HBox inputs = new HBox();
	HBox input1 = new HBox();
	HBox input2 = new HBox();
	HBox sliderBox = new HBox();
	HBox buttons = new HBox();
	VBox vbox = new VBox();
    
	Label numYears = new Label();
	TextField t1 = new TextField(); //input for Principal
	Label principal = new Label();
	TextField t2 = new TextField();//input for rate
	Label rate = new Label();
	Label out = new Label();//The console of output
	
	TextField textField = new TextField();
	Slider slider;
	int slideNum =0;
	//Main Method that launches
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage stage) throws IOException {
		//Initializing of simple Interest button
		b1= new Button("SimpleInterest");
		HBox.setMargin(b1,new Insets(10));
	   //Initializing of Compound Interest button 
		b2 = new Button("CompoundInterest");
		HBox.setMargin(b2,new Insets(10));
	    //Initializing of both interest button
		b3 = new Button("BothInterest");
		//NODE to store buttons
		HBox.setMargin(b3,new Insets(10));
		buttons.getChildren().addAll(b1,b2,b3);//Adding buttons
		buttons.setAlignment(Pos.BOTTOM_CENTER);
		//Labeling the labels 
		principal.setText("Principal: ");
		rate.setText("Rate(Percentage): ");
		numYears.setText("Number of Years: ");
		numYears.setAlignment(Pos.TOP_CENTER);
		//New Slider initializing and customization
		slider = new Slider(0,25,0);
		//slider.setPrefWidth(350);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(1.0);
		slider.setMinorTickCount(0);
		slider.setSnapToTicks(true);
		slider.setShowTickLabels(true);
		//slider.setMinorTickCount(25);
		//Node Arrangement
		input1.getChildren().addAll(principal,t1);
		input2.getChildren().addAll(rate,t2);
		inputs.getChildren().addAll(input1,input2);
		//sliderBox.getChildren().addAll(numYears,slider);
		inputs.setAlignment(Pos.BOTTOM_CENTER);
		//sliderBox.setAlignment(Pos.BOTTOM_CENTER);
	    //Adding all the elements to vbox
		vbox.getChildren().addAll(out,inputs,numYears,slider,buttons);
	    vbox.setAlignment(Pos.BOTTOM_CENTER);
	    Scene sc = new Scene(vbox);
	    
	    //The Button Handler is created
	    ButtonHandler ButtonListner = new ButtonHandler();
	    b1.setOnAction(ButtonListner);
	    b2.setOnAction(ButtonListner);
	    b3.setOnAction(ButtonListner);
	    b1.fire();//To activate 
	    //An Alternative to PrefWidth 
	    out.setText("\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n"+"\n");
		
		stage.setScene(sc);
		//set stage title
		
		//display the stage
		stage.show();

	}
	//Inner Class to handle the Buttons, implements the EventHandler Interface
	class ButtonHandler implements EventHandler<ActionEvent>
	{
		//Anonymous Class that handles each individual button 
		@Override
		public void handle(ActionEvent events) {
			b1.setOnAction((event) -> {    // lambda expression
				int num = (int)slider.getValue();
				InterestTable simpleTable = new InterestTable(  Double.parseDouble(t1.getText()),  Double.parseDouble(t2.getText()),num);
				out.setText("Principal, " + simpleTable.getPrincipal() + " Rate: " + simpleTable.getRate()
				+ "\n"+ "Year, Simple Interest Amount" +  "\n" + simpleTable.getString(1));
			});
			b2.setOnAction((event) -> {    // lambda expression
			{
				int num = (int)slider.getValue();
				InterestTable compTable = new InterestTable(  Double.parseDouble(t1.getText()),  Double.parseDouble(t2.getText()),num);
				out.setText("Principal, " + compTable.getPrincipal() + " Rate: " + compTable.getRate()
						+ "\n" + "Year, Compound Interest Amount" + "\n" + compTable.getString(2));
			}
			});
			  
			b3.setOnAction(new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent event){
					int num = (int)slider.getValue();
					InterestTable bothTable = new InterestTable(  Double.parseDouble(t1.getText()),  Double.parseDouble(t2.getText()),num);
					out.setText("Principal, " + bothTable.getPrincipal() + " Rate: " + bothTable.getRate() 
					+ "\n"+ "Year, Both Interest Amounts" + "\n" + bothTable.getString(3));
			    }
			});	  
		}
	}//end of inner class*/
	
	
	
}