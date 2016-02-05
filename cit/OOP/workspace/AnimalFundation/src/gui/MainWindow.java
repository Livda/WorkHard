package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Animal Shelter");
			
			BorderPane mainLayout = new BorderPane();
			Scene scene = new Scene(mainLayout,400,400);
			
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
	        
	        secondLayout.setTop(mainButtons);
	        mainLayout.setCenter(secondLayout);
	        
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
