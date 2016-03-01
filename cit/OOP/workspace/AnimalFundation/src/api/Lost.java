package api;

import java.util.GregorianCalendar;
import java.util.Map;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class Lost extends Category {
	private String location;
	
	/**
	 * Lost partial constructor
	 * @param id The id of the Lost object
	 * @param date When the animal was lost
	 * @param location Where the animal was lost
	 */
	public Lost(int id, GregorianCalendar date, String location){
		this.id = id;
		this.date = date;
		this.location = location;
	}
	
	/**
	 * Lost full constructor
	 * @param id The id of the Lost object
	 * @param date When the animal was lost
	 * @param contact Who lost the animal
	 * @param location Where the animal was lost
	 */
	public Lost(int id, GregorianCalendar date, Person contact, String location){
		this.id = id;
		this.date = date;
		this.location = location;
		this.contact = contact;
	}
	
	public char getCategoryLetter() {
		return 'l';
	}

	public String toString(){
		return "Lost";
	}
	
	public String toStringForSave() {
		return super.toStringForSave() + "," + location;
	}

	public void print() {
		System.out.println(this.toStringForSave());
		
	}
	
	/**
	 * Load an Lost object from a String array an a Map with the Person
	 * @param s The String array
	 * @param pTable The Map with the Person
	 * @return the Lost object
	 */
	public static Lost load(String[] s, Map<Integer, Person> pTable){
		int id = Integer.parseInt(s[0]);
		int year = Integer.parseInt(s[1]);
		int month = Integer.parseInt(s[2]);
		int day = Integer.parseInt(s[3]);
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		int idPerson = Integer.parseInt(s[4]);
		Person person = pTable.get(idPerson);
		String location = s[5];
		return new Lost(id, date, person, location);
	}
}
