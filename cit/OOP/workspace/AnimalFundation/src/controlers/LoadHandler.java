/**
 * 
 */
package controlers;

import java.util.ArrayList;

import api.Animal;
import api.AnimalShelter;
import api.Saver;
import gui.MainWindow;
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
	private TableView<Animal> table;
	
	public LoadHandler(TableView<Animal> table) {
		this.table = table;
	}
	
	public void handle(ActionEvent e) {
		Saver saver = new Saver();
		MainWindow.shelter = saver.load();
		ArrayList<Animal> list = MainWindow.shelter.getAllAnimals();
		ObservableList<Animal> allData = table.getItems();
		allData.clear();
		allData.addAll(list);
		table.setItems(allData);
	}
}
