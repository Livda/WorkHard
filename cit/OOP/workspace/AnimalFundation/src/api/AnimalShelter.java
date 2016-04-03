package api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class AnimalShelter {
	private AnimalList adoption;
	private AnimalList lost;
	private AnimalList found;
	private ArrayList<Person> persons;
	
	/**
	 * @return the adoption
	 */
	public AnimalList getAdoption() {
		return adoption;
	}
	/**
	 * @param adoption the adoption to set
	 */
	public void setAdoption(AnimalList adoption) {
		this.adoption = adoption;
	}
	/**
	 * @return the lost
	 */
	public AnimalList getLost() {
		return lost;
	}
	/**
	 * @param lost the lost to set
	 */
	public void setLost(AnimalList lost) {
		this.lost = lost;
	}
	/**
	 * @return the found
	 */
	public AnimalList getFound() {
		return found;
	}
	/**
	 * @param found the found to set
	 */
	public void setFound(AnimalList found) {
		this.found = found;
	}
	
	/**
	 * Create an empty Shelter
	 */
	public AnimalShelter() {
		this.persons = new ArrayList<Person>();
		this.adoption = new AnimalList();
		this.lost = new AnimalList();
		this.found = new AnimalList();
	}
	
	/**
	 * AnimalShelter constructor by lists
	 * @param adoption the Adoption list
	 * @param lost the Lost list
	 * @param found the Found list
	 */
	public AnimalShelter(AnimalList adoption, AnimalList lost, AnimalList found) {
		ArrayList<Person> persons = new ArrayList<Person>();
		this.adoption = adoption;
		persons.addAll(adoption.getAllPersons());
		this.lost = lost;
		persons.addAll(lost.getAllPersons());
		this.found = found;
		persons.addAll(found.getAllPersons());
		this.persons = persons;
	}
	
	/**
	 * Save the all Shelter in files
	 * @param adoption The file where the Adoptions will be saved
	 * @param lost The file where the Losts will be saved
	 * @param found The file where the Founds will be saved
	 * @param animals The file where the Animals will be saved
	 * @param persons The file where the Persons will be saved
	 */
	public void save(File adoption, File lost, File found, File animals, File persons){
		if(this.adoption != null) this.adoption.save(animals, adoption, persons);
		if(this.found != null) this.found.save(animals, found, persons);
		if(this.lost != null) this.lost.save(animals, lost, persons);
	}
	
	public String toString(){
		String res = "Animal Shelter :\n";
		res += "Adoption :\n" + adoption.toString() + "\n";
		res += "Lost :\n" + lost.toString() + "\n";
		res += "Found :\n" + found.toString();
		return res;
	}
	
	public ArrayList<Animal> getAllAnimals(){
		ArrayList<Animal> list = new ArrayList<Animal>();
		List<Animal> temp = adoption.getList();
		list.addAll(temp);
		temp = lost.getList();
		list.addAll(temp);
		temp = found.getList();
		list.addAll(temp);
		return list;
	}
	
	public ArrayList<Person> getAllPersons(){
		return persons;
	}
	/**
	 * Add an Animal to the shelter
	 * @param a the animal
	 */
	public void add(Animal a) {
		Category cat = a.getAnimalCategory();
		Person p = cat.getContact();
		if (!persons.contains(p)) persons.add(p);
		switch (cat.getCategoryLetter()) {
		case 'a' :
			adoption.add(a);
			break;
		case 'f' :
			found.add(a);
			break;
		case 'l' :
			lost.add(a);
			break;
		}
	}
	
	/**
	 * Update the attributes of the Person in the list by the Person passed on parameter
	 * @param p the Person to take the attributes
	 */
	public void updatePerson(Person p){
		int index = persons.indexOf(p);
		Person update = persons.get(index);
		update.updatePerson(p);
	}
	
	/**
	 * Remove an animal from the shelter
	 * @param a the animal
	 */
	public void remove(Animal a){
		Category cat = a.getAnimalCategory();
		switch (cat.getCategoryLetter()) {
		case 'a' :
			adoption.remove(a);
			break;
		case 'f' :
			found.remove(a);
			break;
		case 'l' :
			lost.remove(a);
			break;
		}
	}
} 
