/**
 * 
 */
package reportControlers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
				GregorianCalendar animalCalendar = category.getDate();
				Date animalDate = animalCalendar.getTime();
				Instant instant = Instant.ofEpochMilli(animalDate.getTime());
				LocalDate animalLocalDate = LocalDateTime.ofInstant
						(instant, ZoneId.systemDefault()).toLocalDate();
				LocalDate beginDate = begin.getValue();
				LocalDate endDate = end.getValue();
				boolean isBefore = animalLocalDate.isAfter(beginDate);
				boolean isAfter = animalLocalDate.isBefore(endDate);
				if (isBefore && isAfter) {
					datesList.add(a);
				}
			}
		}
		switch (option){
		case 1:
			new ReportHandler(null, stage).fillAnimalByList(locationList);
			break;
		case 2:
			new ReportHandler(null, stage).fillAnimalByList(datesList);
			break;
		case 3:
			List<Animal> res = new ArrayList<Animal>();
			for(Animal a : locationList){
				if (datesList.contains(a)) res.add(a);
			}
			new ReportHandler(null, stage).fillAnimalByList(res);
			break;
		}
	}
}
