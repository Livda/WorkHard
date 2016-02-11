package api;

import java.util.GregorianCalendar;

public class Lost extends Category {
	private String location;
	
	public Lost(GregorianCalendar date, String location){
		this.date = date;
		this.location = location;
	}
	
	public Lost(GregorianCalendar date, String location, Person contact){
		this.date = date;
		this.location = location;
		this.contact = contact;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "," + location;
	}	
}
