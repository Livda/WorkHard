package api;

import java.util.GregorianCalendar;
import java.util.Map;

public class Adoption extends Category{
	private boolean neutered;
	private boolean chipped;
	private boolean vaccinated;
	
	public Adoption(int id, GregorianCalendar date, Person contact){
		this.id = id;
		this.date = date;
		this.contact = contact;
		this.neutered = this.chipped = this.vaccinated = false;
	}
	
	public Adoption(int id, GregorianCalendar date, Person contact, 
			boolean neut, boolean chip, boolean vac){
		this.id = id;
		this.date = date;
		this.contact = contact;
		this.neutered = neut;
		this.chipped = chip;
		this.vaccinated = vac;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "," + neutered + "," + chipped 
				+ "," + vaccinated;
	}

	public void print() {
		System.out.println(this.toString());
	}
	
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
