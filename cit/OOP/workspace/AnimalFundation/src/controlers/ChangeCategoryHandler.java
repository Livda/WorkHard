/**
 * 
 */
package controlers;

import gui.AnimalBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ChangeCategoryHandler implements EventHandler<ActionEvent>{
	private AnimalBox animalBox;
	private int category;
	
	public ChangeCategoryHandler(AnimalBox animalBox, int category){
		this.animalBox = animalBox;
		this.category = category;		
	}
	
	public void handle(ActionEvent e) {
		this.animalBox.setCategory(category);
	}

}
