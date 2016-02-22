package api;

import java.util.GregorianCalendar;
import java.util.Map;

/**
 * @author Aurélien Fontaine
 * @version 1.0
 */
public class Lost extends Category {
	private String location;
	
	public Lost(int id, GregorianCalendar date, String location){
		this.id = id;
		this.date = date;
		this.location = location;
	}
	
	public Lost(int id, GregorianCalendar date, Person contact, String location){
		this.id = id;
		this.date = date;
		this.location = location;
		this.contact = contact;
	}
	
	public char getCategoryLetter() {
		return 'l';
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "," + location;
	}

	@Override
	public void print() {
		System.out.println(this.toString());
		
	}
	
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
