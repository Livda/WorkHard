/**
 *
 */
package controlers;

import api.Animal;
import api.AnimalShelter;
import api.Person;
import gui.MainWindow;
import gui.Messages;
import gui.UpdatePersonBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class UpdatePersonHandler implements EventHandler<ActionEvent> {
	private Person person;
	private Animal animal;
	private Stage stage;
	private static UpdatePersonHandler INSTANCE = null;

	private UpdatePersonHandler(Animal a, Person p){
		this.animal = a;
		this.person = p;
	}

	public static UpdatePersonHandler getInstance(Animal a, Person p) {
		if (INSTANCE == null) INSTANCE = new UpdatePersonHandler(a, p);
		return INSTANCE;
	}

	public void handle(ActionEvent event){
		stage = new Stage();
		stage.initStyle(StageStyle.UTILITY);

		VBox box = new UpdatePersonBox(animal, person).getMainBox();
		Scene scene = new Scene(box);
		stage.setScene(scene);
		stage.setTitle(Messages.getString("update_person"));
		stage.show();
	}

	public void update(ActionEvent event) {
		AnimalShelter shelter = MainWindow.shelter;
		shelter.updatePerson(person);
		stage.close();
	}

	public void keep(ActionEvent event) {
		AnimalShelter shelter = MainWindow.shelter;
		int index = shelter.getAllPersons().indexOf(person);
		Person p = shelter.getAllPersons().get(index);
		animal.getAnimalCategory().setContact(p);
		stage.close();
	}
}
