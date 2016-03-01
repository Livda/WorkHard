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
public class SaveHandler implements EventHandler <ActionEvent> {
	
	public void handle(ActionEvent e) {
		Saver save = new Saver();
		save.save(MainWindow.shelter);
	}
}
