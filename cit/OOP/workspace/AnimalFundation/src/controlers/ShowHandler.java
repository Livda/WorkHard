/**
 * 
 */
package controlers;

import java.util.Calendar;

import api.Adoption;
import api.Animal;
import api.Category;
import api.Found;
import api.Lost;
import gui.AnimalBoxShow;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ShowHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent e){
		TableView<Animal> table = MainWindow.table.getTable();
		Animal selected = table.getSelectionModel().getSelectedItem();
		AnimalBoxShow show = new AnimalBoxShow();
		
		String name = selected.getName();
		show.setName(name);

		int age = selected.getAge();
		show.setAge(age);

		String colour = selected.getColour();
		show.setColour(colour);

		String description = selected.getDescription();
		show.setDescription(description);

		String breed = selected.getBreed();
		show.setBreed(breed);

		String type = selected.getType();
		show.setType(type);

		boolean gender = selected.getGender();
		show.setGender(gender);

		Category category = selected.getAnimalCategory();
		String categoryText = category.toString();
		show.setCategory(categoryText);

		Calendar date = category.getDate();
		show.setDate(date);

		if (categoryText.equals("Adoption")){
			int categoryInt = 0;
			show.setCategoryGrid(categoryInt);
			Adoption adoption = (Adoption) category;
			boolean neutered = adoption.isNeutered();
			show.setNeutered(neutered);
			
			boolean chipped = adoption.isChipped();
			show.setChipped(chipped);
			
			boolean vaccinated = adoption.isVaccinated();
			show.setVaccinated(vaccinated);
		} else {
			int categoryInt = 1;
			show.setCategoryGrid(categoryInt);
			if (category.getCategoryLetter() == 'f') {
				Found found = (Found) category;
				String localisation = found.getLocation();
				show.setLocalisation(localisation);
			} else {
				Lost lost = (Lost) category;
				String localisation = lost.getLocation();
				show.setLocalisation(localisation);
			}
		}
		
		Button editButton = new Button("Edit");
		editButton.setOnAction(new NewHandler(selected));
		
		MainWindow.mainBox.getChildren().clear();
		MainWindow.mainBox.getChildren().addAll(show.getvBox(), editButton);
	}
}
