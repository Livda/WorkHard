package api;

import java.util.GregorianCalendar;
import java.util.Map;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class Found extends Category {
	private String location;
	
	/**
	 * Found partial constructor
	 * @param id The id of the Found object
	 * @param date The date when the animal was founded
	 * @param location Where the animal was founded
	 */
	public Found(int id, GregorianCalendar date, String location){
		this.id = id;
		this.date = date;
		this.location = location;
	}
	
	/**
	 * Found full constructor
	 * @param id The id of the Found object
	 * @param date The date when the animal was founded
	 * @param contact The person who find the animal
	 * @param location Where the animal was founded
	 */
	public Found(int id, GregorianCalendar date, Person contact, String location){
		this.id = id;
		this.date = date;
		this.location = location;
		this.contact = contact;
	}
	
	public char getCategoryLetter(){
		return 'f';
	}

	public String toString(){
		return "Found";
	}
	
	public String toStringForSave() {
		return super.toStringForSave() + "," + location;
	}

	public void print() {
		System.out.println(this.toStringForSave());
		
	}
	
	/**
	 * Load an Found object from a String array an a Map with the Person
	 * @param s The String array
	 * @param pTable The Map with the Person
	 * @return the Found object
	 */
	public static Found load(String[] s, Map<Integer, Person> pTable){
		int id = Integer.parseInt(s[0]);
		int year = Integer.parseInt(s[1]);
		int month = Integer.parseInt(s[2]);
		int day = Integer.parseInt(s[3]);
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		int idPerson = Integer.parseInt(s[4]);
		Person person = pTable.get(idPerson);
		String location = s[5];
		return new Found(id, date, person, location);
	}
}
