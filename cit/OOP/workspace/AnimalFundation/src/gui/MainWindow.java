package gui;

import java.util.Locale;

import api.AnimalShelter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
	public static Locale locale;
	public static AnimalShelter shelter;
	public static TableViewBox table;
	public static VBox mainBox;

	@Override
	public void start(Stage primaryStage) {
		try {
			Locale.setDefault(new Locale("en"));
			shelter = new AnimalShelter();
			mainBox = new VBox(10);	
			table = new TableViewBox();
			
			Text nothingHere = new Text(Messages.getString("select_create_animal")); //$NON-NLS-1$
			mainBox.getChildren().add(nothingHere);
	        VBox tableBox = table.getBox();
	        
			MenuBar menuBar = new MenuBox().getBox();
	        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
	        
	        BorderPane secondLayout = new BorderPane();
	        secondLayout.setCenter(mainBox);
	        mainBox.setAlignment(Pos.CENTER);
	        BorderPane.setMargin(mainBox, new Insets(10));

	        BorderPane mainLayout = new BorderPane();
	        mainLayout.setLeft(tableBox);
	        BorderPane.setAlignment(tableBox, Pos.CENTER_LEFT);
	        BorderPane.setMargin(tableBox, new Insets(10));
	        mainLayout.setTop(menuBar);
	        mainLayout.setCenter(secondLayout);
	        
	        Scene scene = new Scene(mainLayout,1000,600);
	        primaryStage.setScene(scene);
	        scene.getStylesheets().add("file:ressources/css/textField.css"); //$NON-NLS-1$
			primaryStage.setTitle(Messages.getString("animal_shelter")); //$NON-NLS-1$
			primaryStage.getIcons().add(new Image("file:ressources/images/icon.png")); //$NON-NLS-1$
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
