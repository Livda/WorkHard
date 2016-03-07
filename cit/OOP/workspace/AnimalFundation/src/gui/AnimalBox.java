package gui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import controlers.ChangeCategoryHandler;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
	private GridPane animalGrid;
	private GridPane categoryPane;
	private VBox box;

	public AnimalBox(){
		
		animalGrid = new GridPane();
		animalGrid.setVgap(10);
		animalGrid.setHgap(10);
		
		categoryPane = new GridPane();
		categoryPane.setVgap(10);
		categoryPane.setHgap(10);

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
		box.getChildren().addAll(animalTitle, animalGrid, categoryPane);
		box.setAlignment(Pos.TOP_CENTER);
		
		Text animalCategory = new Text("Category :");
		animalGrid.add(animalCategory, 0, 8);
		
		ToggleGroup categoryGroup = new ToggleGroup();
		RadioButton adoptionButton = new RadioButton("Adoption");
		adoptionButton.setToggleGroup(categoryGroup);
		adoptionButton.setSelected(true);
		adoptionButton.setOnAction(new ChangeCategoryHandler(this, 0));
		RadioButton foundButton = new RadioButton("Found");
		foundButton.setToggleGroup(categoryGroup);
		foundButton.setOnAction(new ChangeCategoryHandler(this, 1));
		RadioButton lostButton = new RadioButton("Lost");
		lostButton.setToggleGroup(categoryGroup);
		lostButton.setOnAction(new ChangeCategoryHandler(this, 2));
		HBox categoryBox = new HBox(10);
		categoryBox.getChildren().addAll(adoptionButton, foundButton, lostButton);
		categoryBox.setAlignment(Pos.CENTER);
		animalGrid.add(categoryBox, 1, 8);
		
		this.setCategory(0);
	}

	public VBox getBox(){
		return box;
	}
	
	public GridPane getAnimalGrid(){
		return animalGrid;
	}
	
	public String getName(){
		Node node = getNodeByRowColumnIndex(0, 1, animalGrid);
		String name = ((TextField) node).getText();
		return name;
	}
	
	public int getAge(){
		Node node = getNodeByRowColumnIndex(1, 1, animalGrid);
		int age = Integer.parseInt(((TextField) node).getText());
		return age;
	}
	
	public String getColour(){
		Node node = getNodeByRowColumnIndex(2, 1, animalGrid);
		String colour = ((TextField) node).getText();
		return colour;
	}
	
	public String getDescrition(){
		Node node = getNodeByRowColumnIndex(3, 1, animalGrid);
		String description = ((TextArea) node).getText();
		return description;
	}
	
	public String getBreed(){
		Node node = getNodeByRowColumnIndex(4, 1, animalGrid);
		@SuppressWarnings("unchecked")
		String breed = (String)((ChoiceBox<String>)node).getValue();
		return breed;
	}
	
	public String getType(){
		Node node = getNodeByRowColumnIndex(5, 1, animalGrid);
		String type = ((TextField) node).getText();
		return type;
	}
	
	public boolean getGender(){
		Node node = getNodeByRowColumnIndex(6, 1, animalGrid);
		HBox buttonBox = (HBox)node;
		RadioButton maleButton = (RadioButton)buttonBox.getChildren().get(0); 
		boolean gender = maleButton.isSelected();
		return gender;
	}
	
	public Calendar getDate(){
		Node node = getNodeByRowColumnIndex(7, 1, animalGrid);
		LocalDate date = ((DatePicker)node).getValue();
		Calendar calendar = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
		return calendar;
	}
	
	public String getCategory(){
		Node node = getNodeByRowColumnIndex(8, 1, animalGrid);
		HBox categroyBox = ((HBox)node);
		RadioButton button = (RadioButton)categroyBox.getChildren().get(0);
		ToggleGroup categoryGroup = button.getToggleGroup();
		button = (RadioButton)categoryGroup.getSelectedToggle();
		String category = button.getText();
		return category;
	}
	
	public String getLocalisation(){
		Node node  = getNodeByRowColumnIndex(0, 1, categoryPane);
		TextField localisationField = (TextField)node;
		String localisation = localisationField.getText();
		return localisation;
	}
	
	public boolean getNeutered(){
		Node node = getNodeByRowColumnIndex(0, 1, categoryPane);
		HBox neuteuredBox = (HBox)node;
		RadioButton neutered = (RadioButton)neuteuredBox.getChildren().get(0);
		boolean isNeutered = neutered.isSelected();
		return isNeutered;
	}
	
	public boolean getChipped(){
		Node node = getNodeByRowColumnIndex(1, 1, categoryPane);
		HBox chippedBox = (HBox)node;
		RadioButton chipped = (RadioButton)chippedBox.getChildren().get(0);
		boolean isChipped = chipped.isSelected();
		return isChipped;
	}
	
	public boolean getVaccinated(){
		Node node = getNodeByRowColumnIndex(2, 1, categoryPane);
		HBox vaccinatedBox = (HBox)node;
		RadioButton vaccinated = (RadioButton)vaccinatedBox.getChildren().get(0);
		boolean isVaccinated = vaccinated.isSelected();
		return isVaccinated;
	}
	
	
	@SuppressWarnings("static-access")
	private Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane){
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
	
	public void setCategory(int i){
		categoryPane.getChildren().clear();
		if (i == 0) {
			Text neuteredText = new Text("Neutered :");
			categoryPane.add(neuteredText, 0, 0);

			ToggleGroup neuteredGroup = new ToggleGroup();
			RadioButton neuteredBox = new RadioButton("Yes");
			neuteredBox.setToggleGroup(neuteredGroup);
			neuteredBox.setSelected(true);
			RadioButton nonNeuteredBox = new RadioButton("No");
			nonNeuteredBox.setToggleGroup(neuteredGroup);
			HBox neutered = new HBox(15);
			neutered.getChildren().addAll(neuteredBox, nonNeuteredBox);
			categoryPane.add(neutered, 1, 0);
			neutered.setAlignment(Pos.CENTER);   

			Text chippedText = new Text("Chipped :");
			categoryPane.add(chippedText, 0, 1);

			ToggleGroup chippedGroup = new ToggleGroup();
			RadioButton chippedBox = new RadioButton("Yes");
			chippedBox.setToggleGroup(chippedGroup);
			chippedBox.setSelected(true);
			RadioButton nonChippedBox = new RadioButton("No");
			nonChippedBox.setToggleGroup(chippedGroup);
			HBox chipped = new HBox(15);
			chipped.getChildren().addAll(chippedBox, nonChippedBox);
			categoryPane.add(chipped, 1, 1);
			chipped.setAlignment(Pos.CENTER);

			Text vaccinatedText = new Text("Vaccinated :");
			categoryPane.add(vaccinatedText, 0, 2);

			ToggleGroup vaccinatedGroup = new ToggleGroup();
			RadioButton vaccinatedBox = new RadioButton("Yes");
			vaccinatedBox.setToggleGroup(vaccinatedGroup);
			vaccinatedBox.setSelected(true);
			RadioButton nonVaccinatedBox = new RadioButton("No");
			nonVaccinatedBox.setToggleGroup(vaccinatedGroup);
			HBox vaccinated = new HBox(15);
			vaccinated.getChildren().addAll(vaccinatedBox, nonVaccinatedBox);
			categoryPane.add(vaccinated, 1, 2);
			vaccinated.setAlignment(Pos.CENTER);   
		}
		else {
			Text locationText = new Text("Location :");
			categoryPane.add(locationText, 0, 0);

			TextField locationField = new TextField();
			locationField.setPromptText("Ex : In the dark forest");
			categoryPane.add(locationField, 1, 0);
		}
	}
}
