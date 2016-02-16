package api;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Person p1 = new Person(1, "toto", "1 test street", "+33 6 00 00 00 00", 
				"toto@nomail.com");
		List<Animal> foundList = new ArrayList<Animal>();
		GregorianCalendar calendar1 = new GregorianCalendar(12, 12, 12);
		Category foundCategory = new Found(calendar1, "in my dreams", p1);
		Animal animal1 = new Animal(1, 15, "red", true, "nice little frog", 
				"fluffy", null, "super sayan", foundCategory, "not my type");
		foundList.add(animal1);
		Person p2 = new Person(2, "lala", "no way", "9390393", "no@mail.com");
		List<Animal> lostList = new ArrayList<Animal>();
		GregorianCalendar calendar2 = new GregorianCalendar(13, 13, 13);
		Category lostCategory = new Lost(calendar2, "somewhere", p2);
		Animal animal2 = new Animal(2, 78, "blue", false, "zeuh", 
				"lapfl", null, "vfezh", lostCategory, "type2");
		lostList.add(animal2);
		AnimalShelter shelter = new AnimalShelter();
		shelter.setFound(new AnimalList(foundList));
		shelter.setLost(new AnimalList(lostList));
		Saver saver = new Saver();
		saver.save(shelter);
		//saver.load();
	}

}
