package api;

import java.util.Date;

public class Lost extends Category {
	private String location;
	
	public Lost(Date date, String location){
		this.date = date;
		this.location = location;
	}
	
	public Lost(Date date, String location, Person contact){
		this.date = date;
		this.location = location;
		this.contact = contact;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lost [location=" + location + ", date=" + date + ", contact=" + contact + "]";
	}	
}
