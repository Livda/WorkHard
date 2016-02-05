/**
 Classe representant un ensemble de personnes.
 
 @author Departement informatique
 @version 16.0128
 */

import java.util.*;

public class EnsembleP {
	
	// pourquoi protected ? Personne n'herite de cette classe ...
	 protected List<Personne> list;
	 private static final int CAPACITE=100;
	 protected int cardinal;
	 
	 public EnsembleP(){
	  this.list = new ArrayList<Personne>(); 
	  cardinal=0;
	 }
	 
	 public int card(){
		 return cardinal;
	 }
	 
	 public void ajouter(Personne p){
		 if(cardinal<CAPACITE){
			 list.add(p);
			 cardinal++;
		 }
		 else{
			 System.out.println("plus de place");
		 }
	  }
	 
	 public void oter(Personne p){
		 //on peut faire plus joli encore avec ce code :
		 /*
		  * if (!list.remove(p)) {
		  * 	System.out.println("La personne n'est pas dans la liste");
		  * }
		  */
		 //on sait que la fonction remove renvoie un booleen, autant s'en
		 //servir !
		 if(this.list.contains(p)){
			 list.remove(p);
		 }
		 else{
			 System.out.println("La personne n'est pas dans la liste");
		 }
	  }
	 
	 public boolean contient(Personne p){
		 return this.list.contains(p);
	 }
	 
	public EnsembleP union (EnsembleP e1, EnsembleP e2){
		EnsembleP e= new EnsembleP();
		//cette boucle est inutile.
		//mets juste la liste de e1 dans e
		for(Personne p : e1.list){
			if(!(e2.contient(p))){
				e.ajouter(p);
			}
		}
		for(Personne p : e2.list){
			//fais ton test sur e directement
			//c'est plus logique
			if(!(e1.contient(p))){
				e.ajouter(p);
			}
		}
		return e;
	 }
	 
	 public EnsembleP intersection (EnsembleP e1, EnsembleP e2){
		 EnsembleP e= new EnsembleP();
		 for(Personne p : e1.list){
			 if(e2.contient(p)){
				 e.ajouter(p);
			 }
		 }
		 return e;
	 }
	 
	 public String toString(){
	  return list.toString();
	 }
	 
	 public static void main (String[] args) {
		 Personne toto = new Personne("Jean-Claude","Dusse");
		 toto.setTel(987); //Pourquoi pas mettre de 0 devant?
		 Personne titi = new Personne("JEAN-CLAUDE", "DuSsE",456);
		 Personne Mado = new Personne("Mado","Poncet");
		 Personne Nassiba = new Personne("Nassiba","Benazzouz");
		 Personne Cory = new Personne("Cory","Dusse");
		 
		 List<Personne> list1 = new ArrayList<Personne>();
	     list1.add(toto);
	     list1.add(titi);
	     System.out.println(list1.toString());
	     
	     EnsembleP e1 = new EnsembleP();
	     e1.ajouter(Cory);
	     e1.ajouter(Mado);
	     e1.ajouter(toto);
	     EnsembleP e2 = new EnsembleP();
	     e2.ajouter(Nassiba);
	     e2.ajouter(titi);
	     e2.ajouter(toto);
	    System.out.println(new EnsembleP().intersection(e1, e2));
	    System.out.println(new EnsembleP().union(e1,  e2));
		  }
	
	}
	//Ajouter, modifier, supprimer
		
	
	// A PROGRAMMER SELON LE DIAGRAMME FOURNI

