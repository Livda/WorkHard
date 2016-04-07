/**
 *
 */
package controlers;

import api.Animal;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class DeleteHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent e){
		TableView<Animal> table = MainWindow.table.getTable();
		Animal selected = table.getSelectionModel().getSelectedItem();

		new ClearHandler().handle(e);
		MainWindow.shelter.remove(selected);
		MainWindow.table.printShelter();
	}
}
