package api;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public abstract class Category {
	private static int nextId = 0;
	private int id;
	//TODO change to LocalDate
	private LocalDate date;
	private Person contact;

	public Category(int id, LocalDate date, Person contact){
		this.id = id;
		this.date = date;
		this.contact = contact;
	}

	public Category(LocalDate date, Person contact){
		this.id = nextId;
		nextId++;
		this.date = date;
		this.contact = contact;
	}

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

	public LocalDate getDate(){
		return this.date;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(Person contact) {
		this.contact = contact;
	}

	/**
	 * Get the contact
	 * @return contact
	 */
	public Person getContact(){
		return contact;
	}

	public String toStringForSave(){
		return id + "," + date.getYear() + "," + date.getMonthValue()
		+ "," + date.getDayOfMonth() + "," + contact.getId();
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
