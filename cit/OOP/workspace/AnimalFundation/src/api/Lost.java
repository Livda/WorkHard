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
	 * Lost full constructor
	 * @param id The id of the Lost object
	 * @param date When the animal was lost
	 * @param contact Who lost the animal
	 * @param location Where the animal was lost
	 */
	public Lost(int id, GregorianCalendar date, Person contact, String location){
		super(id, date, contact);
		this.location = location;
	}
	
	/**
	 * Lost full constructor without the id mentionned
	 * @param id The id of the Lost object
	 * @param date When the animal was lost
	 * @param contact Who lost the animal
	 * @param location Where the animal was lost
	 */
	public Lost(GregorianCalendar date, Person contact, String location){
		super(date, contact);
		this.location = location;
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
		int year = Integer.parseInt(s[1]);
		int month = Integer.parseInt(s[2]);
		int day = Integer.parseInt(s[3]);
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		int idPerson = Integer.parseInt(s[4]);
		Person person = pTable.get(idPerson);
		String location = s[5];
		return new Lost(date, person, location);
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
