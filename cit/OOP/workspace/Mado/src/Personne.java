/**
 Classe representant une personne associee a son numero de telephone.

 @author Departement informatique
 @version 16.0128
 */
public class Personne {
	
	private Mot nom;
	private Mot prenom;
	private int tel;
	
	public Personne(String nom, String prenom){
		this.nom= new Mot(nom);
		this.prenom= new Mot(prenom);
	}
	
	public Personne(String nom, String prenom, int tel){
		this.nom= new Mot(nom);
		this.prenom= new Mot(prenom);
		this.tel=tel;
	}

	/** Associe le numero de telephone. */
	public void setTel(int tel) {
		this.tel=tel;
	}

	/** Fournit le numero de telephone. */
	public int getTel() {
		return this.tel; 
	}
	
	/** Teste l'égalite du nom et du prenom */
	public boolean equals(Personne p){
		return(this.nom.equals(p.nom) && this.prenom.equals(p.prenom));
	}

	/**
     Produit une chaine indiquant la personne. 
     (Par exemple Jules CESAR)
	 */
	public String toString() {
		return (this.nom +" "+ this.prenom+" "+ this.tel);
	}

	/**
     Tests.
	 */
	public static void main (String[] args) {

		Personne toto = new Personne("Jean-Claude","Dusse");
		Personne titi = new Personne("JEAN-CLAUDE", "DuSsE");

		System.out.println(toto);
		System.out.println(toto.toString());

		toto.setTel(4321);
		System.out.println(toto.getTel());

		System.out.println(toto.equals(new Personne("Jean-Claude","DUSSE")));
		System.out.println(toto.equals(new Personne("Jean-Claude","Musse")));
		System.out.println(toto.equals(titi));
		//System.out.println(toto.estAvant(new Personne("Jean-Claude","Van Damme")));
	}
}