/**
 * 
 */
package controlers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import api.Adoption;
import api.Animal;
import api.AnimalList;
import api.AnimalShelter;
import api.Category;
import api.Found;
import api.Lost;
import api.Person;
import gui.AnimalBox;
import gui.MainWindow;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class CreateAnimalHandler implements EventHandler<ActionEvent>{
	private AnimalBox animalBox;
	private GridPane person;
	
	public CreateAnimalHandler(AnimalBox animalBox, GridPane person){
		this.animalBox = animalBox;
		this.person = person;
	}
	
	public void handle(ActionEvent event){
		Node node;
		AnimalShelter shelter = MainWindow.shelter;
		
		//We take what is in the person Grid
//		node = getNodeByRowColumnIndex(0, 1, person);
//		String pName = ((TextField) node).getText();
//		node = getNodeByRowColumnIndex(1, 1, person);
//		String adress = ((TextArea) node).getText();
//		node = getNodeByRowColumnIndex(2, 1, person);
//		String phone = ((TextField) node).getText();
//		node = getNodeByRowColumnIndex(3, 1, person);
//		String email = ((TextField) node).getText();
//		Person p = new Person(pName, adress, phone, email);
		Person p = new Person();
		
		//We create a Category
		Category newCategory = null;
		GregorianCalendar date = (GregorianCalendar) animalBox.getDate();
		String category = animalBox.getCategory();
		switch (category) {
		case "Adoption" :
			boolean neutered = animalBox.getNeutered();
			boolean chipped = animalBox.getChipped();
			boolean vaccinated = animalBox.getNeutered();
			newCategory = new Adoption(date, p, neutered, chipped, vaccinated);
			break;
		case "Found" :
			String fLocal = animalBox.getLocalisation();
			newCategory = new Found(date, p, fLocal);
			break;
		case "Lost" :
			String lLocal = animalBox.getLocalisation();
			newCategory = new Lost(date, p, lLocal);
			break;
		}
		
		//We create an Animal
		String aName = animalBox.getName();
		int age = animalBox.getAge();
		String color = animalBox.getColour();
		String description = animalBox.getDescrition();
		String breed = animalBox.getBreed();
		String type = animalBox.getType();
		boolean gender = animalBox.getGender();
		
		Animal a = new Animal(age, color, gender, description, aName, null, 
				breed, newCategory, type);
		
		//Add the animal to the shelter
		shelter.add(a);
		
		//we add the new animal to the TableView
		MainWindow.table.printShelter();
	}
}
