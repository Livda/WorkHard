/**
 * 
 */
package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	private DataBaseManager(){
		this.url = "jdbc:mysql://localhost:3306/db";
		this.user="root";
		this.password="";
		this.personsTable = "persons";
	}
	
	public static DataBaseManager getDataBaseManager(){
		if (INSTANCE == null){
			INSTANCE = new DataBaseManager();
		}
		return INSTANCE;
	}
	
	public ArrayList<Person> getAllPersons(){
		ResultSet result = null;
		ArrayList<Person> persons = new ArrayList<Person>();
		
		try {
			String query = "SELECT * FROM "+personsTable+";";
			result = this.executeQuery(query);
			
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
				if (result != null) {
					result.close();
				}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return persons;
	}
	
	private ResultSet executeQuery(String query){
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			//Do SQL stuff
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
		} catch (SQLException ex){
			Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {				
				if (statement != null) {
					statement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex){
				Logger lgr = Logger.getLogger(DataBaseManager.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
		return result;
	}
}

