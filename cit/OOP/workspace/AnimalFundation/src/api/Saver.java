package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Hashtable;

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
			//New empty file
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
		BufferedReader br = null;
		String currentLine;
		Person person;
		Found f;
		Adoption a;
		Lost l;
		Animal animal;
		AnimalList adoptionList = new AnimalList();
		AnimalList lostList = new AnimalList();
		AnimalList foundList = new AnimalList();
		Map<Integer, Person> pTable = new Hashtable<Integer, Person>();
		Map<Integer, Found> fTable = new Hashtable<Integer, Found>();
		Map<Integer, Adoption> aTable = new Hashtable<Integer, Adoption>();
		Map<Integer, Lost> lTable = new Hashtable<Integer, Lost>();

		//Load all the persons
		try {
			br = new BufferedReader(new FileReader(persons));

			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");
				int id = Integer.parseInt(parts[0]);
				person = Person.load(parts);
				pTable.put(id, person);
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

		//Load all the founded Categories
		try {
			br = new BufferedReader(new FileReader(found));

			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");	
				int id = Integer.parseInt(parts[0]);
				f = Found.load(parts, pTable);
				fTable.put(id, f);
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

		//Load all the adopted Categories
		try {
			br = new BufferedReader(new FileReader(adoption));

			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");
				a = Adoption.load(parts, pTable);
				int id = Integer.parseInt(parts[0]);
				aTable.put(id, a);
			}
			System.out.println("Adoption category loaded successfuly");
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

		//Load all the lost Categories
		try {
			br = new BufferedReader(new FileReader(lost));

			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");
				l = Lost.load(parts, pTable);
				int id = Integer.parseInt(parts[0]);
				lTable.put(id, l);
			}
			System.out.println("Lost category loaded successfuly");
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
			br = new BufferedReader(new FileReader(animals));
			while ((currentLine = br.readLine()) != null) {
				String[] parts = currentLine.split(",");
				
				int idCategory = Integer.parseInt(parts[8]);
				Category cat = aTable.get(idCategory);
				if (cat != null){
					animal = Animal.load(parts, cat);
					adoptionList.add(animal);
				}
				cat = fTable.get(idCategory);
				if (cat != null) {
					animal = Animal.load(parts, cat);
					foundList.add(animal);
				}
				cat = lTable.get(idCategory);
				if (cat != null) {
					animal = Animal.load(parts, cat);
					lostList.add(animal);
				}
			}
			System.out.println("Animals loaded successfuly");
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
		
		AnimalShelter as = new AnimalShelter(adoptionList, lostList, foundList);
		System.out.println("Animal Shelter loaded sucessfuly");
		return as;
	}
}
