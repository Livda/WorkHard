package api;

import java.util.GregorianCalendar;
import java.util.Map;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class Adoption extends Category{
	private boolean neutered;
	private boolean chipped;
	private boolean vaccinated;
	
	/*
	 * Adoption partial constructor
	 * @param id The id of the Adoption object
	 * @param date The date when the animal was adopted
	 * @param contact The person who adopt the animal
	 */
	public Adoption(int id, GregorianCalendar date, Person contact){
		this.id = id;
		this.date = date;
		this.contact = contact;
		this.neutered = this.chipped = this.vaccinated = false;
	}
	
	/**
	 * Adoption full constructor
	 * @param id The id of the new Adoption category
	 * @param date The date when the animal was adopted
	 * @param contact The person who adopt the animal
	 * @param neut Is the animal is neutered
	 * @param chip Is the animal chipped
	 * @param vac Is the animal vaccinated
	 */
	public Adoption(int id, GregorianCalendar date, Person contact, 
			boolean neut, boolean chip, boolean vac){
		this.id = id;
		this.date = date;
		this.contact = contact;
		this.neutered = neut;
		this.chipped = chip;
		this.vaccinated = vac;
	}
	
	public char getCategoryLetter() {
		return 'a';
	}
	
	public String toString() {
		return super.toString() + "," + neutered + "," + chipped 
				+ "," + vaccinated;
	}

	public void print() {
		System.out.println(this.toString());
	}
	
	/**
	 * Load an Adoption object from a String array an a Map with the Person
	 * @param s The String array
	 * @param pTable The Map with the Person
	 * @return the Adoption object
	 */
	public static Adoption load(String[] s, Map<Integer, Person> pTable){
		int id = Integer.parseInt(s[0]);
		int year = Integer.parseInt(s[1]);
		int month = Integer.parseInt(s[2]);
		int day = Integer.parseInt(s[3]);
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		int idPerson = Integer.parseInt(s[4]);
		Person person = pTable.get(idPerson);
		boolean neutered = Boolean.parseBoolean(s[5]);
		boolean chipped = Boolean.parseBoolean(s[6]);
		boolean vaccinated = Boolean.parseBoolean(s[7]);
		return new Adoption(id, date, person, neutered, chipped, vaccinated);
	}
}
