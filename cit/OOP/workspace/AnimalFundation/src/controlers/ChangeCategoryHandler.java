/**
 *
 */
package controlers;

import gui.AnimalBoxEditable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ChangeCategoryHandler implements EventHandler<ActionEvent>{
	private AnimalBoxEditable animalBox;
	private int category;

	public ChangeCategoryHandler(AnimalBoxEditable animalBox, int category){
		this.animalBox = animalBox;
		this.category = category;
	}

	public void handle(ActionEvent e) {
		this.animalBox.setCategoryGrid(category);
	}

}
