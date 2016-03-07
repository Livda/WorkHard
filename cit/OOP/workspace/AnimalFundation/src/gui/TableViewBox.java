package gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import api.*;
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
	private MainWindow main;
	TableView<Animal> table;
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public TableViewBox(MainWindow main){
		this.main = main;
		TableColumn<Animal,String> nameCol = 
				new TableColumn<Animal,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn<Animal,String> breedCol = 
        		new TableColumn<Animal,String>("Breed");
        breedCol.setCellValueFactory(new PropertyValueFactory("breed"));
        TableColumn<Animal,String> categoryCol = 
        		new TableColumn<Animal, String>("Category");
        categoryCol.setCellValueFactory(
        		new PropertyValueFactory("animalCategory"));
        table = new TableView<Animal>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getColumns().setAll(nameCol, breedCol, categoryCol);
		
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
		newAnimal.setOnAction(new NewHandler(main.getMainBox()));
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
	
	public TableView<Animal> getTable(){
		return table;
	}
	
	private class newHandler implements EventHandler <ActionEvent> {
		public void handle(ActionEvent e){
			ObservableList<Animal> allData = table.getItems();
			allData.add(new Animal());
			table.setItems(allData);
		}
	}
	
	public void printShelter(){
		AnimalShelter shelter = MainWindow.shelter;
		table.getItems().clear();
		table.getItems().addAll(shelter.getAllAnimals());
	}
}
