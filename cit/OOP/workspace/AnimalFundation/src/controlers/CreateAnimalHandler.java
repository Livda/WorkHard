/**
 * 
 */
package controlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class CreateAnimalHandler implements EventHandler<ActionEvent>{
	private GridPane animal;
	private GridPane person;
	
	public CreateAnimalHandler(GridPane animal, GridPane person){
		this.animal = animal;
		this.person = person;
	}
	
	public void handle(ActionEvent event){
		System.out.println("Creation");
	}
}
