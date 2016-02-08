package api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Saver {
	private File adoption;
	private File lost;
	private File found;
	private File persons;
	private File animals;

	public Saver(){
		adoption = new File("save/adoption");
		lost = new File("save/lost");
		found = new File("save/found");
		persons = new File("save/persons");
		animals = new File("save/animals");
	}

	public void save(AnimalShelter as){
		AnimalList foundAnimal = as.getFound();
		if (foundAnimal != null){
			List<Animal> list = foundAnimal.getList();
			for(Animal a : list){
				try {
					PrintWriter pw = new PrintWriter(animals);
					pw.println(a.toString());
					pw.close();
					Category cat = a.getMyCat();
					pw = new PrintWriter(found);
					pw.println(cat.toString());
					pw.close();
					Person p = cat.getContact();
					pw = new PrintWriter(persons);
					pw.println(p.toString());
					pw.close();
				}
				catch (IOException e) {
					System.out.println ("Error when trying to write : " 
							+ e.getMessage());
				}
			}
		}
		AnimalList adoptAnimal = as.getAdoption();
		if (adoptAnimal != null){
			List<Animal> list = adoptAnimal.getList();
			for(Animal a : list){
				try {
					PrintWriter pw = new PrintWriter(animals);
					pw.println(a.toString());
					pw.close();
					Category cat = a.getMyCat();
					pw = new PrintWriter(adoption);
					pw.println(cat.toString());
					pw.close();
					Person p = cat.getContact();
					pw = new PrintWriter(persons);
					pw.println(p.toString());
					pw.close();
				}
				catch (IOException e) {
					System.out.println ("Error when trying to write : " 
							+ e.getMessage());
				}
			}
		}
		AnimalList lostAnimal = as.getLost();
		if (lostAnimal != null){
			List<Animal> list = lostAnimal.getList();
			for(Animal a : list){
				try {
					PrintWriter pw = new PrintWriter(animals);
					pw.println(a.toString());
					pw.close();
					Category cat = a.getMyCat();
					pw = new PrintWriter(lost);
					pw.println(cat.toString());
					pw.close();
					Person p = cat.getContact();
					pw = new PrintWriter(persons);
					pw.println(p.toString());
					pw.close();
				}
				catch (IOException e) {
					System.out.println ("Error when trying to write : " 
							+ e.getMessage());
				}
			}
		}
		System.out.println("Shelter save");
	}
	
	public AnimalShelter load(){
		return null;
	}
}
