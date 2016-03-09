/**
 * 
 */
package gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public abstract class Box {
	private GridPane grid;
	private VBox vBox;
	
	public static boolean errorInTheFields;
	
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
	
	public Box(String title) {
		this.grid = new GridPane();
		this.grid.setVgap(10);
		this.grid.setHgap(10);
		VBox.setMargin(grid, new Insets(10, 0, 10, 0));;
		this.vBox = new VBox();
		this.setTitle(title);
		vBox.getChildren().add(1, grid);
		vBox.setAlignment(Pos.TOP_CENTER);
	}

	/**
	 * @return the grid
	 */
	public GridPane getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

	/**
	 * @return the vBox
	 */
	public VBox getvBox() {
		return vBox;
	}

	/**
	 * @param vBox the vBox to set
	 */
	public void setvBox(VBox vBox) {
		this.vBox = vBox;
	}
	
	public void setTitle(String title){
		Text titleText = new Text(title);
		titleText.setFont(Font.font(null, FontWeight.BOLD, 25));
		this.vBox.getChildren().add(0, titleText);;
	}
	
	public boolean testEmptyField(TextField field){
		boolean test;
		String text = field.getText();
		if (text.equals("")){
			field.getStyleClass().add("error");
			errorInTheFields = true;
			test = true;
		} else {
			field.getStyleClass().remove("error");
			test = false;
		}
		return test;
	}
	
	public void testEmptyArea(TextArea area){
		String text = area.getText();
		if(text.equals("")){
			area.getStyleClass().add("error");
			errorInTheFields = true;
		} else {
			area.getStyleClass().remove("error");
		}
	}
}
