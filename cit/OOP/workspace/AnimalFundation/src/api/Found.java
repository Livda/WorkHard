package api;

import java.util.Date;

public class Found extends Category {
	private String location;
	
	public Found(Date date, String location){
		this.date = date;
		this.location = location;
	}
	
	public Found(Date date, String location, Person contact){
		this.date = date;
		this.location = location;
		this.contact = contact;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Found [location=" + location + ", date=" + date + ", contact=" + contact + "]";
	}
	
	
}
