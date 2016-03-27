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
	private Stage stagePopUp;

	public void handle(ActionEvent event){
		stagePopUp = new Stage();
		stagePopUp.initStyle(StageStyle.UTILITY);
		
		VBox box = new ChooseReportBox(stagePopUp).getMainBox();
		Scene scene = new Scene(box);
		stagePopUp.setScene(scene);
		stagePopUp.setTitle("Report selection");
		stagePopUp.show();
	}
}
