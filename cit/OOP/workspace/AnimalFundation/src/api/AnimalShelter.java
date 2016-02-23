package api;

import java.io.File;

/**
 * @author Aurélien Fontaine
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
	
	public AnimalShelter() {
		this.adoption = this.lost = this.found = null;
	}
	
	/**
	 * @param adoption
	 * @param lost
	 * @param found
	 */
	public AnimalShelter(AnimalList adoption, AnimalList lost, AnimalList found) {
		this.adoption = adoption;
		this.lost = lost;
		this.found = found;
	}
	
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
}
