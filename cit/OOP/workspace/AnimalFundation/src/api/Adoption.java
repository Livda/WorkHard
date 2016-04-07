package api;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 */
public class Adoption extends Category{
	private boolean neutered;
	private boolean chipped;
	private boolean vaccinated;
	private boolean reserved;
	//private enum status {inTraining, ready};
	
	
	/**
	 * Adoption full constructor
	 * @param id The id of the new Adoption category
	 * @param date The date when the animal was adopted
	 * @param contact The person who adopt the animal
	 * @param neut Is the animal is neutered
	 * @param chip Is the animal chipped
	 * @param vac Is the animal vaccinated
	 */
	public Adoption(int id, LocalDate date, Person contact, 
			boolean neut, boolean chip, boolean vac){
		super(id, date, contact);
		this.neutered = neut;
		this.chipped = chip;
		this.vaccinated = vac;
	}
	
	/**
	 * Adoption full constructor without the id mentionned
	 * @param id The id of the new Adoption category
	 * @param date The date when the animal was adopted
	 * @param contact The person who adopt the animal
	 * @param neut Is the animal is neutered
	 * @param chip Is the animal chipped
	 * @param vac Is the animal vaccinated
	 */
	public Adoption(LocalDate date, Person contact, 
			boolean neut, boolean chip, boolean vac){
		super(date, contact);
		this.neutered = neut;
		this.chipped = chip;
		this.vaccinated = vac;
	}
	
	public char getCategoryLetter() {
		return 'a';
	}
	
	public String toString(){
		return "Adoption";
	}
	
	public String toStringForSave() {
		return super.toStringForSave() + "," + neutered + "," + chipped 
				+ "," + vaccinated;
	}

	public void print() {
		System.out.println(this.toStringForSave());
	}
	
	/**
	 * Load an Adoption object from a String array an a Map with the Person
	 * @param s The String array
	 * @param pTable The Map with the Person
	 * @return the Adoption object
	 */
	public static Adoption load(String[] s, Map<Integer, Person> pTable){
		int year = Integer.parseInt(s[1]);
		int month = Integer.parseInt(s[2]);
		int day = Integer.parseInt(s[3]);
		LocalDate date = LocalDate.of(year, month, day); 
		int idPerson = Integer.parseInt(s[4]);
		Person person = pTable.get(idPerson);
		boolean neutered = Boolean.parseBoolean(s[5]);
		boolean chipped = Boolean.parseBoolean(s[6]);
		boolean vaccinated = Boolean.parseBoolean(s[7]);
		return new Adoption(date, person, neutered, chipped, vaccinated);
	}

	/**
	 * @return the neutered
	 */
	public boolean isNeutered() {
		return neutered;
	}

	/**
	 * @param neutered the neutered to set
	 */
	public void setNeutered(boolean neutered) {
		this.neutered = neutered;
	}

	/**
	 * @return the chipped
	 */
	public boolean isChipped() {
		return chipped;
	}

	/**
	 * @param chipped the chipped to set
	 */
	public void setChipped(boolean chipped) {
		this.chipped = chipped;
	}

	/**
	 * @return the vaccinated
	 */
	public boolean isVaccinated() {
		return vaccinated;
	}

	/**
	 * @param vaccinated the vaccinated to set
	 */
	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}

	/**
	 * @return the reserved
	 */
	public boolean isReserved() {
		return reserved;
	}

	/**
	 * @param reserved the reserved to set
	 */
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
}
