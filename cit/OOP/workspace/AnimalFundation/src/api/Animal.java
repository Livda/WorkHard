package api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class Animal implements Comparable<Animal>, Comparator<Animal>{
	private static int nextId = 0;
	private int id;
	private int age;
	private String colour;
	private boolean gender;
	private String description;
	private String name;
	private String pathToPicture;
	private String breed;
	private Category animalCategory;
	private String type;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}
	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	/**
	 * @return the gender
	 */
	public boolean getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pathToPicture
	 */
	public String getPathToPicture() {
		return pathToPicture;
	}
	/**
	 * @param pathToPicture the pathToPicture to set
	 */
	public void setPathToPicture(String pathToPicture) {
		this.pathToPicture = pathToPicture;
	}
	/**
	 * @return the breed
	 */
	public String getBreed() {
		return breed;
	}
	/**
	 * @param breed the breed to set
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}
	/**
	 * @return the Category
	 */
	public Category getAnimalCategory() {
		return animalCategory;
	}
	/**
	 * @param animalCategory the Category to set
	 */
	public void setAnimalCategory(Category animalCategory) {
		this.animalCategory = animalCategory;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Animal full constructor
	 * @param id The id of the Animal object
	 * @param age the age of the animal
	 * @param colour the color of the animal
	 * @param gender male = true, female = false
	 * @param description a description of the animal
	 * @param name the name of the animal
	 * @param pathToPicture where the picture of the animal is store
	 * @param breed the breed of the animal
	 * @param animalCategory the Category of animal
	 * @param type the type of the animal
	 */
	public Animal(int id, int age, String colour, boolean gender, 
			String description, String name, String pathToPicture,
			String breed, Category animalCategory, String type) {
		this.id = id;
		this.age = age;
		this.colour = colour;
		this.gender = gender;
		this.description = description;
		this.name = name;
		this.pathToPicture = pathToPicture;
		this.breed = breed;
		this.animalCategory = animalCategory;
		this.type = type;
	}
	
	/**
	 * Animal full constructor without the id mentioned
	 * @param id The id of the Animal object
	 * @param age the age of the animal
	 * @param colour the color of the animal
	 * @param gender male = true, female = false
	 * @param description a description of the animal
	 * @param name the name of the animal
	 * @param pathToPicture where the picture of the animal is store
	 * @param breed the breed of the animal
	 * @param animalCategory the Category of animal
	 * @param type the type of the animal
	 */
	public Animal(int age, String colour, boolean gender, 
			String description, String name, String pathToPicture,
			String breed, Category animalCategory, String type) {
		this.id = Animal.nextId;
		Animal.nextId++;
		this.age = age;
		this.colour = colour;
		this.gender = gender;
		this.description = description;
		this.name = name;
		this.pathToPicture = pathToPicture;
		this.breed = breed;
		this.animalCategory = animalCategory;
		this.type = type;
	}
	
	/**
	 * 
	 */
	public Animal() {
		id = age = -1;
		name = "Test";
		colour = description = pathToPicture = breed = null;
		gender = false; 
		animalCategory = null;
		type = null;
	}
	public String toString() {
		return id + "," + age + "," + colour + "," + gender + "," + description 
				+ "," + name + "," + pathToPicture + "," + breed + "," 
				+ animalCategory.getCategoryLetter() + "," +  animalCategory.getId() + "," + type;
	}
	
	/**
	 * Save the current animal in a file
	 * @param animals The file where the animal will be save
	 * @param categoryFile The file where the category will be save
	 * @param persons The file where the person will be save
	 */
	public void save(File animals, File categoryFile, File persons){
		animalCategory.save(categoryFile, persons);

		BufferedWriter bw;
		try {
		bw = new BufferedWriter(new FileWriter(animals, true));
		bw.write(this.toString());
		bw.newLine();
		bw.flush();
		bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to save an animal : " 
					+ e.getMessage());
		}
	}
	
	/**
	 * Load an Animal from a String array an a Map with the Category
	 * @param s The String array
	 * @param cat the Map with the Category
	 * @return Animal object loaded
	 */
	public static Animal load(String[] s, Category cat){
		int id = Integer.parseInt(s[0]);
		int age = Integer.parseInt(s[1]);
		String color = s[2];
		boolean gender = Boolean.parseBoolean(s[3]);
		String description = s[4];
		String name = s[5];
		String pathToPicture = s[6];
		String breed = s[7];
		String type = s[10];
		return new Animal(id, age, color, gender, description, name,
				pathToPicture, breed, cat, type); 
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public void fillReport(File report){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(report, true));
			bw.write(this.getName() + " & " + this.getAge() + " & " + this.getDescription()
			+ " & " + this.getColour() + " & " + this.getBreed() + " & " + this.getType()
			+ " & " + this.getAnimalCategory().toString() + "\\\\");
			bw.newLine();
			bw.write("\\hline");
			bw.newLine();
			bw.flush();
			bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to put an animal in the report : " 
					+ e.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Animal o) {
		int res = this.name.compareTo(o.getName());
		if (res == 0){
			return this.id < o.getId() ? -1 : 1;
		} else {
			return res;
		}
	}
	
	public int compare(Animal a1, Animal a2){
		String cat1 = a1.getAnimalCategory().toString();
		String cat2 = a2.getAnimalCategory().toString();
		return cat1.compareTo(cat2); 
	}
	
}
