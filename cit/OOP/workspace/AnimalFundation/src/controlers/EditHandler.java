/**
 * 
 */
package controlers;

import java.util.Calendar;

import api.Animal;
import api.Category;
import gui.AnimalBoxShow;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class EditHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent e){
		TableView<Animal> table = MainWindow.table.getTable();
		Animal selected = table.getSelectionModel().getSelectedItem();
		String name = selected.getName();
		int age = selected.getAge();
		String colour = selected.getColour();
		String description = selected.getDescription();
		String breed = selected.getBreed();
		String type = selected.getType();
		boolean gender = selected.getGender();
		Category category = selected.getAnimalCategory();
		Calendar date = category.getDate();
		String categoryText = category.toString();
		
		AnimalBoxShow show = new AnimalBoxShow();
		show.setName(name);
		show.setAge(age);
		show.setColour(colour);
		show.setDescription(description);
		show.setBreed(breed);
		show.setType(type);
		show.setGender(gender);
		show.setDate(date);
		show.setCategory(categoryText);
		
		MainWindow.mainBox.getChildren().clear();
		MainWindow.mainBox.getChildren().add(show.getvBox());
	}
}
