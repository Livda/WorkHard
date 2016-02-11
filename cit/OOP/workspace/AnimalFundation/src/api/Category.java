package api;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Category {
	protected GregorianCalendar date;
	protected Person contact;
	
	public String getNameContact() {
		return contact.getName();
	}
	
	public Person getContact(){
		return contact;
	}
	
	public String toString(){
		return date.get(Calendar.YEAR) + "," + date.get(Calendar.MONTH) 
		+ "," + date.get(Calendar.DAY_OF_MONTH) + "," + contact.getName();
	}
}
