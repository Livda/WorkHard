/**
 * 
 */
package controlers;

import gui.AnimalBox;
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
	private VBox main;
	private int category;
	
	public NewHandler(VBox main, int category){
		this.main = main;
		this.category = category;
	}
	
	public void handle(ActionEvent event) {
		VBox animal = new AnimalBox(main, category).getBox();
		VBox person = new PersonBox().getBox();
		HBox box = new HBox(10);
		box.getChildren().addAll(animal, person);
		
		Button okButton = new Button("Ok");
		Button cancelButton = new Button("Cancel");
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().addAll(okButton, cancelButton);
		buttonBox.setAlignment(Pos.CENTER);
		main.getChildren().clear();
		main.getChildren().addAll(box, buttonBox);
	}
}
