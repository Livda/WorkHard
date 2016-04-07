/**
 * 
 */
package reportControlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.Adoption;
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
public class AdoptionHandler implements EventHandler<ActionEvent> {
	private RadioButton cat;
	private RadioButton dog;
	private RadioButton name;
	private RadioButton age;
	private RadioButton puppies;
	private Stage stage;

	public AdoptionHandler(RadioButton cat, RadioButton dog, RadioButton adoptionByName,
			RadioButton adoptionByAge, RadioButton adoptionPuppies, Stage stage){
		this.cat = cat;
		this.dog = dog;
		this.name = adoptionByName;
		this.age = adoptionByAge;
		this.puppies = adoptionPuppies;
		this.stage = stage;
	}
	
	public void handle(ActionEvent event){
		List<Animal> list = MainWindow.shelter.getAdoption().getList();
		List<Animal> res = new ArrayList<Animal>();
		if (name.isSelected()){
			for (Animal a : list){
				Adoption category = (Adoption) a.getAnimalCategory();
				if (category.isReady()){
					res.add(a);
				}
			}
			Collections.sort(res);
		}
		if (age.isSelected()){
			if (cat.isSelected()){
				for (Animal a : list) {
					if (a.getBreed().equals("Cat")){
						res.add(a);
					}
				}
			} else {
				for (Animal a : list) {
					if (a.getBreed().equals("Dog")){
						res.add(a);
					}
				}
			}
			Collections.sort(res, new AgeComparator());
		}
		if (puppies.isSelected()){
			for (Animal a : list){
				Adoption category = (Adoption) a.getAnimalCategory();
				if (a.getBreed().equals("Dog") && !category.isReady()){
					res.add(a);
				}
			}
			Collections.sort(res);
		}
		new ReportHandler(stage).fillAnimalByList(res);
	}
	
	public void disableBreed(){
		cat.setDisable(true);
		dog.setDisable(true);
	}
	
	public void enableBreed(){
		cat.setDisable(false);
		dog.setDisable(false);
	}
}
