/**
 * 
 */
package gui;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import reportControlers.FoundHandler;
import reportControlers.GeneralHandler;
import reportControlers.LostHandler;
import reportControlers.ReportHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ChooseReportBox {
	private HBox mainBox;
	
	public ChooseReportBox(Stage stage){	
		
		//General reports
		Text allAnimalText = new Text(Messages.getString("general_reports"));
		RadioButton allAnimals = new RadioButton(Messages.getString("all_animals"));
		allAnimals.setSelected(true);
		RadioButton allSponsors = new RadioButton(Messages.getString("all_sponsors"));
		ToggleGroup generalGroup = new ToggleGroup();
		allAnimals.setToggleGroup(generalGroup);
		allSponsors.setToggleGroup(generalGroup);
		
		VBox sortBox = new VBox(10);
		sortBox.getChildren().addAll(allAnimals, allSponsors);
		sortBox.setAlignment(Pos.CENTER);
		
		HBox generalButtonBox = new HBox(10);
		Button generalGenerateButton = new Button(Messages.getString("generate_report"));
		generalGenerateButton.setOnAction(new GeneralHandler(allAnimals, allSponsors, stage));
		Button generalCancelButton = new Button(Messages.getString("cancel"));
		generalCancelButton.setOnAction(e -> stage.close());
		generalButtonBox.getChildren().addAll(generalGenerateButton, generalCancelButton);
		generalButtonBox.setAlignment(Pos.CENTER);
		
		VBox generalBox = new VBox(10);
		generalBox.setAlignment(Pos.TOP_CENTER);
		generalBox.getChildren().addAll(allAnimalText, sortBox, generalButtonBox);
		
		//Adoption report
		Text adoptionText = new Text(Messages.getString("adoption_reports"));
		
		RadioButton adoptionByName = new RadioButton(Messages.getString("ready_adoption_name"));
		adoptionByName.setSelected(true);
		RadioButton adoptionByAge = new RadioButton(Messages.getString("ready_adoption_age"));
		RadioButton adoptionPuppies = new RadioButton(Messages.getString("puppies_training"));
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
		TextField foundLocationField = new TextField();
		foundLocationField.setPromptText(Messages.getString("location"));
		
		DatePicker begin = new DatePicker(LocalDate.now());
		begin.setDisable(true);
		DatePicker end = new DatePicker(LocalDate.now().plusDays(1));
		end.setDisable(true);
		
		FoundHandler foundHandler = new FoundHandler(foundLocationField, begin, end, stage);

		Text foundText = new Text(Messages.getString("found_reports"));
		RadioButton foundLocation = new RadioButton(Messages.getString("in_location"));
		foundLocation.setSelected(true);
		foundLocation.setOnAction(e -> foundHandler.setOnlyLocation());
		RadioButton foundDate = new RadioButton(Messages.getString("between_dates"));
		foundDate.setOnAction(e -> foundHandler.setOnlyBetweenDates());
		RadioButton foundBoth = new RadioButton(Messages.getString("location_and_dates"));
		foundBoth.setOnAction(e -> foundHandler.setLocationAndDates());
		ToggleGroup foundGroup = new ToggleGroup();
		foundLocation.setToggleGroup(foundGroup);
		foundDate.setToggleGroup(foundGroup);
		foundBoth.setToggleGroup(foundGroup);
		
		HBox foundButtonBox = new HBox(10);
		Button foundGenerateButton = new Button(Messages.getString("generate_report"));
		foundGenerateButton.setOnAction(foundHandler);
		Button foundCancelButton = new Button(Messages.getString("cancel"));
		foundCancelButton.setOnAction(e -> stage.close());
		foundButtonBox.getChildren().addAll(foundGenerateButton, foundCancelButton);
		foundButtonBox.setAlignment(Pos.CENTER);
		
		VBox foundBox = new VBox(10);
		foundBox.setAlignment(Pos.TOP_CENTER);
		foundBox.getChildren().addAll(foundText, foundLocation, foundDate, foundBoth, 
				foundLocationField, begin, end, foundButtonBox);
		
		//Lost report
		boolean cat = false;
		Text lostText = new Text(Messages.getString("lost_reports"));
		RadioButton location = new RadioButton(Messages.getString("all_lost_location"));
		location.setSelected(true);
		RadioButton catLocation = new RadioButton(Messages.getString("cats_lost_location"));
		ToggleGroup lostGroup = new ToggleGroup();
		location.setToggleGroup(lostGroup);
		catLocation.setToggleGroup(lostGroup);
		
		TextField lostLocation = new TextField();
		lostLocation.setPromptText(Messages.getString("location"));
		
		HBox lostButtonBox = new HBox(10);
		Button lostGenerateButton = new Button(Messages.getString("generate_report"));
		lostGenerateButton.setOnAction(new LostHandler(stage, lostLocation, cat));
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
