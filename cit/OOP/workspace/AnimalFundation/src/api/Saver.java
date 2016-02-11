package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Saver {
	private File adoption;
	private File lost;
	private File found;
	private File persons;
	private File animals;

	public Saver(){
		adoption = new File("save/adoption");
		lost = new File("save/lost");
		found = new File("save/found");
		persons = new File("save/persons");
		animals = new File("save/animals");
	}

	public void save(AnimalShelter as){
		PrintWriter pw;
		
		// Create empty files in case
		// to restart the new save
		try {
			pw = new PrintWriter(animals);
			pw.close();
			pw = new PrintWriter(persons);
			pw.close();
			pw = new PrintWriter(found);
			pw.close();
			pw = new PrintWriter(lost);
			pw.close();
			pw = new PrintWriter(adoption);
			pw.close();
		}
		catch (IOException e) {
			System.out.println ("Error when trying to write : " 
					+ e.getMessage());
		}
		
		as.save(adoption, lost, found, animals, persons);
		System.out.println("Shelter save successfuly");
	}
	
	public AnimalShelter load(){
		String currentLine;
		BufferedReader br = null;
		Person person;
		List<Person> pList = new ArrayList<Person>();
		Animal animal;
		List<Category> founded = new ArrayList<Category>();
		List<Category> adopted = new ArrayList<Category>();
		List<Category> losted = new ArrayList<Category>();
		
		//Load all the persons
		try {
			br = new BufferedReader(new FileReader(persons));
			
			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");
				person = new Person(parts[0], parts[1], parts[2], parts[3]);
				pList.add(person);
			}
			System.out.println("Persons loaded successfuly");
		}
		catch (IOException e) {
			System.out.println ("Error when trying to read : " 
					+ e.getMessage());
		} finally {
			try {
				if (br != null) br.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			br = new BufferedReader(new FileReader(found));

			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");
				GregorianCalendar date = new GregorianCalendar(
						Integer.parseInt(parts[0]),	Integer.parseInt(parts[1]), 
						Integer.parseInt(parts[2]));
				Category f = new Found(date, parts[3]);
				founded.add(f);
			}
			System.out.println("Found category loaded successfuly");
		}
		catch (IOException e) {
			System.out.println ("Error when trying to read : " 
					+ e.getMessage());
		} finally {
			try {
				if (br != null) br.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
