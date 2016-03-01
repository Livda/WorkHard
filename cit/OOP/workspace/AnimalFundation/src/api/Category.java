package api;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public abstract class Category {
	protected int id;
	protected GregorianCalendar date;
	protected Person contact;
	
	/**
	 * Print the current Category in the console
	 */
	public abstract void print();
	
	/**
	 * Get the first letter of the Category
	 * @return The first letter of the category
	 */
	public abstract char getCategoryLetter();
	
	/**
	 * Get the id
	 * @return id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Get the contact
	 * @return contact
	 */
	public Person getContact(){
		return contact;
	}
	
	public String toStringForSave(){
		return id + "," + date.get(Calendar.YEAR) + "," + date.get(Calendar.MONTH) 
		+ "," + date.get(Calendar.DAY_OF_MONTH) + "," + contact.getId();
	}
	
	/**
	 * Save the current Category
	 * @param categoryFile The File were the Category will be saved
	 * @param persons The File were the persons will be saved
	 */
	public void save(File categoryFile, File persons){
		contact.save(persons);

		BufferedWriter bw;
		try {
		bw = new BufferedWriter(new FileWriter(categoryFile, true));
		bw.write(this.toStringForSave());
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
