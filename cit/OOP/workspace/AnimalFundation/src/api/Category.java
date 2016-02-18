package api;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Aurélien Fontaine
 * @version 1.0
 */
public abstract class Category {
	protected int id;
	protected GregorianCalendar date;
	protected Person contact;
	
	public abstract void print();
	
	public int getId() {
		return this.id;
	}
	
	public Person getContact(){
		return contact;
	}
	
	public String toString(){
		return id + "," + date.get(Calendar.YEAR) + "," + date.get(Calendar.MONTH) 
		+ "," + date.get(Calendar.DAY_OF_MONTH) + "," + contact.getId();
	}
	
	public void save(File categoryFile, File persons){
		contact.save(persons);

		BufferedWriter bw;
		try {
		bw = new BufferedWriter(new FileWriter(categoryFile, true));
		bw.write(this.toString());
		bw.newLine();
		bw.flush();
		bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to save a category : " 
					+ e.getMessage());
		}
	}
}
