/**
 * 
 */
package controlers;

import gui.SettingsBox;
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
public class SettingsHandler implements EventHandler<ActionEvent>{
	
	public void handle(ActionEvent event){
		Stage settingsPopUp = new Stage();
		settingsPopUp.initStyle(StageStyle.UTILITY);
		
		VBox box = new SettingsBox(settingsPopUp).getMainGrid();
		Scene scene = new Scene(box);
		settingsPopUp.setScene(scene);
		settingsPopUp.setTitle("Settings");
		settingsPopUp.show();
	}

}
