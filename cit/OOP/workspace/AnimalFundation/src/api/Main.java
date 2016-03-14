package api;

import java.util.*;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) {
		//Animal 1 - Found
		Person p1 = new Person(1, "toto", "1 test street", "+33 6 00 00 00 00", 
				"toto@nomail.com");
		GregorianCalendar calendar1 = new GregorianCalendar(12, 12, 12);
		Category foundCategory = new Found(1, calendar1, p1, "in my dreams");
		Animal animal1 = new Animal(1, 15, "red", true, "nice little cat with a lot of hair. Could be very affective if no one is around him. Likes break some glasses if not watched", 
				"Fluffy", null, "Cat", foundCategory, "not my type");
		
		//Animal 2 - Lost
		Person p2 = new Person(2, "lala", "no way", "9390393", "no@mail.com");
		GregorianCalendar calendar2 = new GregorianCalendar(13, 3, 13);
		Category lostCategory = new Lost(2, calendar2, p2, "somewhere");
		Animal animal2 = new Animal(2, 78, "blue", false, "zeuh", 
				"Compote", null, "Cat", lostCategory, "type2");
		
		//Animal 3 - Adopted
		Person p3 = new Person(3, "tutu", "42 street Murphy", "01234567889",
				"tutu@cit.ie");
		GregorianCalendar calendar3 = new GregorianCalendar(14, 4, 14);
		Adoption adoption = new Adoption(3, calendar3, p3, true, true, true);
		Animal animal3 = new Animal(3, 35, "white", true, "is an animal", 
				"Rex", null, "Dog", adoption, "Doge");
		
		//Shelter
		AnimalShelter shelter = new AnimalShelter();
		shelter.add(animal1);
		shelter.add(animal2);
		shelter.add(animal3);
		Saver saver = new Saver();
		saver.save(shelter);
		System.out.println(saver.load().toString());
	}

}
