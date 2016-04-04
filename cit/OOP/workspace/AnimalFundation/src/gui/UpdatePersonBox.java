/**
 * 
 */
package gui;

import api.Animal;
import api.Person;
import controlers.UpdatePersonHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class UpdatePersonBox {
	private VBox mainBox;
	
	public UpdatePersonBox(Animal a, Person p){
		mainBox = new VBox(10);
		VBox.setMargin(mainBox, new Insets(20));
		
		String name = p.getName();
		Text updateText = new Text(name + " " + Messages.getString("warning_update_person"));
		updateText.setWrappingWidth(300);
		updateText.setTextAlignment(TextAlignment.CENTER);
		updateText.setFont(Font.font(null, FontWeight.BOLD, 15));
		
		
		UpdatePersonHandler handler = UpdatePersonHandler.getInstance(a, p);
		Button updateButton = new Button(Messages.getString("update"));
		updateButton.setOnAction(e -> handler.update(e));
		Button keepButton = new Button(Messages.getString("keep"));
		keepButton.setOnAction(e -> handler.keep(e));
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(updateButton, keepButton);
		
		mainBox.getChildren().addAll(updateText, buttonBox);
	}
	
	public VBox getMainBox(){
		return mainBox;
	}
}
