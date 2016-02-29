/**
 * 
 */
package controlers;

import java.util.ArrayList;

import api.Animal;
import api.AnimalShelter;
import api.Saver;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class LoadHandler implements EventHandler <ActionEvent> {
	TableView<Animal> table;
	AnimalShelter shelter;
	
	public LoadHandler(TableView<Animal> table, AnimalShelter shelter) {
		this.table = table;
		this.shelter = shelter;
	}
	
	public void handle(ActionEvent e) {
		Saver saver = new Saver();
		shelter = saver.load();
		ArrayList<Animal> list = shelter.getAllAnimals();
		ObservableList<Animal> allData = table.getItems();
		allData.clear();
		allData.addAll(list);
		table.setItems(allData);
	}
}
