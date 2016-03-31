/**
 * 
 */
package controlers;

import gui.MainWindow;
import gui.Messages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ClearHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent e){
		MainWindow.mainBox.getChildren().clear();
		Text nothingHere = new Text(Messages.getString("select_create_animal"));
		MainWindow.mainBox.getChildren().add(nothingHere);
	}
}
