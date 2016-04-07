/**
 * 
 */
package reportControlers;

import java.util.Collections;
import java.util.List;

import api.AgeComparator;
import api.Animal;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class GeneralHandler implements EventHandler<ActionEvent> {
	
	private RadioButton animals;
	private RadioButton persons;
	private Stage stage;

	public GeneralHandler(RadioButton animals, RadioButton persons, Stage stage){
		this.animals = animals;
		this.persons = persons;
		this.stage = stage;
	}
	
	public void handle(ActionEvent event){
		if (animals.isSelected()){
			List<Animal> list = MainWindow.shelter.getAllAnimals();
			Collections.sort(list, new AgeComparator());
			new ReportHandler(stage).fillAnimalByList(list);
		}
		if (persons.isSelected()){
			new ReportHandler(stage).fillAllPerson();
		}
	}
}
