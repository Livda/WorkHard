/**
 *
 */
package controlers;

import java.util.ArrayList;
import java.util.List;

import api.Animal;
import api.AnimalList;
import api.AnimalShelter;
import api.DataBaseManager;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class LoadHandler implements EventHandler <ActionEvent> {

	public void handle(ActionEvent e) {
		DataBaseManager dbm = DataBaseManager.getDataBaseManager();
		List<Animal> animals = dbm.getAllAnimals();
		
		ArrayList<Animal> adoption = new ArrayList<Animal>();
		ArrayList<Animal> found = new ArrayList<Animal>();
		ArrayList<Animal> lost = new ArrayList<Animal>();
		
		for (Animal a : animals){
			char catLetter = a.getAnimalCategory().getCategoryLetter();
			switch(catLetter){
			case 'a':
				adoption.add(a);
				break;
			case 'f':
				found.add(a);
				break;
			case 'l':
				lost.add(a);
				break;
			}
		}
		AnimalList aAdoption = new AnimalList(adoption);
		AnimalList aFound = new AnimalList(found);
		AnimalList aLost = new AnimalList(lost);
		MainWindow.shelter = new AnimalShelter(aAdoption, aFound, aLost);
		MainWindow.table.printShelter();;
	}
}
