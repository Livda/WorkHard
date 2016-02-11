package api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Person {
	private String name;
	private String adress;
	private String phone;
	private String email;
	
	public Person(){
		name = null;
		adress = null;
		phone = null;
		email = null;
	}
	
	public Person(String name, String adress, String phone, String email){
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
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
		return name + "," + adress + "," + phone + "," + email;
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
}
