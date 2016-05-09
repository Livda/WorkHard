package api;

import java.io.File;
import java.time.LocalDate;
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

	/**
	 * @return the adoption
	 */
	public AnimalList getAdoption() {
		return new AnimalList(DataBaseManager.getDataBaseManager().getAllAdoptionAnimals());
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
		return new AnimalList(DataBaseManager.getDataBaseManager().getAllLostAnimals());
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
		return new AnimalList(DataBaseManager.getDataBaseManager().getAllFoundAnimals());
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
		this.adoption = adoption;
		this.lost = lost;
		this.found = found;
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

	public List<Animal> getAllAnimals(){
		DataBaseManager dbm = DataBaseManager.getDataBaseManager();
		List<Animal> animals =dbm.getAllAnimals();
		return animals;
	}

	public List<Person> getAllPersons(){
		DataBaseManager dbm = DataBaseManager.getDataBaseManager();
		List<Person> persons = dbm.getAllPersons();
		return persons;
	}
	/**
	 * Add an Animal to the shelter
	 * @param a the animal
	 */
	public void add(Animal a) {
		DataBaseManager.getDataBaseManager().add(a);
	}

	/**
	 * Update the attributes of the Person in the list by the Person passed on parameter
	 * @param p the Person to take the attributes
	 */
	public void updatePerson(Person p){
		DataBaseManager.getDataBaseManager().updatePerson(p);
	}

	/**
	 * Remove an animal from the shelter
	 * @param a the animal
	 */
	public void remove(Animal a){
		DataBaseManager.getDataBaseManager().remove(a);
	}
	
	public void update(){
		List<Animal> found = new ArrayList<Animal>(this.found.getList());
		for(Animal a : found){
			Found category =(Found) a.getAnimalCategory();
			LocalDate aMonthAgo = LocalDate.now().minusMonths(1);
			if (category.getDate().isBefore(aMonthAgo)){
				Person p = category.getContact();
				Adoption adoption = new Adoption(LocalDate.now(), p, false, false, false,
						false, false);
				a.setAnimalCategory(adoption);;
				this.adoption.add(a);
				this.found.remove(a);
			}
		}
	}
}
