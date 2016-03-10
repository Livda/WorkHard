package gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class PersonBox extends Box {
	private GridPane personGrid;
	
	public PersonBox(){
		super("Person");
		personGrid = this.getGrid();
        personGrid.setVgap(10);
        personGrid.setHgap(10);
        
        Text personName = new Text("Name :");
        personGrid.add(personName, 0, 0);
        
        Text personAdress = new Text("Adress :");
        personGrid.add(personAdress, 0, 1);
        
        Text personPhone = new Text("Phone :");
        personGrid.add(personPhone, 0, 2);
        
        Text personEmail = new Text("Email :");
        personGrid.add(personEmail, 0, 3);
	}
	
	public VBox getBox(){
		return this.getvBox();
	}
	
	public GridPane getPersonGrid(){
		return personGrid;
	}
	
	public abstract void setName(String name);
	public abstract void setAdress(String adress);
	public abstract void setTelephone(String phone);
	public abstract void setEmail(String email);
}
