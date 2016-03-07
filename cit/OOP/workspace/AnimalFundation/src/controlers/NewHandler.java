/**
 * 
 */
package controlers;

import gui.AnimalBox;
import gui.MainWindow;
import gui.PersonBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class NewHandler implements EventHandler<ActionEvent> {
	
	public NewHandler(){
	}
	
	public void handle(ActionEvent event) {
		AnimalBox animalBox = new AnimalBox();
		VBox animal = animalBox.getBox();
		PersonBox personBox = new PersonBox();
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
	}
}
