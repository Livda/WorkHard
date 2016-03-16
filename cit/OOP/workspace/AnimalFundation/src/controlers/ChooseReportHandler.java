/**
 * 
 */
package controlers;

import gui.ChooseReportBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ChooseReportHandler implements EventHandler<ActionEvent> {
	private Stage dialog;

	public void handle(ActionEvent event){
		dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		
		VBox box = new ChooseReportBox().getMainBox();
		Scene scene = new Scene(box);
		dialog.setScene(scene);
		dialog.setTitle("Selection");
		dialog.show();
	}
}
