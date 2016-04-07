/**
 *
 */
package reportControlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.Animal;
import api.AnimalShelter;
import api.Lost;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class LostHandler implements EventHandler<ActionEvent> {
	private Stage stage;
	private TextField locationToFind;
	private boolean printCat;

	public LostHandler(Stage stage, TextField location, boolean cat){
		this.stage = stage;
		this.locationToFind = location;
		this.printCat = cat;
	}

	public void handle(ActionEvent event){
		AnimalShelter shelter = MainWindow.shelter;

		String locationFind = locationToFind.getText();

		List<Animal> list = shelter.getLost().getList();
		List<Animal> res = new ArrayList<Animal>();
		for (Animal a : list){
			String location = ((Lost) a.getAnimalCategory()).getLocation();
			if (locationFind.equals(location)){
				if (printCat){
					if (a.getBreed().equals("Cat")) {res.add(a);}
				} else {
					res.add(a);
				}
			}
		}
		Collections.sort(res);
		new ReportHandler(stage).fillAnimalByList(res);
	}

}
