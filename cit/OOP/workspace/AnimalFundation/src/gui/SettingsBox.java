/**
 * 
 */
package gui;

import controlers.SetSettingsHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class SettingsBox {
	private VBox mainBox;
	private GridPane grid;
	
	public SettingsBox(Stage stage){
		mainBox = new VBox(10);
		VBox.setMargin(mainBox, new Insets(20));
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		
		Text languageText = new Text("Language" + " :");
		grid.add(languageText, 0, 0);
		
		ChoiceBox<String> language = new ChoiceBox<>();
		language.getItems().addAll("English", "French");
		language.getSelectionModel().selectFirst();
		grid.add(language, 1, 0);
		
		Text latexText = new Text("Path to pdflatex");
		grid.add(latexText, 2, 0);
		TextField latex = new TextField("/usr/bin/pdflatex");
		grid.add(latex, 3, 0);
		
		Text pdfText = new Text ("Path to pdf reader");
		grid.add(pdfText, 2, 1);
		TextField pdfReader = new TextField("/usr/bin/evince");
		grid.add(pdfReader, 3, 1);
		
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		Button okButton = new Button("Ok");
		okButton.setOnAction(new SetSettingsHandler(stage, grid));
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(e -> stage.close());
		buttonBox.getChildren().addAll(okButton, cancelButton);
		mainBox.getChildren().addAll(grid, buttonBox);
	}
	
	public VBox getMainGrid(){
		return mainBox;
	}
}
