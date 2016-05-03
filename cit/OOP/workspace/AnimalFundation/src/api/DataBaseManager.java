/**
 * 
 */
package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
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
	
	public List<Adoption> getAllAdoption(){
		Map<Integer, Person> pTable = new Hashtable<Integer, Person>();
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
	
	public List<Found> getAllFound() {
		Map<Integer, Person> pTable = new Hashtable<Integer, Person>();
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
	
	public List<Lost> getAllLost() {
		Map<Integer, Person> pTable = new Hashtable<Integer, Person>();
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
	
	public List<Animal> getAllAnimals(){
		Map<Integer, Adoption> aTable = new Hashtable<Integer, Adoption>();
		Map<Integer, Found> fTable = new Hashtable<Integer, Found>();
		Map<Integer, Lost> lTable = new Hashtable<Integer, Lost>();
		for (Adoption a : this.getAllAdoption()){
			int aID = a.getId();
			aTable.put(aID, a);
		}
		for (Found f : this.getAllFound()){
			int fID = f.getId();
			fTable.put(fID, f);
		}
		for (Lost l : this.getAllLost()){
			int lID = l.getId();
			lTable.put(lID, l);
		}
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		ArrayList<Animal> animal = new ArrayList<Animal>();
		try {
			//Connecting to the database
			String query = "SELECT * FROM "+animalTable+";";
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
				String categoryLetter = result.getString("categoryLetter");
				String type = result.getString("type");
				Category category = null;
				int catID = result.getInt("catID");
				switch (categoryLetter){
				case "a":
					category = aTable.get(catID);
					break;
				case "f":
					category = fTable.get(catID);
					break;
				case "l":
					category = lTable.get(catID);
					break;
				}
				Animal a = new Animal(id, age, color, gender, description, name, pathToPicture,
						breed, category, type);
				System.out.println(a);
				animal.add(a);
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
		return animal;
	}
	
	public void add(Animal a){
		Connection connection = null;
		Statement statement = null;
		try {
			int id = a.getId();
			int age = a.getAge();
			String color = a.getColour();
			boolean gender = a.getGender();
			String description = a.getDescription();
			String name = a.getName();
			String pathToPicture = a.getPathToPicture();
			String breed = a.getBreed();
			String type = a.getType();
			Category category = a.getAnimalCategory();
			char categoryLetter = category.getCategoryLetter();
			int catID = category.getId();
			
			String animalQuery = "INSERT INTO "+animalTable+" VALUES (" + id + "," + age + "," 
					+ color + "," + gender + "," + description + "," + name + ","
					+ pathToPicture + "," + breed + "," + categoryLetter + "," + catID
					+ "," + type;
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			statement.execute(animalQuery);
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

