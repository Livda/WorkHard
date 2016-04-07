/**
 *
 */
package controlers;

import gui.AnimalBoxEditable;
import gui.MainWindow;
import gui.Messages;
import gui.PersonBoxEditable;
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
	private AnimalBoxEditable animalBox;
	private PersonBoxEditable personBox;

	public void handle(ActionEvent event) {
		animalBox = new AnimalBoxEditable();
		VBox animal = animalBox.getBox();
		personBox = new PersonBoxEditable();
		VBox person = personBox.getBox();
		HBox box = new HBox(10);
		box.getChildren().addAll(animal, person);

		Button okButton = new Button(Messages.getString("ok"));
		okButton.setOnAction(new CreateAnimalHandler(animalBox, personBox));
		Button cancelButton = new Button(Messages.getString("cancel"));
		cancelButton.setOnAction(new ClearHandler());
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(okButton, cancelButton);
		buttonBox.setAlignment(Pos.CENTER);
		VBox main = MainWindow.mainBox;
		main.getChildren().clear();
		main.getChildren().addAll(box, buttonBox);
	}
}
