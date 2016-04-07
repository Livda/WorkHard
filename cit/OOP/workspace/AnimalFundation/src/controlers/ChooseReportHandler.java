/**
 *
 */
package controlers;

import gui.ChooseReportBox;
import gui.Messages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ChooseReportHandler implements EventHandler<ActionEvent> {

	public void handle(ActionEvent event){
		Stage stagePopUp = new Stage();
		stagePopUp.initStyle(StageStyle.UTILITY);

		HBox box = new ChooseReportBox(stagePopUp).getMainBox();
		Scene scene = new Scene(box);
		stagePopUp.setScene(scene);
		stagePopUp.setTitle(Messages.getString("report_selection"));
		stagePopUp.setHeight(300);
		stagePopUp.show();
	}
}
