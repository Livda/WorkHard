
public class myGolombSolver {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private myGolombRuler bestSolution;
	private myGolombRuler current;
	
	//-------------------------------------------------------------------
	// Constructor
	//-------------------------------------------------------------------
	public myGolombSolver(int n){
		//1. Get the initial best solution using the upper bound
		this.bestSolution = new myGolombRuler(n);		
		int val = 1;
		for (int i = 1; i < n; i++){
			val = val * 2;
			bestSolution.addMark(val-1);
		}
		val = val - 1;

		//2. Create the initial status to start the search 
		this.current = new myGolombRuler(n);
	}
				
	/**
	 * Return the best solution of this <tt>myGolombSolver</tt>
	 * @return the best soltuion <tt>myGolombSolver</tt>
	 */
	public myGolombRuler getBestSolution(){
		return this.bestSolution;
	}
	
	/**
	 * This method tries exhaustively all possible combinations of 
	 * numbers (from 1 to ub) to be added
	 * as marks to current. By trying all possible combinations, it finds 
	 * all possible feasible Golomb rulers of size n.
	 * @param k the Golomb ruler hosted in current already contains k 
	 * marks (with [1 < k ≤ n])
	 * @param n size of the <tt>myGolombRuler</tt>
	 * @param ub the upper bound
	 */
	public void exploreSearchSpace(int k, int n, int ub){
		if (k < n) {
			for(int i = 1; i < ub ; i++) {
				boolean tryAdd = this.current.addMark(i);
				if (tryAdd) {
					exploreSearchSpace(k+1, n, ub);
					this.current.removeMark();
				}
			}
		} else {
			if (this.current.getLastMark() < this.bestSolution.getLastMark()) {
				this.bestSolution = new myGolombRuler(current);
			}
		}
	}
	
}
