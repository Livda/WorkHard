package gui;

import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AnimalBox {
	private VBox box;
	
	public AnimalBox(){
	GridPane animalGrid = new GridPane();
    animalGrid.setVgap(10);
    animalGrid.setHgap(10);
    Text animalName = new Text("Name :");
    TextField setAnimalName = new TextField();
    Text animalAge = new Text("Age :");
    TextField setAnimalAge = new TextField();
    Text animalColor = new Text("Color :");
    TextField setAnimalColor = new TextField();
    Text animalGender = new Text("Gender :");
    ToggleGroup group = new ToggleGroup();
    RadioButton maleBox = new RadioButton("Male");
    maleBox.setToggleGroup(group);
    maleBox.setSelected(true);
    RadioButton femaleBox = new RadioButton("Female");
    femaleBox.setToggleGroup(group);
    HBox gender = new HBox(15);
    gender.getChildren().addAll(maleBox, femaleBox);
    Text animalDescription = new Text("Description :");
    TextArea description = new TextArea();
    description.setMaxSize(300, 50);
    Text animalBreed = new Text("Breed :");
    TextField setAnimalBreed = new TextField();
    Text animalType = new Text("Type :");
    TextField setAnimalType = new TextField();
    
    animalGrid.add(animalName, 0, 0);
    animalGrid.add(setAnimalName, 1, 0);
    animalGrid.add(animalAge, 0, 1);
    animalGrid.add(setAnimalAge, 1, 1);
    animalGrid.add(animalColor, 0, 2);
    animalGrid.add(setAnimalColor, 1, 2);
    animalGrid.add(animalDescription, 0, 3);
    animalGrid.add(description, 1, 3);
    animalGrid.add(animalBreed, 0, 4);
    animalGrid.add(setAnimalBreed, 1, 4);
    animalGrid.add(animalType, 0, 5);
    animalGrid.add(setAnimalType, 1, 5);
    animalGrid.add(animalGender, 0, 6);
    animalGrid.add(gender, 1, 6);
    gender.setAlignment(Pos.CENTER);
    
    box = new VBox(20);
    Text animalTitle = new Text("Animal");
    animalTitle.setFont(Font.font(null, FontWeight.BOLD, 25));
    box.getChildren().addAll(animalTitle, animalGrid);
    box.setAlignment(Pos.TOP_CENTER);
	}
	
	public VBox getBox(){
		return box;
	}
}
