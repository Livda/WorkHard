package api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class Person implements Comparable<Person> {

	private static int nextId = 0;
	private int id;
	private String name;
	private String adress;
	private String phone;
	private String email;

	/**
	 * Create an empty person
	 */
	public Person(){
		id = -1;
		name = null;
		adress = null;
		phone = null;
		email = null;
	}

	/**
	 * Create a full person
	 * @param id the id of the Person
	 * @param name the name of the Person
	 * @param adress the address of the Person
	 * @param phone the phone number of the Person
	 * @param email the mail of the Person
	 */
	public Person(int id, String name, String adress, String phone, String email){
		this.id = id;
		nextId = nextId <= id ? id + 1 : nextId;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * Create a full person without the id mentioned
	 * @param name the name of the Person
	 * @param adress the address of the Person
	 * @param phone the phone number of the Person
	 * @param email the mail of the Person
	 */
	public Person(String name, String adress, String phone, String email){
		this.id = Person.nextId;
		Person.nextId++;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * Update the attributes of a Person but not the id nether the name
	 * @param p the Person to take the attributes
	 */
	public void updatePerson(Person p) {
		this.adress = p.getAdress();
		this.phone = p.getPhone();
		this.email = p.getEmail();
	}

	/**
	 * get the id
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * set the id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get the name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the address
	 * @return address
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * set the address
	 * @param adress
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * get the phone
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * set the phone
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * get the email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set the email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return id + "," + name + "," + adress + "," + phone + "," + email;
	}

	/**
	 * Print the person in the console
	 */
	public void print() {
		System.out.println(this.toString());
	}

	/**
	 * Save the person in a file
	 * @param persons the file where the person will be saved
	 */
	public void save(File persons) {
		BufferedWriter bw;
		try {
		bw = new BufferedWriter(new FileWriter(persons, true));
		bw.write(this.toString());
		bw.newLine();
		bw.flush();
		bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to save a person : "
					+ e.getMessage());
		}
	}

	/**
	 * Load a person from a String array
	 * @param s The array
	 * @return The new person object loaded
	 */
	public static Person load(String[] s){
		String name = s[1];
		String adress = s[2];
		String phone = s[3];
		String email = s[4];
		return new Person(name, adress, phone, email);
	}

	public void fillReport(File report){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(report, true));
			bw.write(this.getName() + " & " + this.getAdress() + " & " + this.getPhone()
			+ " & " + this.getEmail() + "\\\\");
			bw.newLine();
			bw.write("\\hline");
			bw.newLine();
			bw.flush();
			bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to put a person in the report : "
					+ e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Person o) {
		return this.name.compareTo(o.getName());
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
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
