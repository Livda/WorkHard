/**
 * 
 */
package gui;

import controlers.ReportHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ChooseReportBox {
	private HBox mainBox;
	
	public ChooseReportBox(Stage stage){	
		
		//General reports
		Text allAnimalText = new Text("General reports");
		RadioButton allAnimals = new RadioButton("All the animals");
		allAnimals.setSelected(true);
		RadioButton allSponsors = new RadioButton("All the sponsors");
		ToggleGroup generalGroup = new ToggleGroup();
		allAnimals.setToggleGroup(generalGroup);
		allSponsors.setToggleGroup(generalGroup);
		
		VBox sortBox = new VBox(10);
		sortBox.getChildren().addAll(allAnimals, allSponsors);
		sortBox.setAlignment(Pos.CENTER);
		
		HBox generalButtonBox = new HBox(10);
		Button generalGenerateButton = new Button(Messages.getString("generate_report"));
		Button generalCancelButton = new Button(Messages.getString("cancel"));
		generalCancelButton.setOnAction(e -> stage.close());
		generalButtonBox.getChildren().addAll(generalGenerateButton, generalCancelButton);
		generalButtonBox.setAlignment(Pos.CENTER);
		
		VBox generalBox = new VBox(10);
		generalBox.setAlignment(Pos.TOP_CENTER);
		generalBox.getChildren().addAll(allAnimalText, sortBox, generalButtonBox);
		generalGenerateButton.setOnAction(new ReportHandler(sortBox, stage));
		
		//Adoption report
		Text adoptionText = new Text("Adoption reports");
		
		RadioButton adoptionByName = new RadioButton("Ready for adoption sort by name");
		adoptionByName.setSelected(true);
		RadioButton adoptionByAge = new RadioButton("Ready for adoption sort by age");
		RadioButton adoptionPuppies = new RadioButton("Puppies in training");
		ToggleGroup adoptionGroup = new ToggleGroup();
		adoptionByName.setToggleGroup(adoptionGroup);
		adoptionByAge.setToggleGroup(adoptionGroup);
		adoptionPuppies.setToggleGroup(adoptionGroup);
		
		HBox adoptionButtonBox = new HBox(10);
		Button adoptionGenerateButton = new Button(Messages.getString("generate_report"));
		Button adoptionCancelButton = new Button(Messages.getString("cancel"));
		adoptionCancelButton.setOnAction(e -> stage.close());
		adoptionButtonBox.getChildren().addAll(adoptionGenerateButton, adoptionCancelButton);
		adoptionButtonBox.setAlignment(Pos.CENTER);
		
		VBox adoptionBox = new VBox(10);
		adoptionBox.setAlignment(Pos.TOP_CENTER);
		adoptionBox.getChildren().addAll(adoptionText, adoptionByName, adoptionByAge, 
				adoptionPuppies, adoptionButtonBox);
		
		//Found report
		Text foundText = new Text("Found reports");
		RadioButton foundLocation = new RadioButton("In a certain location");
		foundLocation.setSelected(true);
		RadioButton foundDate = new RadioButton("Between certain dates");
		RadioButton foundBoth = new RadioButton("In a certain location + between certain "
				+ "dates");
		ToggleGroup foundGroup = new ToggleGroup();
		foundLocation.setToggleGroup(foundGroup);
		foundDate.setToggleGroup(foundGroup);
		foundBoth.setToggleGroup(foundGroup);
		
		TextField foundLocationField = new TextField();
		foundLocationField.setPromptText(Messages.getString("location"));
		
		HBox foundButtonBox = new HBox(10);
		Button foundGenerateButton = new Button(Messages.getString("generate_report"));
		Button foundCancelButton = new Button(Messages.getString("cancel"));
		foundCancelButton.setOnAction(e -> stage.close());
		foundButtonBox.getChildren().addAll(foundGenerateButton, foundCancelButton);
		foundButtonBox.setAlignment(Pos.CENTER);
		
		VBox foundBox = new VBox(10);
		foundBox.setAlignment(Pos.TOP_CENTER);
		foundBox.getChildren().addAll(foundText, foundLocation, foundLocationField,
				foundDate, foundBoth, foundButtonBox);
		
		//Lost report
		Text lostText = new Text("Lost reports");
		RadioButton location = new RadioButton("All animals lost in a certain location");
		location.setSelected(true);
		RadioButton catLocation = new RadioButton("Cats lost in a certain location");
		ToggleGroup lostGroup = new ToggleGroup();
		location.setToggleGroup(lostGroup);
		catLocation.setToggleGroup(lostGroup);
		
		TextField lostLocation = new TextField();
		lostLocation.setPromptText(Messages.getString("location"));
		
		HBox lostButtonBox = new HBox(10);
		Button lostGenerateButton = new Button(Messages.getString("generate_report"));
		Button lostCancelButton = new Button(Messages.getString("cancel"));
		lostCancelButton.setOnAction(e -> stage.close());
		lostButtonBox.getChildren().addAll(lostGenerateButton, lostCancelButton);
		lostButtonBox.setAlignment(Pos.CENTER);
		VBox lostBox = new VBox(10);
		lostBox.getChildren().addAll(lostText, location, catLocation, lostLocation, 
				lostButtonBox);
		lostBox.setAlignment(Pos.TOP_CENTER);

		//Manage general box
		this.mainBox = new HBox(0);
		mainBox.getChildren().addAll(generalBox, adoptionBox, foundBox, lostBox);
		HBox.setMargin(foundBox, new Insets(10));
		HBox.setMargin(lostBox, new Insets(10));
		HBox.setMargin(generalBox, new Insets(10));
		HBox.setMargin(adoptionBox, new Insets(10));
	}
	
	public HBox getMainBox(){
		return this.mainBox;
	}
}
