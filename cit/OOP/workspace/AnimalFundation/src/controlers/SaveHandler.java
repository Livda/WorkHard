/**
 * 
 */
package controlers;

import api.AnimalShelter;
import api.Saver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class SaveHandler implements EventHandler <ActionEvent> {
	AnimalShelter shelter;
	
	public SaveHandler(AnimalShelter a) {
		this.shelter = a;
	}
	
	public void handle(ActionEvent e) {
		Saver save = new Saver();
		save.save(shelter);
	}
}
