/**
 * 
 */
package reportControlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import api.Animal;
import api.AnimalShelter;
import api.Found;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class FoundHandler implements EventHandler<ActionEvent> {
	private TextField location;
	private DatePicker begin;
	private DatePicker end;
	private Stage stage;
	
	public FoundHandler(TextField location, DatePicker begin, DatePicker end, Stage stage){
		this.location = location;
		this.begin = begin;
		this.end = end;
		this.stage = stage;
	}
	
	public void setOnlyLocation(){
		location.setDisable(false);
		begin.setDisable(true);
		end.setDisable(true);
	}
	
	public void setOnlyBetweenDates(){
		location.setDisable(true);
		begin.setDisable(false);
		end.setDisable(false);
	}
	
	public void setLocationAndDates(){
		location.setDisable(false);
		begin.setDisable(false);
		end.setDisable(false);
	}

	public void handle(ActionEvent event){
		AnimalShelter shelter = MainWindow.shelter;
		List<Animal> list = shelter.getFound().getList();
		List<Animal> locationList = new ArrayList<Animal>();
		List<Animal> datesList = new ArrayList<Animal>();
		int option = 0;
		if (!location.isDisable()) option += 1;
		if (!begin.isDisable()) option +=2;
		for (Animal a : list){
			Found category = (Found) a.getAnimalCategory();
			if (!location.isDisable()){
				String searchLocation = location.getText();
				String animalLocation = category.getLocation();
				if (searchLocation.equals(animalLocation)){
					locationList.add(a);
				}
			}
			if (!begin.isDisable()){
				LocalDate animalDate = category.getDate();
				LocalDate beginDate = begin.getValue();
				LocalDate endDate = end.getValue();
				boolean isBefore = animalDate.isAfter(beginDate);
				boolean isAfter = animalDate.isBefore(endDate);
				if (isBefore && isAfter) {
					datesList.add(a);
				}
			}
		}
		switch (option){
		case 1:
			new ReportHandler(stage).fillAnimalByList(locationList);
			break;
		case 2:
			new ReportHandler(stage).fillAnimalByList(datesList);
			break;
		case 3:
			List<Animal> res = new ArrayList<Animal>();
			for(Animal a : locationList){
				if (datesList.contains(a)) res.add(a);
			}
			new ReportHandler(stage).fillAnimalByList(res);
			break;
		}
	}
}
