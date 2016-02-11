package gui;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PersonBox {
	private final double vSpacing = 10;
	private final double hSpacing = 10;
	private VBox box;
	
	public PersonBox(){
		GridPane personGrid = new GridPane();
        personGrid.setVgap(vSpacing);
        personGrid.setHgap(hSpacing);
        
        Text personName = new Text("Name :");
        personGrid.add(personName, 0, 0);

        TextField setPersonName = new TextField();
        personGrid.add(setPersonName, 1, 0);
        
        Text personAdress = new Text("Adress :");
        personGrid.add(personAdress, 0, 1);
        
        TextArea setPersonAdress = new TextArea();
        setPersonAdress.setMaxSize(300, 60);
        personGrid.add(setPersonAdress, 1, 1);
        
        Text personPhone = new Text("Phone :");
        personGrid.add(personPhone, 0, 2);

        TextField setPersonPhone = new TextField();
        personGrid.add(setPersonPhone, 1, 2);
        
        Text personEmail = new Text("Email :");
        personGrid.add(personEmail, 0, 3);
        
        TextField setPersonEmail = new TextField();
        personGrid.add(setPersonEmail, 1, 3);
                
        Text personTitle = new Text("Person");
        personTitle.setFont(Font.font(null, FontWeight.BOLD, 25));
        
        box = new VBox(20);
        box.getChildren().addAll(personTitle, personGrid);
        box.setAlignment(Pos.TOP_CENTER);
	}
	
	public VBox getBox(){
		return box;
	}
}
