/**
 * 
 */
package api;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class DataBaseManager {
	private static DataBaseManager INSTANCE = null;
	private String url;
	private String user;
	private String password;
	private String personsTable;
	private String adoptionTable;
	private String foundTable;
	private String lostTable;
	private String animalTable;
	
	private DataBaseManager(){
		this.url = "jdbc:mysql://localhost:3306/db";
		this.user="root";
		this.password="";
		this.personsTable = "persons";
		this.adoptionTable = "adoption";
		this.foundTable = "found";
		this.lostTable = "lost";
		this.animalTable = "animals";
	}
	
	public static DataBaseManager getDataBaseManager(){
		if (INSTANCE == null){
			INSTANCE = new DataBaseManager();
		}
		return INSTANCE;
	}
	
	public AnimalShelter getSavedShelter(){
		List<Animal> animals = this.getAllAnimals();
		
		ArrayList<Animal> adoption = new ArrayList<Animal>();
		ArrayList<Animal> found = new ArrayList<Animal>();
		ArrayList<Animal> lost = new ArrayList<Animal>();
		
		for (Animal a : animals){
			char catLetter = a.getAnimalCategory().getCategoryLetter();
			switch(catLetter){
			case 'a':
				adoption.add(a);
				break;
			case 'f':
				found.add(a);
				break;
			case 'l':
				lost.add(a);
				break;
			}
		}
		AnimalList aAdoption = new AnimalList(adoption);
		AnimalList aFound = new AnimalList(found);
		AnimalList aLost = new AnimalList(lost);
		return new AnimalShelter(aAdoption, aFound, aLost);
	}
	
	public List<Person> getAllPersons(){
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		ArrayList<Person> persons = new ArrayList<Person>();
		
		try {
			//Connecting to the database
			String query = "SELECT * FROM "+personsTable+";";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			
			//Manage the data we get
			while(result.next()){
				int id = result.getInt("pID");
				String name = result.getString("name");
				String adress = result.getString("adress");
				String phone = result.getString("phone");
				String email = result.getString("email");
				Person p = new Person(id,name,adress,phone,email);
				persons.add(p);
			}
		} catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
				if (result != null) {result.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return persons;
	}
	
	public List<Adoption> getAllAdoptionCategories(){
		Map<Integer, Person> pTable = new HashMap<Integer, Person>();
		for (Person p : this.getAllPersons()){
			int pID = p.getId();
			pTable.put(pID, p);
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		ArrayList<Adoption> adoption = new ArrayList<Adoption>();
		try {
			//Connecting to the database
			String query = "SELECT * FROM "+adoptionTable+";";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			
			//Manage the data we get
			while(result.next()){
				int id = result.getInt("aID");
				LocalDate date = result.getDate("date").toLocalDate();
				int pID = result.getInt("pID");
				boolean neutered = result.getBoolean("neutered");
				boolean chipped = result.getBoolean("chipped");
				boolean vaccinated = result.getBoolean("vaccinated");
				boolean reserved = result.getBoolean("reserved");
				boolean ready = result.getBoolean("ready");
				Person p = pTable.get(pID);
				Adoption a = new Adoption(id, date, p, neutered, chipped, vaccinated, reserved,
						ready);
				adoption.add(a);
			}
		} catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
				if (result != null) {result.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		
		return adoption;
	}
	
	public List<Animal> getAllAdoptionAnimals(){
		Map<Integer, Adoption> adoption = new HashMap<Integer, Adoption>();
		for (Adoption a : this.getAllAdoptionCategories()){
			adoption.put(a.getId(), a);
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Animal> animals = new ArrayList<Animal>();
		try {
			//Connecting to the database
			String query = "SELECT * FROM "+animalTable+" WHERE categoryLetter='a';";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			
			//Manage the data we get
			while(result.next()){
				int id = result.getInt("ID");
				int age = result.getInt("age");
				String color = result.getString("color");
				boolean gender = result.getBoolean("gender");
				String description = result.getString("description");
				String name = result.getString("name");
				String pathToPicture = result.getString("pathToPicture");
				String breed = result.getString("breed");
				String type = result.getString("type");
				int catID = result.getInt("catID");
				Adoption adopt = adoption.get(catID);
				Animal a = new Animal(id, age, color, gender, description, name, pathToPicture,
						breed, adopt, type);
				animals.add(a);
			}
		} catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
				if (result != null) {result.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return animals;
	}
	
	public List<Found> getAllFoundCategories() {
		Map<Integer, Person> pTable = new HashMap<Integer, Person>();
		for (Person p : this.getAllPersons()){
			int pID = p.getId();
			pTable.put(pID, p);
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		ArrayList<Found> found = new ArrayList<Found>();
		try {
			//Connecting to the database
			String query = "SELECT * FROM "+foundTable+";";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			
			//Manage the data we get
			while(result.next()){
				int id = result.getInt("fID");
				LocalDate date = result.getDate("date").toLocalDate();
				int pID = result.getInt("pID");
				String location = result.getString("location");
				Person p = pTable.get(pID);
				Found f = new Found(id, date, p, location);
				found.add(f);
			}
		} catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
				if (result != null) {result.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return found;
	}
	
	public List<Animal> getAllFoundAnimals(){
		Map<Integer, Found> found = new HashMap<Integer, Found>();
		for (Found f : this.getAllFoundCategories()){
			found.put(f.getId(), f);
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Animal> animals = new ArrayList<Animal>();
		try {
			//Connecting to the database
			String query = "SELECT * FROM "+animalTable+" WHERE categoryLetter='f';";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			
			//Manage the data we get
			while(result.next()){
				int id = result.getInt("ID");
				int age = result.getInt("age");
				String color = result.getString("color");
				boolean gender = result.getBoolean("gender");
				String description = result.getString("description");
				String name = result.getString("name");
				String pathToPicture = result.getString("pathToPicture");
				String breed = result.getString("breed");
				String type = result.getString("type");
				int catID = result.getInt("catID");
				Found foun = found.get(catID);
				Animal a = new Animal(id, age, color, gender, description, name, pathToPicture,
						breed, foun, type);
				animals.add(a);
			}
		} catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
				if (result != null) {result.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return animals;
	}
	
	public List<Lost> getAllLostCategories() {
		Map<Integer, Person> pTable = new HashMap<Integer, Person>();
		for (Person p : this.getAllPersons()){
			int pID = p.getId();
			pTable.put(pID, p);
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		ArrayList<Lost> lost = new ArrayList<Lost>();
		try {
			//Connecting to the database
			String query = "SELECT * FROM "+lostTable+";";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			
			//Manage the data we get
			while(result.next()){
				int id = result.getInt("lID");
				LocalDate date = result.getDate("date").toLocalDate();
				int pID = result.getInt("pID");
				String location = result.getString("location");
				Person p = pTable.get(pID);
				Lost l = new Lost(id, date, p, location);
				lost.add(l);
			}
		} catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
				if (result != null) {result.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return lost;
	}
	
	public List<Animal> getAllLostAnimals(){
		Map<Integer, Lost> lost = new HashMap<Integer, Lost>();
		for (Lost l : this.getAllLostCategories()){
			lost.put(l.getId(), l);
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Animal> animals = new ArrayList<Animal>();
		try {
			//Connecting to the database
			String query = "SELECT * FROM "+animalTable+" WHERE categoryLetter='l';";
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			
			//Manage the data we get
			while(result.next()){
				int id = result.getInt("ID");
				int age = result.getInt("age");
				String color = result.getString("color");
				boolean gender = result.getBoolean("gender");
				String description = result.getString("description");
				String name = result.getString("name");
				String pathToPicture = result.getString("pathToPicture");
				String breed = result.getString("breed");
				String type = result.getString("type");
				int catID = result.getInt("catID");
				Lost los = lost.get(catID);
				Animal a = new Animal(id, age, color, gender, description, name, pathToPicture,
						breed, los, type);
				animals.add(a);
			}
		} catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
				if (result != null) {result.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return animals;
	}
	
	public List<Animal> getAllAnimals(){
		List<Animal> animals = new ArrayList<Animal>();
		animals.addAll(this.getAllAdoptionAnimals());
		animals.addAll(this.getAllFoundAnimals());
		animals.addAll(	this.getAllLostAnimals());
		return animals;
	}
	
	public void add(Animal a){
		//Get animal details
		int id = a.getId();
		int age = a.getAge();
		String color = a.getColour();
		boolean gender = a.getGender();
		String description = a.getDescription();
		String name = a.getName();
		String pathToPicture = a.getPathToPicture();
		String breed = a.getBreed();
		String type = a.getType();
		
		//Get category details
		Category category = a.getAnimalCategory();
		char categoryLetter = category.getCategoryLetter();
		int catID = category.getId();
		LocalDate localDate = category.getDate();
		Date date = Date.valueOf(localDate);
		
		//Get person details
		Person person = category.getContact();
		int pID = person.getId();
		String pName = person.getName();
		String adress = person.getAdress();
		String phone = person.getPhone();
		String email = person.getEmail();
		
		//Construction of all the queries
		String animalQuery = "INSERT INTO "+animalTable+" VALUES (" + id + "," 
				+ age + ",\"" + color + "\"," + gender + ",\"" + description + "\",\"" + name 
				+ "\",\"" + pathToPicture + "\",\"" + breed + "\",\"" + categoryLetter + "\","
				+ catID + ",\"" + type + "\");";
		
		String categoryQuery = "INSERT INTO ";
		String secondPart = " VALUES (" + catID + ",\'" + date + "\'," + pID;
		switch (categoryLetter){
		case 'a':
			Adoption adoption = (Adoption) category;
			boolean neutered = adoption.isNeutered();
			boolean chipped = adoption.isChipped();
			boolean vaccinated = adoption.isVaccinated();
			boolean reserved = adoption.isReserved();
			boolean ready = adoption.isReady();
			categoryQuery += adoptionTable + secondPart + "," + neutered +"," + chipped + "," 
					+ vaccinated + "," + reserved + "," + ready + ");"; 
			break;
		case 'f':
			Found found = (Found) category;
			String fLocation = found.getLocation();
			categoryQuery += foundTable + secondPart + ",\"" + fLocation + "\");";
			break;
		case 'l':
			Lost lost = (Lost) category;
			String lLocation = lost.getLocation();
			categoryQuery += lostTable +secondPart + ",\"" + lLocation + "\");";
			break;
		}
		
		String personQuery = "INSERT INTO " + personsTable + " VALUES (" + pID + ",\"" +
				pName + "\",\"" + adress + "\",\"" + phone + "\",\"" + email + "\");";
		
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.execute(personQuery);
			statement.execute(categoryQuery);
			statement.execute(animalQuery);
			connection.setAutoCommit(true);
		}catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}
	
	public void remove(Animal a){
		int id = a.getId();
		Category cat = a.getAnimalCategory();
		int catID = cat.getId();
		char catLetter = cat.getCategoryLetter();
		
		String deleteAnimalQuery = "DELETE FROM " + animalTable + " WHERE ID=" + id + ";";
		String deleteCategoryQuery = "DELETE FROM ";
		String secondPart = " WHERE " + catLetter + "ID=" + catID +";";
		switch (catLetter){
		case 'a':
			deleteCategoryQuery += adoptionTable + secondPart;
			break;
		case 'f':
			deleteCategoryQuery += foundTable + secondPart;
			break;
		case 'l':
			deleteCategoryQuery += lostTable + secondPart;
			break;
		}
		
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			statement.execute(deleteCategoryQuery);
			statement.execute(deleteAnimalQuery);
			connection.setAutoCommit(true);
		}catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}
	
	public void updatePerson(Person p){
		int pID = p.getId();
		String name = p.getName();
		String address = p.getAdress();
		String phone = p.getPhone();
		String email = p.getEmail();
		
		String updateQuery = "UPDATE " + personsTable + " SET name=\""+ name + "\", adress=\""
				+ address + "\", phone=\"" + phone + "\", email=\"" + email + "\" WHERE pID="
				+ pID + ";";
		
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			statement.execute(updateQuery);
		}catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (statement != null) {statement.close();}
				if (connection != null) {connection.close();}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}
}

