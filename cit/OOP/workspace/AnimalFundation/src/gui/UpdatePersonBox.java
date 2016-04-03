/**
 * 
 */
package gui;

import api.Person;
import controlers.UpdatePersonHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class UpdatePersonBox {
	private VBox mainBox;
	
	public UpdatePersonBox(Person p){
		mainBox = new VBox(10);
		VBox.setMargin(mainBox, new Insets(20));
		
		String name = p.getName();
		Text updateText = new Text(name + Messages.getString("warning_update_person"));
		
		Button updateButton = new Button(Messages.getString("update"));
		updateButton.setOnAction(new UpdatePersonHandler(p));
		Button keepButton = new Button(Messages.getString("keep"));
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(updateButton, keepButton);
		
		mainBox.getChildren().addAll(updateText, buttonBox);
	}
	
	public VBox getMainBox(){
		return mainBox;
	}
}
