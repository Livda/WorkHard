package api;

import java.util.*;

/**
 * @author Aurélien Fontaine
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		//Animal 1 - Found
		Person p1 = new Person(1, "toto", "1 test street", "+33 6 00 00 00 00", 
				"toto@nomail.com");
		GregorianCalendar calendar1 = new GregorianCalendar(12, 12, 12);
		Category foundCategory = new Found(1, calendar1, p1, "in my dreams");
		Animal animal1 = new Animal(1, 15, "red", true, "nice little frog", 
				"fluffy", null, "super sayan", foundCategory, "not my type");
		List<Animal> foundList = new ArrayList<Animal>();
		foundList.add(animal1);
		
		//Animal 2 - Lost
		Person p2 = new Person(2, "lala", "no way", "9390393", "no@mail.com");
		GregorianCalendar calendar2 = new GregorianCalendar(13, 3, 13);
		Category lostCategory = new Lost(2, calendar2, p2, "somewhere");
		Animal animal2 = new Animal(2, 78, "blue", false, "zeuh", 
				"lapfl", null, "vfezh", lostCategory, "type2");
		List<Animal> lostList = new ArrayList<Animal>();
		lostList.add(animal2);
		
		//Animal 3 - Adopted
		Person p3 = new Person(3, "tutu", "42 street Murphy", "01234567889",
				"tutu@cit.ie");
		GregorianCalendar calendar3 = new GregorianCalendar(14, 4, 14);
		Adoption adoption = new Adoption(3, calendar3, p3, true, true, true);
		Animal animal3 = new Animal(3, 35, "white", true, "is an animal", 
				"noTitle", null, "dog", adoption, "Doge");
		List<Animal> adoptionList = new ArrayList<Animal>();
		adoptionList.add(animal3);
		
		//Shelter
		AnimalShelter shelter = new AnimalShelter();
		shelter.setFound(new AnimalList(foundList));
		shelter.setLost(new AnimalList(lostList));
		shelter.setAdoption(new AnimalList(adoptionList));
		Saver saver = new Saver();
		saver.save(shelter);
		System.out.println(saver.load().toString());
	}

}
