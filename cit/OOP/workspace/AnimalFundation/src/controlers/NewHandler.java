/**
 * 
 */
package controlers;

import api.Animal;
import gui.AnimalBoxEditable;
import gui.MainWindow;
import gui.PersonBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class NewHandler implements EventHandler<ActionEvent> {
	private AnimalBoxEditable animalBox;
	private PersonBox personBox;
	
	public NewHandler(){
	}
	
	public NewHandler(Animal a){
		this.handle(new ActionEvent());
		this.setAnimal(a);
	}
	
	public void handle(ActionEvent event) {
		animalBox = new AnimalBoxEditable();
		VBox animal = animalBox.getBox();
		personBox = new PersonBox();
		VBox person = personBox.getBox();
		HBox box = new HBox(10);
		box.getChildren().addAll(animal, person);
		
		Button okButton = new Button("Ok");
		okButton.setOnAction(new CreateAnimalHandler(animalBox, personBox));
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> this.empty(e));
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(okButton, cancelButton);
		buttonBox.setAlignment(Pos.CENTER);
		VBox main = MainWindow.mainBox;
		main.getChildren().clear();
		main.getChildren().addAll(box, buttonBox);
	}
	
	public void empty(ActionEvent e){
		MainWindow.mainBox.getChildren().clear();
		Text nothingHere = new Text("Please select or create an animal");
		MainWindow.mainBox.getChildren().add(nothingHere);
	}
	
	private void setAnimal(Animal a){
		String aName = a.getName();
		animalBox.setName(aName);
		
		int age = a.getAge();
		animalBox.setAge(age);
		
		String description = a.getDescription();
		animalBox.setDescription(description);
		
		String breed = a.getBreed();
		animalBox.setBreed(breed);
	}
}
