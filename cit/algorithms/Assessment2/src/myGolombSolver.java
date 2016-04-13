
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
				
	//-------------------------------------------------------------------
	// getBestSolution
	//-------------------------------------------------------------------
	public myGolombRuler getBestSolution(){
		return this.bestSolution;
	}
	
	//-------------------------------------------------------------------
	// exploreSearchSpace
	//-------------------------------------------------------------------
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
