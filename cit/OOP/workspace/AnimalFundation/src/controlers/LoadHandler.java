/**
 *
 */
package controlers;

import api.DataBaseManager;
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
		DataBaseManager dbm = DataBaseManager.getDataBaseManager();
		MainWindow.shelter = dbm.getSavedShelter();
		MainWindow.table.printShelter();
	}
}
