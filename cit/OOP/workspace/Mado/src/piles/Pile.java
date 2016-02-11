package piles;

public interface Pile {

	public Object depiler();
	
	public void empiler(Object o);
	
	public boolean pileVide();
	
	public Object sommetPile();
	
	public void viderPile();
	
	public String toString();
}
