package gui;

import api.Animal;
import controlers.MainControler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			MenuBar menuBar = new MenuBox().getBox();
	        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());	        
	        
	        Button adoption = new Button("Adoption");
	        Button found = new Button("Found");
	        Button lost = new Button("Lost");
	        HBox mainButtons = new HBox();
	        mainButtons.setAlignment(Pos.TOP_CENTER);
	        mainButtons.getChildren().addAll(adoption, found, lost);
	        
	        VBox personBox = new PersonBox().getBox();   
	        VBox animalBox = new AnimalBox(1).getBox();
       
	        HBox bottomButtons = new HBox(10);
	        Button ok = new Button("OK");
	        Button cancel = new Button("Cancel");
	        bottomButtons.getChildren().addAll(ok, cancel);
	        bottomButtons.setAlignment(Pos.BOTTOM_CENTER);
	        
	        HBox mainBox = new HBox(20);
	        mainBox.getChildren().addAll(personBox, animalBox);
	        
	        TableView<Animal> tableView = new TableViewBox().getBox();
	        Button editAnimal = new Button("Edit");
	        Button deleteAnimal = new Button("Delete");
	        HBox buttonBox = new HBox(15);
	        buttonBox.getChildren().addAll(editAnimal, deleteAnimal);
	        buttonBox.setAlignment(Pos.CENTER);
	        VBox tableBox = new VBox(10);
	        tableBox.getChildren().addAll(tableView, buttonBox);
	        
	        BorderPane secondLayout = new BorderPane();
	        secondLayout.setTop(mainButtons);
	        BorderPane.setMargin(mainButtons, new Insets(10));
	        secondLayout.setLeft(tableBox);
	        BorderPane.setMargin(tableBox, new Insets(10));
	        secondLayout.setCenter(mainBox);
	        mainBox.setAlignment(Pos.CENTER);
	        BorderPane.setMargin(mainBox, new Insets(10));
	        secondLayout.setBottom(bottomButtons);
	        BorderPane.setMargin(bottomButtons, new Insets(10));

	        BorderPane mainLayout = new BorderPane();
	        mainLayout.setTop(menuBar);
	        mainLayout.setCenter(secondLayout);
	        
	        Scene scene = new Scene(mainLayout,1000,700);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Animal Shelter");
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
