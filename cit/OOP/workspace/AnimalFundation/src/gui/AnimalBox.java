package gui;

import java.time.LocalDate;

import controlers.NewHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
	private VBox boxFromMain;
	private VBox box;
	private int category;

	public AnimalBox(VBox boxFromMain, int category){
		this.boxFromMain = boxFromMain;
		this.category = category;
		
		GridPane animalGrid = new GridPane();
		animalGrid.setVgap(10);
		animalGrid.setHgap(10);

		Text animalName = new Text("Animal name :");
		animalGrid.add(animalName, 0, 0);

		TextField setAnimalName = new TextField();
		setAnimalName.setPromptText("Ex : Leonard");
		animalGrid.add(setAnimalName, 1, 0);

		Text animalAge = new Text("Age :");
		animalGrid.add(animalAge, 0, 1);

		TextField ageNumber = new TextField();
		ageNumber.setPromptText("Ex : 2");
		animalGrid.add(ageNumber, 1, 1);

		Text animalColor = new Text("Color :");
		animalGrid.add(animalColor, 0, 2);

		TextField setAnimalColor = new TextField();
		setAnimalColor.setPromptText("Ex : Black & White");
		animalGrid.add(setAnimalColor, 1, 2);

		Text animalDescription = new Text("Description :");
		animalGrid.add(animalDescription, 0, 3);

		TextArea description = new TextArea();
		description.setMaxSize(300, 50);
		description.setPromptText("Likes to be pet");
		animalGrid.add(description, 1, 3);

		Text animalBreed = new Text("Breed :");
		animalGrid.add(animalBreed, 0, 4);

		ChoiceBox<String> setAnimalBreed = new ChoiceBox<String>();
		setAnimalBreed.getItems().addAll("Dog", "Cat");
		setAnimalBreed.getSelectionModel().selectFirst();
		animalGrid.add(setAnimalBreed, 1, 4);

		Text animalType = new Text("Type :");
		animalGrid.add(animalType, 0, 5);

		TextField setAnimalType = new TextField();
		setAnimalType.setPromptText("Ex : Shiba Inu");
		animalGrid.add(setAnimalType, 1, 5);    

		Text animalGender = new Text("Gender :");
		ToggleGroup genderGroup = new ToggleGroup();
		RadioButton maleBox = new RadioButton("Male");
		maleBox.setToggleGroup(genderGroup);
		maleBox.setSelected(true);
		RadioButton femaleBox = new RadioButton("Female");
		femaleBox.setToggleGroup(genderGroup);
		HBox gender = new HBox(15);
		gender.getChildren().addAll(maleBox, femaleBox);
		animalGrid.add(animalGender, 0, 6);
		animalGrid.add(gender, 1, 6);
		gender.setAlignment(Pos.CENTER);

		Text date = new Text("Date :");
		animalGrid.add(date, 0, 7);

		DatePicker datePicker = new DatePicker(LocalDate.now());
		animalGrid.add(datePicker, 1, 7);

		box = new VBox(20);
		Text animalTitle = new Text("Animal");
		animalTitle.setFont(Font.font(null, FontWeight.BOLD, 25));
		box.getChildren().addAll(animalTitle, animalGrid);
		box.setAlignment(Pos.TOP_CENTER);
		
		Text animalCategory = new Text("Category :");
		animalGrid.add(animalCategory, 0, 8);
		
		ToggleGroup categoryGroup = new ToggleGroup();
		RadioButton adoptionButton = new RadioButton("Adoption");
		adoptionButton.setToggleGroup(categoryGroup);
		if (category == 0)	adoptionButton.setSelected(true);
		adoptionButton.setOnAction(new NewHandler(boxFromMain, 0));
		RadioButton foundButton = new RadioButton("Found");
		foundButton.setToggleGroup(categoryGroup);
		if (category == 1)	foundButton.setSelected(true);
		foundButton.setOnAction(new NewHandler(boxFromMain, 1));
		RadioButton lostButton = new RadioButton("Lost");
		lostButton.setToggleGroup(categoryGroup);
		if (category == 2)	lostButton.setSelected(true);
		lostButton.setOnAction(new NewHandler(boxFromMain, 2));
		HBox categoryBox = new HBox(10);
		categoryBox.getChildren().addAll(adoptionButton, foundButton, lostButton);
		categoryBox.setAlignment(Pos.CENTER);
		animalGrid.add(categoryBox, 1, 8);

		if (this.category == 0) {
			Text neuteredText = new Text("Neutered :");
			animalGrid.add(neuteredText, 0, 9);

			ToggleGroup neuteredGroup = new ToggleGroup();
			RadioButton neuteredBox = new RadioButton("Yes");
			neuteredBox.setToggleGroup(neuteredGroup);
			neuteredBox.setSelected(true);
			RadioButton nonNeuteredBox = new RadioButton("No");
			nonNeuteredBox.setToggleGroup(neuteredGroup);
			HBox neutered = new HBox(15);
			neutered.getChildren().addAll(neuteredBox, nonNeuteredBox);
			animalGrid.add(neutered, 1, 9);
			neutered.setAlignment(Pos.CENTER);   

			Text chippedText = new Text("Chipped :");
			animalGrid.add(chippedText, 0, 10);

			ToggleGroup chippedGroup = new ToggleGroup();
			RadioButton chippedBox = new RadioButton("Yes");
			chippedBox.setToggleGroup(chippedGroup);
			chippedBox.setSelected(true);
			RadioButton nonChippedBox = new RadioButton("No");
			nonChippedBox.setToggleGroup(chippedGroup);
			HBox chipped = new HBox(15);
			chipped.getChildren().addAll(chippedBox, nonChippedBox);
			animalGrid.add(chipped, 1, 10);
			chipped.setAlignment(Pos.CENTER);

			Text vaccinatedText = new Text("Vaccinated :");
			animalGrid.add(vaccinatedText, 0, 11);

			ToggleGroup vaccinatedGroup = new ToggleGroup();
			RadioButton vaccinatedBox = new RadioButton("Yes");
			vaccinatedBox.setToggleGroup(vaccinatedGroup);
			vaccinatedBox.setSelected(true);
			RadioButton nonVaccinatedBox = new RadioButton("No");
			nonVaccinatedBox.setToggleGroup(vaccinatedGroup);
			HBox vaccinated = new HBox(15);
			vaccinated.getChildren().addAll(vaccinatedBox, nonVaccinatedBox);
			animalGrid.add(vaccinated, 1, 11);
			vaccinated.setAlignment(Pos.CENTER);   
		}
		else {
			Text locationText = new Text("Location :");
			animalGrid.add(locationText, 0, 9);

			TextField locationField = new TextField();
			locationField.setPromptText("Ex : In the dark forest");
			animalGrid.add(locationField, 1, 9);
		}

	}

	public VBox getBox(){
		return box;
	}
}
