package gui;

import controlers.MainControler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Animal Shelter");
			
			BorderPane mainLayout = new BorderPane();
			Scene scene = new Scene(mainLayout,800,600);
			
			Menu fileMenu = new Menu("File");
			MenuItem newItem = new MenuItem("New ...");
			newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
			MenuItem saveItem = new MenuItem("Save");
			saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
	        MenuItem settingsItem = new MenuItem("Settings");
	        MenuItem exitItem = new MenuItem("Exit");
	        exitItem.setAccelerator(KeyCombination.keyCombination("Alt+f4"));
	        exitItem.setOnAction(e -> System.exit(0));
	        fileMenu.getItems().addAll(newItem, saveItem, new SeparatorMenuItem(),
	        		settingsItem, new SeparatorMenuItem(),
	        		exitItem);
	        
	        Menu editMenu = new Menu("Edit");
	        MenuItem undoItem = new MenuItem("Undo");
	        undoItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
	        MenuItem redoItem = new MenuItem("Redo");
	        redoItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Y"));
	        MenuItem copyItem = new MenuItem("Copy");
	        copyItem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
	        MenuItem cutItem = new MenuItem("Cut");
	        cutItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
	        MenuItem pasteItem = new MenuItem("Paste");
	        pasteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
	        editMenu.getItems().addAll(undoItem, redoItem, new SeparatorMenuItem(),
	        		copyItem, cutItem, pasteItem);
	        
	        Menu helpMenu = new Menu("Help");
	        helpMenu.getItems().add(new MenuItem("There is no help here"));
	        
	        MenuBar menuBar = new MenuBar();
	        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
	        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
	        
	        mainLayout.setTop(menuBar);
	        
	        BorderPane secondLayout = new BorderPane();
	        
	        Button adoption = new Button("Adoption");
	        Button lost = new Button("Lost");
	        Button found = new Button("Found");
	        
	        HBox mainButtons = new HBox();
	        mainButtons.setAlignment(Pos.TOP_CENTER);
	        mainButtons.getChildren().addAll(adoption, lost, found);
	        
	        GridPane personGrid = new GridPane();
	        personGrid.setVgap(10);
	        personGrid.setHgap(10);
	        Text personName = new Text("Name :");
	        TextField setPersonName = new TextField();
	        Text personAdress = new Text("Adress :");
	        TextArea setPersonAdress = new TextArea();
	        setPersonAdress.setMaxSize(300, 60);
	        Text personPhone = new Text("Phone :");
	        TextField setPersonPhone = new TextField();
	        Text personEmail = new Text("Email :");
	        TextField setPersonEmail = new TextField();
	        
	        personGrid.add(personName, 0, 0);
	        personGrid.add(setPersonName, 1, 0);
	        personGrid.add(personAdress, 0, 1);
	        personGrid.add(setPersonAdress, 1, 1);
	        personGrid.add(personPhone, 0, 2);
	        personGrid.add(setPersonPhone, 1, 2);
	        personGrid.add(personEmail, 0, 3);
	        personGrid.add(setPersonEmail, 1, 3);
	        
	        VBox personBox = new VBox(20);
	        Text personTitle = new Text("Person");
	        personTitle.setFont(Font.font(null, FontWeight.BOLD, 25));
	        personBox.getChildren().addAll(personTitle, personGrid);
	        personBox.setAlignment(Pos.TOP_CENTER);
	        
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
	        
	        VBox animalBox = new VBox(20);
	        Text animalTitle = new Text("Animal");
	        animalTitle.setFont(Font.font(null, FontWeight.BOLD, 25));
	        animalBox.getChildren().addAll(animalTitle, animalGrid);
	        animalBox.setAlignment(Pos.TOP_CENTER);
	        
	        HBox bottomButtons = new HBox(10);
	        Button ok = new Button("OK");
	        Button cancel = new Button("Cancel");
	        bottomButtons.getChildren().addAll(ok, cancel);
	        bottomButtons.setAlignment(Pos.BOTTOM_CENTER);
	        
	        secondLayout.setTop(mainButtons);
	        BorderPane.setMargin(mainButtons, new Insets(10));
	        secondLayout.setLeft(personBox);
	        BorderPane.setMargin(personBox, new Insets(10));
	        secondLayout.setRight(animalBox);
	        BorderPane.setMargin(animalBox, new Insets(10));
	        secondLayout.setBottom(bottomButtons);
	        BorderPane.setMargin(bottomButtons, new Insets(10));
	        mainLayout.setCenter(secondLayout);
	        
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		MainControler main = new MainControler();
		main.start();
		launch(args);
	}
}
