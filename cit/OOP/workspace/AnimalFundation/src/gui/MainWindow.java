package gui;

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
			primaryStage.setTitle("Animal Shelter");
			
			BorderPane mainLayout = new BorderPane();
			Scene scene = new Scene(mainLayout,800,600);
			
			MenuBar menuBar = new MenuBox().getBox();
	        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());	        
	        mainLayout.setTop(menuBar);
	        
	        Button adoption = new Button("Adoption");
	        Button lost = new Button("Lost");
	        Button found = new Button("Found");
	        
	        HBox mainButtons = new HBox();
	        mainButtons.setAlignment(Pos.TOP_CENTER);
	        mainButtons.getChildren().addAll(adoption, lost, found);
	        
	        VBox personBox = new PersonBox().getBox();   
	        VBox animalBox = new AnimalBox().getBox();
	        
	        HBox bottomButtons = new HBox(10);
	        Button ok = new Button("OK");
	        Button cancel = new Button("Cancel");
	        bottomButtons.getChildren().addAll(ok, cancel);
	        bottomButtons.setAlignment(Pos.BOTTOM_CENTER);
	        
	        BorderPane secondLayout = new BorderPane();
	        secondLayout.setTop(mainButtons);
	        BorderPane.setMargin(mainButtons, new Insets(10));
	        secondLayout.setLeft(personBox);
	        //BorderPane.setMargin(personBox, new Insets(10));
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
