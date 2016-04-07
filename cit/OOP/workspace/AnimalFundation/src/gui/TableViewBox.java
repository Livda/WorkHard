package gui;

import api.*;
import controlers.DeleteHandler;
import controlers.FilterHandler;
import controlers.ShowHandler;
import controlers.NewHandler;
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
	TableView<Animal> table;

	@SuppressWarnings({"unchecked", "rawtypes"})
	public TableViewBox(){
		TableColumn<Animal,String> nameCol =
				new TableColumn<Animal,String>(Messages.getString("name"));
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Animal,String> breedCol =
        		new TableColumn<Animal,String>(Messages.getString("breed"));
        breedCol.setCellValueFactory(new PropertyValueFactory("breed"));
        TableColumn<Animal,String> categoryCol =
        		new TableColumn<Animal, String>(Messages.getString("category"));
        categoryCol.setCellValueFactory(
        		new PropertyValueFactory("animalCategory"));
        table = new TableView<Animal>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().setAll(nameCol, breedCol, categoryCol);

		CheckBox adoption = new CheckBox(Messages.getString("adoption"));
		adoption.setIndeterminate(false);
		adoption.setSelected(true);
		adoption.setOnAction(new FilterHandler());
		CheckBox found = new CheckBox(Messages.getString("found"));
		found.setIndeterminate(false);
		found.setSelected(true);
		found.setOnAction(new FilterHandler());
		CheckBox lost = new CheckBox(Messages.getString("lost"));
		lost.setIndeterminate(false);
		lost.setSelected(true);
		lost.setOnAction(new FilterHandler());
		HBox filterBox = new HBox(10);
		filterBox.getChildren().addAll(adoption, found, lost);
		filterBox.setAlignment(Pos.CENTER);

		Button newAnimal = new Button(Messages.getString("new"));
		newAnimal.setOnAction(new NewHandler());
		Button editAnimal = new Button(Messages.getString("view"));
		editAnimal.setOnAction(new ShowHandler());
        Button deleteAnimal = new Button(Messages.getString("delete"));
        deleteAnimal.setOnAction(new DeleteHandler());
        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(newAnimal, editAnimal, deleteAnimal);
        buttonBox.setAlignment(Pos.CENTER);

        box = new VBox(10);
        box.getChildren().addAll(filterBox, table, buttonBox);
	}

	public VBox getBox(){
		return box;
	}

	public TableView<Animal> getTable(){
		return table;
	}

	public void printShelter(){
		AnimalShelter shelter = MainWindow.shelter;
		table.getItems().clear();
		table.getItems().addAll(shelter.getAllAnimals());
	}
}
