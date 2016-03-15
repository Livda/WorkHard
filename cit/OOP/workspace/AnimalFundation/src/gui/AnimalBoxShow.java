/**
 * 
 */
package gui;

import java.text.DateFormat;
import java.util.Calendar;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class AnimalBoxShow extends AnimalBox {
	private GridPane animalGrid;
	private GridPane categoryGrid;

	public AnimalBoxShow(){
		this.animalGrid = getAnimalGrid();
		this.categoryGrid = getCategoryGrid();

		Text nameText = new Text();
		animalGrid.add(nameText, 1, 0);

		Text ageText = new Text();
		animalGrid.add(ageText, 1, 1);

		Text colorText = new Text();
		animalGrid.add(colorText, 1, 2);

		Text descriptionText = new Text();
		animalGrid.add(descriptionText, 1, 3);

		Text breedText = new Text();
		animalGrid.add(breedText, 1, 4);

		Text typeText = new Text();
		animalGrid.add(typeText, 1, 5);

		Text genderText = new Text();
		animalGrid.add(genderText, 1, 6);

		Text dateText = new Text();
		animalGrid.add(dateText, 1, 7);

		Text categoryText = new Text();
		animalGrid.add(categoryText, 1, 8);
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setCategoryGrid(int)
	 */
	@Override
	public void setCategoryGrid(int i) {
		categoryGrid.getChildren().clear();
		if (i == 0) {
			Text neuteredText = new Text("Neutered :");
			categoryGrid.add(neuteredText, 0, 0);

			Text neuteredField = new Text();
			categoryGrid.add(neuteredField, 1, 0);

			Text chippedText = new Text("Chipped :");
			categoryGrid.add(chippedText, 0, 1);

			Text chippedField = new Text();
			categoryGrid.add(chippedField, 1, 1);

			Text vaccinatedText = new Text("Vaccinated :");
			categoryGrid.add(vaccinatedText, 0, 2);

			Text vaccinatedField = new Text();
			categoryGrid.add(vaccinatedField, 1, 2);
		} else {
			Text locationText = new Text("Location :");
			categoryGrid.add(locationText, 0, 0);

			Text locationField = new Text();
			categoryGrid.add(locationField, 1, 0);
		}
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		Node node = getNodeByRowColumnIndex(0, 1, animalGrid);
		Text nameText = (Text) node; 
		nameText.setText(name);
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setAge(int)
	 */
	@Override
	public void setAge(int age) {
		Node node = getNodeByRowColumnIndex(1, 1, animalGrid);
		Text ageText = (Text) node;
		ageText.setText(String.valueOf(age));
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setColour(java.lang.String)
	 */
	@Override
	public void setColour(String colour) {
		Node node = getNodeByRowColumnIndex(2, 1, animalGrid);
		Text colourText = (Text) node; 
		colourText.setText(colour);

	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		Node node = getNodeByRowColumnIndex(3, 1, animalGrid);
		Text descriptionText = (Text) node;
		descriptionText.setWrappingWidth(250);
		descriptionText.setText(description);
		
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setBreed(java.lang.String)
	 */
	@Override
	public void setBreed(String breed) {
		Node node = getNodeByRowColumnIndex(4, 1, animalGrid);
		Text breedText = (Text) node;
		breedText.setText(breed);
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setType(java.lang.String)
	 */
	@Override
	public void setType(String type) {
		Node node = getNodeByRowColumnIndex(5, 1, animalGrid);
		Text typeText = (Text) node;
		typeText.setText(type);
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setGender(boolean)
	 */
	@Override
	public void setGender(boolean gender) {
		Node node = getNodeByRowColumnIndex(6, 1, animalGrid);
		Text genderText = (Text) node;
		String genderString = gender ? "Male" : "Female";
		genderText.setText(genderString);
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setDate(java.util.Calendar)
	 */
	@Override
	public void setDate(Calendar date) {
		Node node = getNodeByRowColumnIndex(7, 1, animalGrid);
		Text dateText = (Text) node;
		DateFormat shortDateFormat = DateFormat.getDateTimeInstance
				(DateFormat.SHORT, DateFormat.SHORT, MainWindow.locale);
		dateText.setText(shortDateFormat.format(date.getTime()));
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setCategory(java.lang.String)
	 */
	@Override
	public void setCategory(String category) {
		Node node = getNodeByRowColumnIndex(8, 1, animalGrid);
		Text categoryText = (Text) node;
		categoryText.setText(category);
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setLocalisation(java.lang.String)
	 */
	@Override
	public void setLocalisation(String localisation) {
		Node node = getNodeByRowColumnIndex(0, 1, categoryGrid);
		Text localisationText = (Text) node;
		localisationText.setText(localisation);

	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setNeutered(boolean)
	 */
	@Override
	public void setNeutered(boolean neutered) {
		Node node = getNodeByRowColumnIndex(0, 1, categoryGrid);
		Text neuteredText = (Text) node;
		String text = neutered ? "Yes" : "No";
		neuteredText.setText(text);
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setChipped(boolean)
	 */
	@Override
	public void setChipped(boolean chipped) {
		Node node = getNodeByRowColumnIndex(1, 1, categoryGrid);
		Text chippedText = (Text) node;
		String text = chipped ? "Yes" : "No";
		chippedText.setText(text);
	}

	/* (non-Javadoc)
	 * @see gui.AnimalBox#setVaccinated(boolean)
	 */
	@Override
	public void setVaccinated(boolean vaccinated) {
		Node node = getNodeByRowColumnIndex(2, 1, categoryGrid);
		Text vaccinatedText = (Text) node;
		String text = vaccinated ? "Yes" : "No";
		vaccinatedText.setText(text);
	}

}
