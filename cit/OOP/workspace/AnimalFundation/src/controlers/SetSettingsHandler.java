/**
 *
 */
package controlers;

import java.util.Locale;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import reportControlers.ReportHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class SetSettingsHandler implements EventHandler<ActionEvent> {
	private Stage stage;
	private GridPane grid;

	public SetSettingsHandler(Stage stage, GridPane grid){
		this.stage = stage;
		this.grid = grid;
	}

	public void handle(ActionEvent event){
		Node node;
		//Set the locale
		node = getNodeByRowColumnIndex(0, 1, grid);
		@SuppressWarnings("unchecked")
		ChoiceBox<String> languages = (ChoiceBox<String>) node;
		String language = languages.getSelectionModel().getSelectedItem();
		System.out.println(language);
		switch (language) {
		case "English" :
			Locale.setDefault(new Locale("us"));
			break;
		case "French" :
			Locale.setDefault(new Locale("fr"));
			break;
		}

		//Set the path to the latex compiler
		node = getNodeByRowColumnIndex(0, 3, grid);
		ReportHandler.pathToPdfLatex = ((TextField) node).getText();

		//Set the path to the PDF Reader
		node = getNodeByRowColumnIndex(1, 3, grid);
		ReportHandler.pathToPdfReader = ((TextField) node).getText();

		//Close the pop up window
		stage.close();
	}

	@SuppressWarnings("static-access")
	protected Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane){
		Node result = null;
		ObservableList<Node> children = gridPane.getChildren();
		for (Node node : children){
			if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}
		return result;
	}
}
