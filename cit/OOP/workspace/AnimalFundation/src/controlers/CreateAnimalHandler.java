/**
 * 
 */
package controlers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import api.Adoption;
import api.Animal;
import api.AnimalList;
import api.AnimalShelter;
import api.Category;
import api.Found;
import api.Lost;
import api.Person;
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
	private GridPane animal;
	private GridPane person;
	
	public CreateAnimalHandler(GridPane animal, GridPane person){
		this.animal = animal;
		this.person = person;
	}
	
	public void handle(ActionEvent event){
		Node node;
		AnimalShelter shelter = MainWindow.shelter;
		
		//We take what is in the person Grid
		node = getNodeByRowColumnIndex(0, 1, person);
		String pName = ((TextField) node).getText();
		node = getNodeByRowColumnIndex(1, 1, person);
		String adress = ((TextArea) node).getText();
		node = getNodeByRowColumnIndex(2, 1, person);
		String phone = ((TextField) node).getText();
		node = getNodeByRowColumnIndex(3, 1, person);
		String email = ((TextField) node).getText();
		Person p = new Person(pName, adress, phone, email);
		
		//We take the category fields
		node = getNodeByRowColumnIndex(7, 1, animal);
		LocalDate date = ((DatePicker)node).getValue();
		GregorianCalendar calendar = 
				GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
		node = getNodeByRowColumnIndex(8, 1, animal);
		HBox categoryBox = ((HBox)node);
		ToggleGroup categoryGroup = 
				((RadioButton)categoryBox.getChildren().get(0)).getToggleGroup();
		String categoryName = ((RadioButton)categoryGroup.getSelectedToggle()).getText();
		Category category = null;
		AnimalList aList = null;
		switch (categoryName) {
		case "Adoption" :
			node = getNodeByRowColumnIndex(9, 1, animal);
			HBox neuteredBox = ((HBox)node);
			boolean neutred = ((RadioButton)neuteredBox.getChildren().get(0)).isSelected();
			node = getNodeByRowColumnIndex(10, 1, animal);
			HBox chippedBox = ((HBox)node);
			boolean chipped = ((RadioButton)chippedBox.getChildren().get(0)).isSelected();
			node = getNodeByRowColumnIndex(11, 1, animal);
			HBox vaccinatedBox = ((HBox)node);
			boolean vaccinated = 
					((RadioButton)vaccinatedBox.getChildren().get(0)).isSelected();
			category = new Adoption(calendar, p, neutred, chipped, vaccinated);
			aList = shelter.getAdoption();
			break;
		case "Found" :
			node = getNodeByRowColumnIndex(9, 1, animal);
			String fLocation = ((TextField)node).getText();
			category = new Found(calendar, p, fLocation);
			aList = shelter.getFound();
			break;
		case "Lost" :
			node = getNodeByRowColumnIndex(9, 1, animal);
			String lLocation = ((TextField)node).getText();
			category = new Lost(calendar, p, lLocation);
			aList = shelter.getLost();
			break;
		}

		//We take what is in the Animal Grid
		node = getNodeByRowColumnIndex(0, 1, animal);
		String aName = ((TextField) node).getText();
		node = getNodeByRowColumnIndex(1, 1, animal);
		int age = Integer.parseInt(((TextField) node).getText());
		node = getNodeByRowColumnIndex(2, 1, animal);
		String colour = ((TextField) node).getText();
		node = getNodeByRowColumnIndex(3, 1, animal);
		String description = ((TextArea) node).getText();
		node = getNodeByRowColumnIndex(4, 1, animal);
		@SuppressWarnings("rawtypes")
		String breed = (String)((ChoiceBox)node).getValue();
		node = getNodeByRowColumnIndex(5, 1, animal);
		String type = ((TextField) node).getText();
		node = getNodeByRowColumnIndex(6, 1, animal);
		HBox buttonBox = ((HBox)node);
		boolean gender  = ((RadioButton)buttonBox.getChildren().get(0)).isSelected();
		Animal a = new Animal(age, colour, gender, description, aName, null, breed, 
				category, type);
		//add the animal to the right list
		aList.add(a);
		
		//we add the new animal to the TableView
		node = animal.getParent().getParent().getParent().getParent().getParent();
		BorderPane pane = (BorderPane) node;
		VBox vbox = (VBox) pane.getLeft();
		@SuppressWarnings("unchecked")
		TableView<Animal> table = (TableView<Animal>) vbox.getChildren().get(1);
		ArrayList<Animal> list = shelter.getAllAnimals();
		ObservableList<Animal> allData = table.getItems();
		allData.clear();
		allData.addAll(list);
		table.setItems(allData);
	}
	
	@SuppressWarnings("static-access")
	private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane){
		Node result = null;
		ObservableList<Node> children = gridPane.getChildren();
		for (Node node : children){
			if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}
		return result;
	}
}
