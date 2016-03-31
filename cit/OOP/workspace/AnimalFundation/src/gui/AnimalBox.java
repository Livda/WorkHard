package gui;

import java.util.Calendar;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class AnimalBox extends Box {
	private GridPane animalGrid;
	private GridPane categoryGrid;
	private VBox vBox;

	public AnimalBox() {
		super(Messages.getString("animal"));
		animalGrid = this.getGrid();
		categoryGrid = new GridPane();
		categoryGrid.setVgap(10);
		categoryGrid.setHgap(10);
		vBox = this.getvBox();
		vBox.getChildren().add(2, categoryGrid);

		Text animalName = new Text(Messages.getString("animal_name") + " :");
		animalGrid.add(animalName, 0, 0);

		Text animalAge = new Text(Messages.getString("age") + " :");
		animalGrid.add(animalAge, 0, 1);

		Text animalColor = new Text(Messages.getString("color") + " :");
		animalGrid.add(animalColor, 0, 2);

		Text animalDescription = new Text(Messages.getString("description") + " :");
		animalGrid.add(animalDescription, 0, 3);

		Text animalBreed = new Text(Messages.getString("breed") + " :");
		animalGrid.add(animalBreed, 0, 4);

		Text animalType = new Text(Messages.getString("type") + " :");
		animalGrid.add(animalType, 0, 5);
		
		Text animalGender = new Text(Messages.getString("gender") + " :");
		animalGrid.add(animalGender, 0, 6);

		Text date = new Text(Messages.getString("date") + " :");
		animalGrid.add(date, 0, 7);

		Text animalCategory = new Text(Messages.getString("category") + " :");
		animalGrid.add(animalCategory, 0, 8);
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(26);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(74);
		categoryGrid.getColumnConstraints().addAll(col1, col2);
	}

	public VBox getBox(){
		return vBox;
	}

	public GridPane getAnimalGrid(){
		return animalGrid;
	}
	
	public GridPane getCategoryGrid(){
		return categoryGrid;
	}
	public abstract void setCategoryGrid(int i);
	
	public abstract void setName(String name);
	public abstract void setAge(int age);
	public abstract void setColour(String colour);
	public abstract void setDescription(String description);
	public abstract void setBreed(String breed);
	public abstract void setType(String type);
	public abstract void setGender(boolean gender);
	public abstract void setDate(Calendar date);
	public abstract void setCategory(String category);
	public abstract void setLocalisation(String localisation);
	public abstract void setNeutered(boolean neutered);
	public abstract void setChipped(boolean chipped);
	public abstract void setVaccinated(boolean vaccinated);
	
}