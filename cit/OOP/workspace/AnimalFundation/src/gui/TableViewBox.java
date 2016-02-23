package gui;

import api.Animal;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TableViewBox {
	private VBox box;
	private TableView<Animal> table;
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public TableViewBox(){
		TableColumn<Animal,String> firstNameCol = 
				new TableColumn<Animal,String>("Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Animal,String> breedCol = 
        		new TableColumn<Animal,String>("Breed");
        TableColumn<Animal,String> categoryCol = 
        		new TableColumn<Animal, String>("Category");
		this.setTable(new TableView<Animal>());
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getTable().getColumns().setAll(firstNameCol, breedCol, categoryCol);
		
		CheckBox adoption = new CheckBox("Adoption");
		adoption.setIndeterminate(false);
		adoption.setSelected(true);
		CheckBox found = new CheckBox("Found");
		found.setIndeterminate(false);
		found.setSelected(true);
		CheckBox lost = new CheckBox("Lost");
		lost.setIndeterminate(false);
		lost.setSelected(true);
		HBox filterBox = new HBox(10);
		filterBox.getChildren().addAll(adoption, found, lost);
		filterBox.setAlignment(Pos.CENTER);
		
		Button newAnimal = new Button("New");
		Button editAnimal = new Button("Edit");
        Button deleteAnimal = new Button("Delete");
        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(newAnimal, editAnimal, deleteAnimal);
        buttonBox.setAlignment(Pos.CENTER);
        
        box = new VBox(10);
        box.getChildren().addAll(filterBox, table, buttonBox);
	}
	
	public VBox getBox(){
		return box;
	}

	public TableView<Animal> getTable() {
		return table;
	}

	public void setTable(TableView<Animal> table) {
		this.table = table;
	}
}
