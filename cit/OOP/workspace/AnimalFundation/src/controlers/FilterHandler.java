/**
 * 
 */
package controlers;

import java.util.ArrayList;

import api.Animal;
import api.AnimalShelter;
import gui.MainWindow;
import gui.TableViewBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class FilterHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent event){
		TableViewBox tableBox = MainWindow.table;
		TableView<Animal> table = tableBox.getTable();
		VBox vBox = tableBox.getBox();
		HBox filterBox = (HBox) vBox.getChildren().get(0);
		CheckBox adoption = (CheckBox) filterBox.getChildren().get(0);
		CheckBox found = (CheckBox) filterBox.getChildren().get(1);
		CheckBox lost = (CheckBox) filterBox.getChildren().get(2);
		
		ArrayList<Animal> list = new ArrayList<Animal>();
		AnimalShelter shelter = MainWindow.shelter;
		if (adoption.isSelected()) {
			list.addAll(shelter.getAdoption().getList());
		}
		if (found.isSelected()) {
			list.addAll(shelter.getFound().getList());
		}
		if (lost.isSelected()) {
			list.addAll(shelter.getLost().getList());
		}
		table.getItems().clear();
		table.getItems().addAll(list);
	}
}

