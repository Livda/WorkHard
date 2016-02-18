package api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Aurélien Fontaine
 * @version 1.0
 */
public class Person {
	private int id;
	private String name;
	private String adress;
	private String phone;
	private String email;
	
	public Person(){
		id = -1;
		name = null;
		adress = null;
		phone = null;
		email = null;
	}
	
	public Person(int id, String name, String adress, String phone, String email){
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id + "," + name + "," + adress + "," + phone + "," + email;
	}

	public void print() {
		System.out.println(this.toString());
	}
	
	public void save(File persons) {
		BufferedWriter bw;
		try {
		bw = new BufferedWriter(new FileWriter(persons, true));
		bw.write(this.toString());
		bw.newLine();
		bw.flush();
		bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to save a person : " 
					+ e.getMessage());
		}
	}
	
	public static Person load(String[] s){
		int id = Integer.parseInt(s[0]);
		String name = s[1];
		String adress = s[2];
		String phone = s[3];
		String email = s[4];
		return new Person(id, name, adress, phone, email);
	}
}
