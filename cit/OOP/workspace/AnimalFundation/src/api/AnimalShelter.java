package api;

public class AnimalShelter {
	private AnimalList adoption;
	private AnimalList lost;
	private AnimalList found;
	/**
	 * @return the adoption
	 */
	public AnimalList getAdoption() {
		return adoption;
	}
	/**
	 * @param adoption the adoption to set
	 */
	public void setAdoption(AnimalList adoption) {
		this.adoption = adoption;
	}
	/**
	 * @return the lost
	 */
	public AnimalList getLost() {
		return lost;
	}
	/**
	 * @param lost the lost to set
	 */
	public void setLost(AnimalList lost) {
		this.lost = lost;
	}
	/**
	 * @return the found
	 */
	public AnimalList getFound() {
		return found;
	}
	/**
	 * @param found the found to set
	 */
	public void setFound(AnimalList found) {
		this.found = found;
	}
	
	public AnimalShelter() {
		this.adoption = this.lost = this.found = null;
	}
	
	/**
	 * @param adoption
	 * @param lost
	 * @param found
	 */
	public AnimalShelter(AnimalList adoption, AnimalList lost, AnimalList found) {
		this.adoption = adoption;
		this.lost = lost;
		this.found = found;
	}
	
	
}
