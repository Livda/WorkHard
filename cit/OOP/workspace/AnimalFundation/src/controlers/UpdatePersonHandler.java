/**
 * 
 */
package controlers;

import api.AnimalShelter;
import api.Person;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class UpdatePersonHandler implements EventHandler<ActionEvent> {
	private Person person;
	
	public UpdatePersonHandler(Person p){
		this.person = p;
	}
	
	public void handle(ActionEvent event){
		AnimalShelter shelter = MainWindow.shelter;
		shelter.updatePerson(person);
	}
}
