package api;

import java.util.GregorianCalendar;

public class Found extends Category {
	private String location;
	
	public Found(GregorianCalendar date, String location){
		this.date = date;
		this.location = location;
	}
	
	public Found(GregorianCalendar date, String location, Person contact){
		this.date = date;
		this.location = location;
		this.contact = contact;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FOUND," + super.toString() + "," + location;
	}

	@Override
	public void print() {
		System.out.println(this.toString());
		
	}
}
