
public class myMain_A02_Part1 {
			
	//-------------------------------------------------------------------
	// computeOptimalGolombRuler 
	//-------------------------------------------------------------------	
	public static void computeOptimalGolombRuler(int n){
		//1. We create the golomb solver
		myGolombSolver solver = new myGolombSolver(n);
		
		//2. We display the initial best solution
		System.out.println("------------------------------\nINITIAL SOLUTION\n------------------------------");
		myGolombRuler sol = solver.getBestSolution();
		sol.displayContent();
				
		//3. We explore the search space of current
		System.out.println("------------------------------\nSEARCH FOR OPTIMAL SOLUTION\n------------------------------");

		long startTime = System.currentTimeMillis();		
		int val = (int) Math.pow(2, (n-1)) - 1;
		
		solver.exploreSearchSpace(1, n, val);

		long time = System.currentTimeMillis() - startTime;		
		
		System.out.println("------------------------------\nSEARCH CONCLUDED IN "+(time/(1000 * 1.0))+" seconds");

		//4. We display the optimal solution		
		System.out.println("------------------------------\nOPTIMAL SOLUTION:\n------------------------------");
		sol = solver.getBestSolution();
		sol.displayContent();
	}		

	//-------------------------------------------------------------------
	// MAIN METHOD 
	//-------------------------------------------------------------------	
	public static void main(String[] args) {
		computeOptimalGolombRuler(4);
	}
		
}














