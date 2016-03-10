/**
 * 
 */
package gui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import controlers.ChangeCategoryHandler;
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
import javafx.scene.text.Text;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class AnimalBoxEditable extends AnimalBox {
	private GridPane animalGrid;
	private GridPane categoryGrid;
	
	public AnimalBoxEditable(){
		super();
		this.animalGrid = this.getAnimalGrid();
		this.categoryGrid = this.getCategoryGrid();
		
		TextField setAnimalName = new TextField();
		setAnimalName.setPromptText("Ex : Leonard");
		animalGrid.add(setAnimalName, 1, 0);
		
		TextField ageNumber = new TextField();
		ageNumber.setPromptText("Ex : 2");
		animalGrid.add(ageNumber, 1, 1);
		
		TextField setAnimalColor = new TextField();
		setAnimalColor.setPromptText("Ex : Black & White");
		animalGrid.add(setAnimalColor, 1, 2);
		
		TextArea description = new TextArea();
		description.setMaxSize(300, 50);
		description.setPromptText("Likes to be pet");
		animalGrid.add(description, 1, 3);
		
		ChoiceBox<String> setAnimalBreed = new ChoiceBox<String>();
		setAnimalBreed.getItems().addAll("Dog", "Cat");
		setAnimalBreed.getSelectionModel().selectFirst();
		animalGrid.add(setAnimalBreed, 1, 4);
		
		TextField setAnimalType = new TextField();
		setAnimalType.setPromptText("Ex : Shiba Inu");
		animalGrid.add(setAnimalType, 1, 5);
		
		ToggleGroup genderGroup = new ToggleGroup();
		RadioButton maleBox = new RadioButton("Male");
		maleBox.setToggleGroup(genderGroup);
		maleBox.setSelected(true);
		RadioButton femaleBox = new RadioButton("Female");
		femaleBox.setToggleGroup(genderGroup);
		HBox gender = new HBox(15);
		gender.getChildren().addAll(maleBox, femaleBox);
		animalGrid.add(gender, 1, 6);
		gender.setAlignment(Pos.CENTER);
		
		DatePicker datePicker = new DatePicker(LocalDate.now());
		animalGrid.add(datePicker, 1, 7);
				
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
		this.setCategoryGrid(0);
	}
	
	public String getName(){
		Node node = getNodeByRowColumnIndex(0, 1, animalGrid);
		TextField nameField = (TextField) node; 
		this.testEmptyField(nameField);
		String name = nameField.getText();
		return name;
	}
	
	public void setName(String name){
		Node node = getNodeByRowColumnIndex(0, 1, animalGrid);
		TextField nameField = (TextField) node; 
		nameField.setText(name);
	}
	
	public int getAge(){
		Node node = getNodeByRowColumnIndex(1, 1, animalGrid);
		TextField ageField = (TextField) node;
		boolean error = this.testEmptyField(ageField);
		int age = error ? -1 : Integer.parseInt(ageField.getText());
		return age;
	}
	
	public void setAge(int age){
		Node node = getNodeByRowColumnIndex(1, 1, animalGrid);
		TextField ageField = (TextField) node;
		ageField.setText(String.valueOf(age));
	}
	
	public String getColour(){
		Node node = getNodeByRowColumnIndex(2, 1, animalGrid);
		TextField colourField = (TextField) node;
		this.testEmptyField(colourField);
		String colour = colourField.getText();
		return colour;
	}
	
	public void setColour(String colour){
		Node node = getNodeByRowColumnIndex(2, 1, animalGrid);
		TextField colourField = (TextField) node;
		colourField.setText(colour);
	}

	public String getDescrition(){
		Node node = getNodeByRowColumnIndex(3, 1, animalGrid);
		TextArea descriptionArea = (TextArea) node;
		this.testEmptyArea(descriptionArea);
		String description = descriptionArea.getText();
		return description;
	}
	
	public void setDescription(String description){
		Node node = getNodeByRowColumnIndex(3, 1, animalGrid);
		TextArea descriptionArea = (TextArea) node;
		descriptionArea.setText(description);
	}

	public String getBreed(){
		Node node = getNodeByRowColumnIndex(4, 1, animalGrid);
		@SuppressWarnings("unchecked")
		String breed = (String)((ChoiceBox<String>)node).getValue();
		return breed;
	}
	
	public void setBreed(String breed){
		Node node = getNodeByRowColumnIndex(4, 1, animalGrid);
		@SuppressWarnings("unchecked")
		ChoiceBox<String> breedChoice = (ChoiceBox<String>) node;
		breedChoice.getSelectionModel().select(breed);
	}

	public String getType(){
		Node node = getNodeByRowColumnIndex(5, 1, animalGrid);
		TextField typeField = (TextField) node;
		this.testEmptyField(typeField);
		String type = typeField.getText();
		return type;
	}
	
	public void setType(String type) {
		Node node = getNodeByRowColumnIndex(5, 1, animalGrid);
		TextField typeField = (TextField) node;
		typeField.setText(type);
	}

	public boolean getGender(){
		Node node = getNodeByRowColumnIndex(6, 1, animalGrid);
		HBox buttonBox = (HBox)node;
		RadioButton maleButton = (RadioButton)buttonBox.getChildren().get(0); 
		boolean gender = maleButton.isSelected();
		return gender;
	}
	
	public void setGender(boolean gender) {
		Node node = getNodeByRowColumnIndex(6, 1, animalGrid);
		HBox buttonBox = (HBox)node;
		int i = gender ? 0 : 1;
		RadioButton genderButton = (RadioButton)buttonBox.getChildren().get(i);
		genderButton.setSelected(gender);
	}

	public Calendar getDate(){
		Node node = getNodeByRowColumnIndex(7, 1, animalGrid);
		LocalDate date = ((DatePicker)node).getValue();
		Calendar calendar = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
		return calendar;
	}
	
	public void setDate(Calendar date){
		
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
	
	public void setCategory(String category){
		Node node = getNodeByRowColumnIndex(8, 1, animalGrid);
		HBox categroyBox = ((HBox)node);
		int i = -1;
		switch (category) {
		case "Adoption" :
			i = 0;
			break;
		case "Found" :
			i = 1;
			break;
		case "Lost" :
			i = 2;
			break;
		}
		RadioButton button = (RadioButton)categroyBox.getChildren().get(i);
		button.setSelected(true);
	}

	public String getLocalisation(){
		Node node  = getNodeByRowColumnIndex(0, 1, categoryGrid);
		TextField localisationField = (TextField) node;
		this.testEmptyField(localisationField);
		String localisation = localisationField.getText();
		return localisation;
	}
	
	public void setLocalisation(String localisation){
		Node node  = getNodeByRowColumnIndex(0, 1, categoryGrid);
		TextField localisationField = (TextField) node;
		localisationField.setText(localisation);
	}

	public boolean getNeutered(){
		Node node = getNodeByRowColumnIndex(0, 1, categoryGrid);
		HBox neuteuredBox = (HBox)node;
		RadioButton neutered = (RadioButton)neuteuredBox.getChildren().get(0);
		boolean isNeutered = neutered.isSelected();
		return isNeutered;
	}
	
	public void setNeutered(boolean neutered){
		Node node = getNodeByRowColumnIndex(0, 1, categoryGrid);
		HBox neuteuredBox = (HBox)node;
		int i = neutered ? 0 : 1;
		RadioButton neuteredButton = (RadioButton)neuteuredBox.getChildren().get(i);
		neuteredButton.setSelected(true);
	}

	public boolean getChipped(){
		Node node = getNodeByRowColumnIndex(1, 1, categoryGrid);
		HBox chippedBox = (HBox)node;
		RadioButton chipped = (RadioButton)chippedBox.getChildren().get(0);
		boolean isChipped = chipped.isSelected();
		return isChipped;
	}
	
	public void setChipped(boolean chipped){
		Node node = getNodeByRowColumnIndex(1, 1, categoryGrid);
		HBox chippedBox = (HBox)node;
		int i = chipped ? 0 : 1;
		RadioButton chippedButton = (RadioButton)chippedBox.getChildren().get(i);
		chippedButton.setSelected(true);
	}

	public boolean getVaccinated(){
		Node node = getNodeByRowColumnIndex(2, 1, categoryGrid);
		HBox vaccinatedBox = (HBox)node;
		RadioButton vaccinated = (RadioButton)vaccinatedBox.getChildren().get(0);
		boolean isVaccinated = vaccinated.isSelected();
		return isVaccinated;
	}
	
	public void setVaccinated(boolean vaccinated){
		Node node = getNodeByRowColumnIndex(2, 1, categoryGrid);
		HBox vaccinatedBox = (HBox)node;
		int i = vaccinated ? 0 : 1;
		RadioButton vaccinatedButton = (RadioButton)vaccinatedBox.getChildren().get(i);
		vaccinatedButton.setSelected(true);
	}
	
	public void setCategoryGrid(int i){
		categoryGrid.getChildren().clear();
		if (i == 0) {
			Text neuteredText = new Text("Neutered :");
			categoryGrid.add(neuteredText, 0, 0);

			ToggleGroup neuteredGroup = new ToggleGroup();
			RadioButton neuteredBox = new RadioButton("Yes");
			neuteredBox.setToggleGroup(neuteredGroup);
			neuteredBox.setSelected(true);
			RadioButton nonNeuteredBox = new RadioButton("No");
			nonNeuteredBox.setToggleGroup(neuteredGroup);
			HBox neutered = new HBox(15);
			neutered.getChildren().addAll(neuteredBox, nonNeuteredBox);
			categoryGrid.add(neutered, 1, 0);
			neutered.setAlignment(Pos.CENTER);   

			Text chippedText = new Text("Chipped :");
			categoryGrid.add(chippedText, 0, 1);

			ToggleGroup chippedGroup = new ToggleGroup();
			RadioButton chippedBox = new RadioButton("Yes");
			chippedBox.setToggleGroup(chippedGroup);
			chippedBox.setSelected(true);
			RadioButton nonChippedBox = new RadioButton("No");
			nonChippedBox.setToggleGroup(chippedGroup);
			HBox chipped = new HBox(15);
			chipped.getChildren().addAll(chippedBox, nonChippedBox);
			categoryGrid.add(chipped, 1, 1);
			chipped.setAlignment(Pos.CENTER);

			Text vaccinatedText = new Text("Vaccinated :");
			categoryGrid.add(vaccinatedText, 0, 2);

			ToggleGroup vaccinatedGroup = new ToggleGroup();
			RadioButton vaccinatedBox = new RadioButton("Yes");
			vaccinatedBox.setToggleGroup(vaccinatedGroup);
			vaccinatedBox.setSelected(true);
			RadioButton nonVaccinatedBox = new RadioButton("No");
			nonVaccinatedBox.setToggleGroup(vaccinatedGroup);
			HBox vaccinated = new HBox(15);
			vaccinated.getChildren().addAll(vaccinatedBox, nonVaccinatedBox);
			categoryGrid.add(vaccinated, 1, 2);
			vaccinated.setAlignment(Pos.CENTER);   
		} else {
			Text locationText = new Text("Location :");
			categoryGrid.add(locationText, 0, 0);

			TextField locationField = new TextField();
			locationField.setPromptText("Ex : In the dark forest");
			categoryGrid.add(locationField, 1, 0);
		}
	}
}
