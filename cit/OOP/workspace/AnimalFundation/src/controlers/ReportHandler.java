/**
 * 
 */
package controlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import api.Animal;
import api.Person;
import gui.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ReportHandler implements EventHandler<ActionEvent> {
	private static File header = new File("report/header");
	private static File footer = new File("report/footer");
	private static File report = new File("report/report.tex");

	public void handle(ActionEvent event){
		try {
			this.initialise();
			this.fillAnimal();
			this.fillPerson();
			this.finish();
			Process process = new ProcessBuilder(
					"pdflatex", 
					"-output-directory=report",
					"report/report.tex").start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
			
			process = new ProcessBuilder("evince", "report/report.pdf").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fillPerson(){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(report, true));
			bw.write("\\part{Persons}");
			bw.newLine();
			bw.write("\\begin{center}");
			bw.newLine();
			bw.write("\\begin{tabular}{|c|p{5cm}|c|c|}");
			bw.newLine();
			bw.write("\\hline");
			bw.newLine();
			bw.write("\\textbf{Name} & \\textbf{Adress} & \\textbf{Phone} & "
					+ "\\textbf{Email} \\\\");
			bw.newLine();
			bw.write("\\hline");
			bw.newLine();
			bw.flush();
			bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to initialise the person part : " 
					+ e.getMessage());
		}
		ArrayList<Person> list = MainWindow.shelter.getAllPersons();
		for(Person p : list){
			p.fillReport(report);
		}
		try {
			bw = new BufferedWriter(new FileWriter(report, true));
			bw.write("\\end{tabular}");
			bw.newLine();
			bw.write("\\end{center}");
			bw.newLine();
			bw.flush();
			bw.close();
		}
		catch (IOException e){
				System.out.println("Error when trying to finish the person part : " 
						+ e.getMessage());
		}
	}

	private void fillAnimal(){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(report, true));
			bw.write("\\part{Animals}");
			bw.newLine();
			bw.write("\\begin{center}");
			bw.newLine();
			bw.write("\\begin{tabular}{|c|c|p{5cm}|c|c|c|c|}");
			bw.newLine();
			bw.write("\\hline");
			bw.newLine();
			bw.write("\\textbf{Name} & \\textbf{Age} & \\textbf{Description} & \\textbf{Colour}"
					+ "& \\textbf{Breed} & \\textbf{Type} & \\textbf{Category} \\\\");
			bw.newLine();
			bw.write("\\hline");
			bw.newLine();
			bw.flush();
			bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to initialise the animal part : " 
					+ e.getMessage());
		}
		ArrayList<Animal> list = MainWindow.shelter.getAllAnimals();
		for(Animal a : list){
			a.fillReport(report);
		}
		try {
			bw = new BufferedWriter(new FileWriter(report, true));
			bw.write("\\end{tabular}");
			bw.newLine();
			bw.write("\\end{center}");
			bw.newLine();
			bw.flush();
			bw.close();
		}
		catch (IOException e){
				System.out.println("Error when trying to finish the animal part : " 
						+ e.getMessage());
		}
	}

	private void initialise(){
		PrintWriter pw;
		try {
			pw = new PrintWriter(report);
			pw.close();
		}
		catch (IOException e) {
			System.out.println ("Error when trying to write : " 
					+ e.getMessage());
		}

		BufferedReader br;
		BufferedWriter bw;
		String currentLine;
		try {
			br = new BufferedReader(new FileReader(header));
			bw = new BufferedWriter(new FileWriter(report, true));
			while ((currentLine = br.readLine()) != null) {
				bw.write(currentLine);
				bw.newLine();
				bw.flush();				
			}
			bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to initialise the report file : " 
					+ e.getMessage());
		}
	}
	
	private void finish(){
		BufferedReader br;
		BufferedWriter bw;
		String currentLine;
		try {
			br = new BufferedReader(new FileReader(footer));
			bw = new BufferedWriter(new FileWriter(report, true));
			while ((currentLine = br.readLine()) != null) {
				bw.write(currentLine);
				bw.newLine();
				bw.flush();				
			}
			bw.close();
		}
		catch (IOException e){
			System.out.println("Error when trying to finish the report file : " 
					+ e.getMessage());
		}
	}
}
