package piles;

import java.util.*;

public class MaPile{
	
	private static final int CAPACITE=100;
	public List<Object> list;
	protected int cardinal;
	
	public MaPile(){
		this.list = new ArrayList<Object>();
		cardinal=0;
	}
	
	public Object depiler(){
		Object o = list.get(0);
		// Pour moi, l� je place dans la variable o l'�l�ment de 
		//ma liste � la position 0
		if(list.contains(o)){
		list.remove(0); // Puis je le supprime de la liste 
		cardinal -= 1;
		return o; //mais vu que j'ai ma variable, je peux le retourner
		}
		return null;
	}
	
	public void empiler(Object o) {
		if(list.contains(o)){
		list.remove(o);
		cardinal -= cardinal;
		} // j'ai fait �a au cas o� l'object est d�j� dans la liste 
		//et vu qu'il ne faut pas le copier, 
	    //je le supprime puis je l'ajoute l� o� je veux
		if(cardinal<CAPACITE){  // �vite les d�bordements
		list.add(0,o);// j'ajoute le terme en param�tre en premi�re position de ma liste
		cardinal += cardinal;
		}
	}
	
	public boolean pileVide(){
	return list.isEmpty(); // �a retourne vrai si je n'ai aucun �l�ment dans ma liste
	}
	
	public Object sommetPile(){
	return list.get(0);
	}

	public void viderPile(){
	list.clear();
	cardinal=0;
	}
	
	public String toString(){
	return list.toString();
	}
	
	 public static void main (String[] args) {
	 }
	 
}

// A PROGRAMMER
