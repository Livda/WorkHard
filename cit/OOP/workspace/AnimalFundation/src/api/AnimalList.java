package api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	
	public boolean add(Animal a) {
		return list.add(a);
	}
	
	public boolean remove(Animal a) {
		return list.remove(a);
	}
	
	public void printList() {
		System.out.println(this);
	}
	
	public void save(File animals, File categoryFile, File persons){
		for(Animal a : this.list){
			a.save(animals, categoryFile, persons);
		}
	}
}
