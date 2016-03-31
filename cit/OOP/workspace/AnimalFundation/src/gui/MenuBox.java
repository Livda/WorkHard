package gui;

import controlers.ChooseReportHandler;
import controlers.LoadHandler;
import controlers.NewHandler;
import controlers.SaveHandler;
import controlers.SettingsHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

public class MenuBox {
	private MenuBar box;
	
	public MenuBox(){
		Menu fileMenu = new Menu(Messages.getString("file"));
		MenuItem newItem = new MenuItem(Messages.getString("new_file"));
		newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
		newItem.setOnAction(new NewHandler());
		MenuItem saveItem = new MenuItem(Messages.getString("save"));
		saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		saveItem.setOnAction(new SaveHandler());
		MenuItem loadItem = new MenuItem(Messages.getString("load_from_files"));
		loadItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
		loadItem.setOnAction(new LoadHandler());
        MenuItem settingsItem = new MenuItem(Messages.getString("settings"));
        settingsItem.setOnAction(new SettingsHandler());
        MenuItem exitItem = new MenuItem(Messages.getString("exit"));
        exitItem.setAccelerator(KeyCombination.keyCombination("Alt+f4"));
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+W"));
        exitItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().addAll(newItem, saveItem, loadItem, new SeparatorMenuItem(),
        		settingsItem, new SeparatorMenuItem(),
        		exitItem);
        
        Menu editMenu = new Menu(Messages.getString("edit"));
        MenuItem undoItem = new MenuItem(Messages.getString("undo"));
        undoItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Z"));
        undoItem.setDisable(true);
        MenuItem redoItem = new MenuItem(Messages.getString("redo"));
        redoItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Y"));
        redoItem.setDisable(true);
        MenuItem copyItem = new MenuItem(Messages.getString("copy"));
        copyItem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
        MenuItem cutItem = new MenuItem(Messages.getString("cut"));
        cutItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        MenuItem pasteItem = new MenuItem(Messages.getString("paste"));
        pasteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
        editMenu.getItems().addAll(undoItem, redoItem, new SeparatorMenuItem(),
        		copyItem, cutItem, pasteItem);
        
        Menu toolMenu = new Menu(Messages.getString("tools"));
        MenuItem bugItem = new MenuItem(Messages.getString("report_bug"));
        bugItem.setDisable(true);
        MenuItem reportItem = new MenuItem(Messages.getString("generate_report"));
        reportItem.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        reportItem.setOnAction(new ChooseReportHandler());
        toolMenu.getItems().addAll(bugItem, reportItem);
        
        box = new MenuBar();
        box.getMenus().addAll(fileMenu, editMenu, toolMenu);
	}
	
	public MenuBar getBox(){
		return box;
	}

}
