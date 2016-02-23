package api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class AnimalList {
	private List<Animal> list;

	/**
	 * @return the list
	 */
	public List<Animal> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Animal> list) {
		this.list = list;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnimalList " + list;
	}

	public AnimalList(){
		this.list = new ArrayList<Animal>();
	}
	
	/**
	 * @param list
	 */
	public AnimalList(List<Animal> list) {
		this.list = list;
	}
	
	/**
	 * Add an animal to the list
	 * @param a The animal to add
	 * @return True if the animal is added
	 */
	public boolean add(Animal a) {
		return list.add(a);
	}
	
	/**
	 * Remove an animal from the list
	 * @param a The animal to remove
	 * @return True if the animal is removed
	 */
	public boolean remove(Animal a) {
		return list.remove(a);
	}
	
	/**
	 * Print in the console the animal
	 */
	public void printList() {
		System.out.println(this);
	}
	
	/**
	 * Save the animal in a file
	 * @param animals The file were the animal will be saved
	 * @param categoryFile The file were the Category will be saved
	 * @param persons The file where the person will be saved
	 */
	public void save(File animals, File categoryFile, File persons){
		for(Animal a : this.list){
			a.save(animals, categoryFile, persons);
		}
	}
}
