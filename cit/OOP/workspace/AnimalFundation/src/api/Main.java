package api;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Person p1 = new Person("toto", "1 test street", "+33 6 00 00 00 00", "toto@nomail.com");
		List<Animal> list = new ArrayList<Animal>();
		Category category = new Found(new Date(50), "in my dreams", p1);
		Animal animal = new Animal(1, 15, "red", true, "nice little frog", "fluffy", null, "super sayan", category, "not my type");
		list.add(animal);
		AnimalShelter shelter = new AnimalShelter();
		shelter.setFound(new AnimalList(list));
		Saver saver = new Saver();
		saver.save(shelter);
	}

}
