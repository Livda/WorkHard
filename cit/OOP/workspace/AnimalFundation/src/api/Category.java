package api;
import java.util.Date;

public abstract class Category {
	protected Date date;
	protected Person contact;
	
	public String getNameContact() {
		return contact.getName();
	}
	
	public Person getContact(){
		return contact;
	}
	
	public String toString(){
		return date + "," + contact.getName();
	}
}
