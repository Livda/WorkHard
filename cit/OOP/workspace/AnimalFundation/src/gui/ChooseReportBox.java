/**
 * 
 */
package gui;

import controlers.ReportHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
public class ChooseReportBox {
	private VBox mainBox;
	
	public ChooseReportBox(){
		this.mainBox = new VBox(10);
		mainBox.setAlignment(Pos.CENTER);
		VBox.setMargin(mainBox, new Insets(20));
		
		Text chooseYourSort = new Text("Choose the way you want to sort "
				+ "the animal in the report");
		chooseYourSort.setWrappingWidth(300);
		chooseYourSort.setTextAlignment(TextAlignment.CENTER);
		chooseYourSort.setFont(Font.font(null, FontWeight.BOLD, 15));
		
		RadioButton byName = new RadioButton("Name");
		byName.setSelected(true);
		RadioButton byCategory = new RadioButton("Category");
		ToggleGroup sortGroup = new ToggleGroup();
		byName.setToggleGroup(sortGroup);
		byCategory.setToggleGroup(sortGroup);
		
		HBox sortBox = new HBox(10);
		sortBox.getChildren().addAll(byName, byCategory);
		sortBox.setAlignment(Pos.CENTER);
		
		Button generateButton = new Button("Generate report");
		mainBox.getChildren().addAll(chooseYourSort, sortBox, generateButton);
		
		generateButton.setOnAction(new ReportHandler(sortBox));
	}
	
	public VBox getMainBox(){
		return this.mainBox;
	}
}
