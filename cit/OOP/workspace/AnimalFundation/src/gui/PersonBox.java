package gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PersonBox extends Box {
	private GridPane personGrid;
	private final double vSpacing = 10;
	private final double hSpacing = 10;
	private VBox box;
	
	public PersonBox(){
		super("Person");
		personGrid = new GridPane();
        personGrid.setVgap(vSpacing);
        personGrid.setHgap(hSpacing);
        
        Text personName = new Text("Name :");
        personGrid.add(personName, 0, 0);

        TextField setPersonName = new TextField();
        setPersonName.setPromptText("Ex : John Smith");
        personGrid.add(setPersonName, 1, 0);
        
        Text personAdress = new Text("Adress :");
        personGrid.add(personAdress, 0, 1);
        
        TextArea setPersonAdress = new TextArea();
        setPersonAdress.setMaxSize(300, 60);
        setPersonAdress.setPromptText("Ex : 1, Road of Ireland");
        personGrid.add(setPersonAdress, 1, 1);
        
        Text personPhone = new Text("Phone :");
        personGrid.add(personPhone, 0, 2);

        TextField setPersonPhone = new TextField();
        setPersonPhone.setPromptText("Ex : (021) 000 0000 ");
        personGrid.add(setPersonPhone, 1, 2);
        
        Text personEmail = new Text("Email :");
        personGrid.add(personEmail, 0, 3);
        
        TextField setPersonEmail = new TextField();
        setPersonEmail.setPromptText("Ex : john.smith@mail.com");
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
	
	public GridPane getPersonGrid(){
		return personGrid;
	}
	
	public String getName(){
		Node node = getNodeByRowColumnIndex(0, 1, personGrid);
		TextField nameField = (TextField) node;
		nameField.getStyleClass().remove("error");
		String name = nameField.getText();
		if (name.equals("")) {
			errorInTheFields = true;
			nameField.getStyleClass().add("error");
		}
		return name;
	}
	
	public String getAdress(){
		Node node = getNodeByRowColumnIndex(1, 1, personGrid);
		TextArea adressField = (TextArea) node;
		adressField.getStyleClass().remove("error");
		String adress = adressField.getText();
		if (adress.equals("")) {
			errorInTheFields = true;
			adressField.getStyleClass().add("error");
		}
		return adress;
	}
	
	public String getTelephone(){
		Node node = getNodeByRowColumnIndex(2, 1, personGrid);
		TextField phoneField = (TextField) node;
		phoneField.getStyleClass().remove("error");
		String phone = phoneField.getText();
		if (phone.equals("")) {
			errorInTheFields = true;
			phoneField.getStyleClass().add("error");
		}
		return phone;
	}
	
	public String getEmail(){
		Node node = getNodeByRowColumnIndex(3, 1, personGrid);
		TextField emailField = (TextField) node;
		emailField.getStyleClass().remove("error");
		String email = emailField.getText();
		if (email.equals("")) {
			errorInTheFields = true;
			emailField.getStyleClass().add("error");
		}
		return email;
	}
}
