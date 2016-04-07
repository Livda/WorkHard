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
import gui.AnimalBoxEditable;
import gui.MainWindow;
import gui.PersonBoxEditable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class EditHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent e){
		AnimalBoxEditable animalBox = new AnimalBoxEditable();
		PersonBoxEditable personBox = new PersonBoxEditable();
		Animal a = MainWindow.table.getTable().getSelectionModel().getSelectedItem();
		Category c = a.getAnimalCategory();
		Person p = c.getContact();

		//Fill the animal box
		String aName = a.getName();
		animalBox.setName(aName);

		int age = a.getAge();
		animalBox.setAge(age);

		String colour = a.getColour();
		animalBox.setColour(colour);

		String description = a.getDescription();
		animalBox.setDescription(description);

		String breed = a.getBreed();
		animalBox.setBreed(breed);

		String type = a.getType();
		animalBox.setType(type);

		LocalDate date = c.getDate();
		animalBox.setDate(date);

		String category = c.toString();
		animalBox.setCategory(category);

		char letter = c.getCategoryLetter();
		switch (letter){
		case 'a' :
			Adoption aCat = (Adoption)c;
			boolean neutered = aCat.isNeutered();
			animalBox.setNeutered(neutered);

			boolean chipped = aCat.isChipped();
			animalBox.setChipped(chipped);

			boolean vaccinated = aCat.isVaccinated();
			animalBox.setVaccinated(vaccinated);

			boolean reserved = aCat.isReserved();
			animalBox.setReserved(reserved);

			boolean ready = aCat.isReady();
			animalBox.setReady(ready);
			break;
		case 'f':
			Found fCat = (Found)c;
			String fLocalisation = fCat.getLocation();
			animalBox.setLocation(fLocalisation);
			break;
		case 'l':
			Lost lCat = (Lost)c;
			String lLocalisation = lCat.getLocation();
			animalBox.setLocation(lLocalisation);

		}

		//Fill the person box
		String pName = p.getName();
		personBox.setName(pName);

		String adress = p.getAdress();
		personBox.setAdress(adress);

		String phone = p.getPhone();
		personBox.setTelephone(phone);

		String email = p.getEmail();
		personBox.setEmail(email);

		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(animalBox.getBox(), personBox.getvBox());

		Button okButton = new Button("Ok");
		okButton.setOnAction(new EditAnimalHandler(animalBox, personBox));
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new ShowHandler());
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(okButton, cancelButton);

		MainWindow.mainBox.getChildren().clear();
		MainWindow.mainBox.getChildren().addAll(hBox, buttonBox);
	}
}
