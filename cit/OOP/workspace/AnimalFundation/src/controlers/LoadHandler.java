/**
 * 
 */
package controlers;

import api.Saver;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class LoadHandler implements EventHandler <ActionEvent> {
	
	public void handle(ActionEvent e) {
		Saver saver = new Saver();
		MainWindow.shelter = saver.load();
		MainWindow.table.printShelter();;
	}
}
