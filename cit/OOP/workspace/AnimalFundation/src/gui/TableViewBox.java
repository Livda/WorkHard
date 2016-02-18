package gui;

import api.Animal;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewBox {
	private TableView<Animal> table;
	
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
	}
	
	public TableView<Animal> getBox(){
		return getTable();
	}

	public TableView<Animal> getTable() {
		return table;
	}

	public void setTable(TableView<Animal> table) {
		this.table = table;
	}
}
