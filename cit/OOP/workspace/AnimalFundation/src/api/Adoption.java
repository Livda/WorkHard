package api;

import java.util.GregorianCalendar;

public class Adoption extends Category{
	private boolean neutered;
	private boolean chipped;
	private boolean vaccinated;
	
	public Adoption(GregorianCalendar date, Person contact){
		this.date = date;
		this.contact = contact;
		this.neutered = this.chipped = this.vaccinated = false;
	}
	
	public Adoption(GregorianCalendar date, Person contact, boolean neut, boolean chip, boolean vac){
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
		return super.toString() + "," + neutered + "," + chipped + "," + vaccinated;
	}

	public void print() {
		String s = "Adoption :\n";
		s += "date  : " + date + "\n";
		System.out.println(s);
	}
}
