package gui;

import api.AnimalShelter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainWindow extends Application {
	public static AnimalShelter shelter;
	public static TableViewBox table;
	private VBox mainBox;

	@Override
	public void start(Stage primaryStage) {
		try {
			shelter = new AnimalShelter();
			mainBox = new VBox(10);
			
			table = new TableViewBox(this);
	        VBox tableBox = table.getBox();
	        
			MenuBar menuBar = new MenuBox(this).getBox();
	        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
	        
	        BorderPane secondLayout = new BorderPane();
	        secondLayout.setCenter(mainBox);
	        mainBox.setAlignment(Pos.TOP_CENTER);
	        BorderPane.setMargin(mainBox, new Insets(10));

	        BorderPane mainLayout = new BorderPane();
	        mainLayout.setLeft(tableBox);
	        BorderPane.setAlignment(tableBox, Pos.CENTER_LEFT);
	        BorderPane.setMargin(tableBox, new Insets(10));
	        mainLayout.setTop(menuBar);
	        mainLayout.setCenter(secondLayout);
	        
	        Scene scene = new Scene(mainLayout,1000,550);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Animal Shelter");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void setMainBox(VBox box){
		mainBox = box;
	}
	
	public VBox getMainBox(){
		return mainBox;
	}
}
