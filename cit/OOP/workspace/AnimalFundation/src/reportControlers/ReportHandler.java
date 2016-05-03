/**
 *
 */
package reportControlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import api.Animal;
import api.Person;
import gui.MainWindow;
import gui.Messages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class ReportHandler implements EventHandler<ActionEvent> {
	public static String pathToPdfLatex = "/usr/bin/pdflatex";
	public static String pathToPdfReader = "/usr/bin/evince";
	private static File header = new File("report/header");
	private static File footer = new File("report/footer");
	private static File report = new File("report/report.tex");
	private Stage stage;

	public ReportHandler(Stage stage){
		this.stage = stage;
	}

	public void handle(ActionEvent event){
	}

	private void process(){
		try {
			Process process = new ProcessBuilder(
					pathToPdfLatex,
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

			process = new ProcessBuilder(pathToPdfReader, "report/report.pdf").start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.close();
	}

	private void fillPerson(){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(report, true));
			bw.write("\\part*{" + Messages.getString("persons") + "}");
			bw.newLine();
			bw.write("\\begin{center}");
			bw.newLine();
			bw.write("\\begin{tabular}{|c|p{5cm}|c|c|}");
			bw.newLine();
			bw.write("\\hline");
			bw.newLine();
			bw.write("\\textbf{" + Messages.getString("name") + "} & \\textbf{" +
					Messages.getString("adress") + "} & \\textbf{" + Messages.getString("phone")
					+ "} & " + "\\textbf{" + Messages.getString("email") + "} \\\\");
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
		List<Person> list = MainWindow.shelter.getAllPersons();
		Collections.sort(list);
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

	private void initialiseAnimalPart(){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(report, true));
			bw.write("\\part*{" + Messages.getString("animals") + "}");
			bw.newLine();
			bw.write("\\begin{center}");
			bw.newLine();
			bw.write("\\begin{tabular}{|c|c|p{5cm}|c|c|c|c|}");
			bw.newLine();
			bw.write("\\hline");
			bw.newLine();
			bw.write("\\textbf{" + Messages.getString("name") + "} & \\textbf{" +
					Messages.getString("age") + "} & \\textbf{"	+
					Messages.getString("description") + "} & \\textbf{" +
					Messages.getString("color") + "}& \\textbf{" + Messages.getString("breed") +
					"} & \\textbf{" + Messages.getString("type") + "} & \\textbf{" +
					Messages.getString("category") + "} \\\\");
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
	}

	private void endAnimalPart() {
		BufferedWriter bw;
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

	public void fillAnimalByList(List<Animal> list){
		initialise();
		initialiseAnimalPart();
		for (Animal a : list) {
			a.fillReport(report);
		}
		endAnimalPart();
		finish();
		process();
	}

	public void fillAllPerson(){
		initialise();
		fillPerson();
		finish();
		process();
	}
}
