/**
 Classe representant un mot.
 
 @author Departement informatique
 @version 16.0128
 */
class Mot {
	
	private String txt;

	/** Constructeur */
	public Mot(String texte){
		this.txt=texte;
	}
	
	/** Teste l'egalite, insensible a la casse */
	public boolean equals(Mot mot){
		return(this.txt.toLowerCase().equals(mot.txt.toLowerCase()));
	}

	/** Retourne une version String du mot */
	public String toString() {
        return txt; 
	}

	/**
    Tests.
	 */
	public static void main(String[] args) {
		Mot y = new Mot("Yann");
		Mot x = new Mot("Mado");
		Mot z = new Mot("Yann");
		System.out.println(x.toString());
		System.out.println(y.equals(z));
		System.out.println("egal a YANN ? " + y.equals(new Mot("YANN")));
        
	}
}
