/**
 *
 */
package controlers;

import java.time.LocalDate;

import api.Adoption;
import api.Animal;
import api.Category;
import api.Found;
import api.Lost;
import api.Person;
import gui.AnimalBoxShow;
import gui.MainWindow;
import gui.Messages;
import gui.PersonBoxShow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ShowHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent e){
		TableView<Animal> table = MainWindow.table.getTable();
		Animal selected = table.getSelectionModel().getSelectedItem();
		AnimalBoxShow animalShow = new AnimalBoxShow();
		PersonBoxShow personShow = new PersonBoxShow();

		String name = selected.getName();
		animalShow.setName(name);

		int age = selected.getAge();
		animalShow.setAge(age);

		String colour = selected.getColour();
		animalShow.setColour(colour);

		String description = selected.getDescription();
		animalShow.setDescription(description);

		String breed = selected.getBreed();
		animalShow.setBreed(breed);

		String type = selected.getType();
		animalShow.setType(type);

		boolean gender = selected.getGender();
		animalShow.setGender(gender);

		Category category = selected.getAnimalCategory();
		String categoryText = category.toString();
		animalShow.setCategory(categoryText);

		LocalDate date = category.getDate();
		animalShow.setDate(date);

		if (categoryText.equals("Adoption")){
			int categoryInt = 0;
			animalShow.setCategoryGrid(categoryInt);
			Adoption adoption = (Adoption) category;
			boolean neutered = adoption.isNeutered();
			animalShow.setNeutered(neutered);

			boolean chipped = adoption.isChipped();
			animalShow.setChipped(chipped);

			boolean vaccinated = adoption.isVaccinated();
			animalShow.setVaccinated(vaccinated);

			boolean reserved = adoption.isReserved();
			animalShow.setReserved(reserved);

			boolean ready = adoption.isReady();
			animalShow.setReady(ready);
		} else {
			int categoryInt = 1;
			animalShow.setCategoryGrid(categoryInt);
			if (category.getCategoryLetter() == 'f') {
				Found found = (Found) category;
				String localisation = found.getLocation();
				animalShow.setLocation(localisation);
			} else {
				Lost lost = (Lost) category;
				String localisation = lost.getLocation();
				animalShow.setLocation(localisation);
			}
		}

		Person person = category.getContact();

		String pName = person.getName();
		personShow.setName(pName);

		String adress = person.getAdress();
		personShow.setAdress(adress);

		String phone = person.getPhone();
		personShow.setTelephone(phone);

		String email = person.getEmail();
		personShow.setEmail(email);

		HBox hBox = new HBox(50);
		hBox.getChildren().addAll(animalShow.getBox(), personShow.getBox());
		hBox.setAlignment(Pos.TOP_CENTER);

		Button editButton = new Button(Messages.getString("edit"));
		editButton.setOnAction(new EditHandler());

		Button deleteButton = new Button(Messages.getString("delete"));
		deleteButton.setOnAction(new DeleteHandler());

		Button cancleButton = new Button(Messages.getString("cancel"));
		cancleButton.setOnAction(new ClearHandler());

		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(editButton, deleteButton, cancleButton);
		buttonBox.setAlignment(Pos.CENTER);

		MainWindow.mainBox.getChildren().clear();
		MainWindow.mainBox.getChildren().addAll(hBox, buttonBox);
	}
}
