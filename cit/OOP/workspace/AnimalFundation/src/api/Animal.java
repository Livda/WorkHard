package api;

public class Animal {
	private int id;
	private int age;
	private String colour;
	private boolean gender;
	private String description;
	private String name;
	private String pathToPicture;
	private String breed;
	private Category myCat;
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
	public boolean isGender() {
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
	 * @return the myCat
	 */
	public Category getMyCat() {
		return myCat;
	}
	/**
	 * @param myCat the myCat to set
	 */
	public void setMyCat(Category myCat) {
		this.myCat = myCat;
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
	 * @param id
	 * @param age
	 * @param colour
	 * @param gender
	 * @param description
	 * @param name
	 * @param pathToPicture
	 * @param breed
	 * @param myCat
	 * @param type
	 */
	public Animal(int id, int age, String colour, boolean gender, 
			String description, String name, String pathToPicture,
			String breed, Category myCat, String type) {
		this.id = id;
		this.age = age;
		this.colour = colour;
		this.gender = gender;
		this.description = description;
		this.name = name;
		this.pathToPicture = pathToPicture;
		this.breed = breed;
		this.myCat = myCat;
		this.type = type;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id + "," + age + "," + colour + "," + gender + "," + description 
				+ "," + name + "," + pathToPicture + "," + breed + "," 
				+ myCat.getNameContact() + "," + type;
	}
	
}
