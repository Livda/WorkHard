/**
 * 
 */
package controlers;

import java.util.GregorianCalendar;
import api.Adoption;
import api.Animal;
import api.AnimalShelter;
import api.Category;
import api.Found;
import api.Lost;
import api.Person;
import gui.AnimalBox;
import gui.MainWindow;
import gui.PersonBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class CreateAnimalHandler implements EventHandler<ActionEvent>{
	private AnimalBox animalBox;
	private PersonBox personBox;
	
	public CreateAnimalHandler(AnimalBox animalBox, PersonBox personBox){
		this.animalBox = animalBox;
		this.personBox = personBox;
	}
	
	public void handle(ActionEvent event){
		AnimalShelter shelter = MainWindow.shelter;
		
		//We create a person
		String pName = personBox.getName();
		String adress = personBox.getAdress();
		String phone = personBox.getTelephone();
		String email = personBox.getEmail();
		Person p = new Person(pName, adress, phone, email);
		
		//We create a Category
		Category newCategory = null;
		GregorianCalendar date = (GregorianCalendar) animalBox.getDate();
		String category = animalBox.getCategory();
		switch (category) {
		case "Adoption" :
			boolean neutered = animalBox.getNeutered();
			boolean chipped = animalBox.getChipped();
			boolean vaccinated = animalBox.getNeutered();
			newCategory = new Adoption(date, p, neutered, chipped, vaccinated);
			break;
		case "Found" :
			String fLocal = animalBox.getLocalisation();
			newCategory = new Found(date, p, fLocal);
			break;
		case "Lost" :
			String lLocal = animalBox.getLocalisation();
			newCategory = new Lost(date, p, lLocal);
			break;
		}
		
		//We create an Animal
		String aName = animalBox.getName();
		int age = animalBox.getAge();
		String color = animalBox.getColour();
		String description = animalBox.getDescrition();
		String breed = animalBox.getBreed();
		String type = animalBox.getType();
		boolean gender = animalBox.getGender();
		
		Animal a = new Animal(age, color, gender, description, aName, null, 
				breed, newCategory, type);
		
		//Add the animal to the shelter
		shelter.add(a);
		
		//we add the new animal to the TableView
		MainWindow.table.printShelter();
	}
}
