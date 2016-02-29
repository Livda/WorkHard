package api;

import java.io.File;
import java.util.ArrayList;

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
		this.adoption = this.lost = this.found = null;
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
	
	public ArrayList<Animal> getAllAnimals(){
		ArrayList<Animal> list = new ArrayList<Animal>();
		list.addAll(adoption.getList());
		list.addAll(lost.getList());
		list.addAll(found.getList());
		return list;
	}
} 
