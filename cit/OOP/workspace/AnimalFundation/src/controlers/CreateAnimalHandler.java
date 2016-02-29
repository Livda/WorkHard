/**
 * 
 */
package controlers;

import java.util.Calendar;

import api.Animal;
import api.AnimalShelter;
import gui.MainWindow;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

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
		Node node = getNodeByRowColumnIndex(0, 1, animal);
		String name = ((TextField) node).getText();
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
		//Toggle button = ((Toggle)node).getSelectedToggle();
		boolean gender = true;
		node = getNodeByRowColumnIndex(7, 1, animal);
		Calendar date = null;
		node = getNodeByRowColumnIndex(8, 1, animal);
		int category = 0;
		
		System.out.println(name + " " + age +" " + colour + " " + description + " " + breed +
				" " + type + " " + gender + " " + date + " " + category);
		
		AnimalShelter shelter = MainWindow.shelter;
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
