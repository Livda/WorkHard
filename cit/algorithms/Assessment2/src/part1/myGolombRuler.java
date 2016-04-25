package part1;
/**
 * 
 * @author Ignacio Castiñeiras
 *
 */
public class myGolombRuler {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private myStack<Integer> rulers;
	private myList<Boolean> differences;

	/**
	 * Create a <tt>myGolomRuler</tt> of the size given
	 * in parameter.
	 * @param n the size of the <tt>myGolomRuler</tt>.
	 */
	public myGolombRuler(int n){
		//1. Initialise the attributes
		this.rulers = new myStack_as_myList<Integer>();
		this.differences = new myListArrayList<Boolean>();

		//2. Get the upper bound
		int ub = 1;
		for (int i = 1; i < n; i++)
			ub = ub * 2;
		ub = ub - 1;

		//3. Fill the distances list
		for (int i = 0; i <= ub; i++)
			this.differences.my_add_element(0, false);

		//4. Add a first ruler to the stack
		this.rulers.my_push(0);
	}

	/**
	 * Constructs a list containing the elements of the specified
	 * <tt>myGolomRuler</tt>.
	 * @param g the <tt>myGolomRuler</tt> whose elements are to 
	 * 			be placed into this <tt>myGolomRuler</tt>.
	 */
	public myGolombRuler(myGolombRuler g){
		//1. Initialise the attributes
		this.rulers = new myStack_as_myList<Integer>();
		this.differences = new myListArrayList<Boolean>();

		//2. Copy the distances list from g	
		int s = g.differences.my_get_length();
		for (int i = 0; i < s; i++)
			this.differences.my_add_element(i, g.differences.my_get_element(i));

		//3. Copy the marks stack from g		

		//3.1. We use an auxiliary stack
		myStack<Integer> aux = new myStack_as_myList<Integer>();

		//3.2. We empty the stack, adding the elements in reverse order to aux
		while (g.rulers.my_is_empty() == false){
			//3.2.1. We get the top of the stack and remove it
			int elem = g.rulers.my_peek();
			g.rulers.my_pop();

			//3.2.2. We add it to aux
			aux.my_push(elem);
		}

		//3.3. We empty the stack aux, adding the elements in reverse again to g and to this
		while (aux.my_is_empty() == false){
			//3.3.1. We get the top of the stack and remove it
			int elem = aux.my_peek();
			aux.my_pop();

			//3.3.2. We add it to g.rulers and to this
			g.rulers.my_push(elem);
			this.rulers.my_push(elem);
		}
	}

	/**
	 * Check if the integer passed in parameter is a 
	 * successful candidate for this <tt>myGolomRuler</tt>. 
	 * @param m the integer checked
	 * @return <tt>true</tt> if it is a successful candidate
	 */
	public boolean successfulCandidate(int m){
		boolean res;
		//check if stack is empty
		if (!this.rulers.my_is_empty()){
			//peek and remove the top of the stack
			Integer top = this.rulers.my_peek();
			this.rulers.my_pop();

			//do my stuff
			//1. Check property 1
			boolean b1 = m > top;
			//2. Check property 2
			int diff = m - top;
			
			if (!b1 || this.differences.my_get_element(diff)) {
				res = false;
			} else {
				//recursion part
				res = this.successfulCandidate(m);
			}
			//rebuild my stack after the recursive call
			this.rulers.my_push(top);
			return res;
		} else {
			//if the stack is not empty
			return true;
		}
	}

	/**
	 * Update the difference list with the mode given for the mark given
	 * @param mark 
	 * @param mode
	 */
	public void updateDifferences(int mark, boolean mode){
		//check if stack is empty
		if (!this.rulers.my_is_empty()){
			//peek and remove the top of the stack
			Integer top = this.rulers.my_peek();
			this.rulers.my_pop();

			//do my stuff
			int diff =  Math.abs(mark - top);
			this.differences.my_remove_element(diff);
			this.differences.my_add_element(diff, mode);

			//recursion part
			this.updateDifferences(mark, mode);

			//rebuild my stack after the recursive call
			this.rulers.my_push(top);
		}
	}	

	/**
	 * Try to add a mark to this <tt>myGolomRuler</tt>.
	 * @param mark 
	 * @return <tt>true</tt> if the mark is added.
	 */
	public boolean addMark(int mark){
		boolean res = successfulCandidate(mark);

		if (res == true){
			this.updateDifferences(mark, true);;
			this.rulers.my_push(mark);
		}

		return res;
	}

	/**
	 * Try to remove a mark to this <tt>myGolomRuler</tt>.
	 * @param mark 
	 * @return <tt>true</tt> if the mark is remove.
	 */
	public boolean removeMark(){
		boolean res = !(this.rulers.my_is_empty());

		if (res == true){
			int mark = this.rulers.my_peek();
			this.rulers.my_pop();	
			updateDifferences(mark, false);
		}

		return res;
	}

	/**
	 * Get the last mark of this <tt>myGolomRuler</tt>. 
	 * @return the last mark
	 * @throws myException if the stack is empty
	 */
	public int getLastMark() throws myException{
		if (this.rulers.my_is_empty() == false)
			return this.rulers.my_peek();
		else
			throw new myException("Rulers is empty");			
	}

	/**
	 * Display this <tt>myGolomRuler</tt>.
	 */
	public void displayContent(){
		System.out.println("---RULER---");
		displayRulers();
		System.out.println();
		displayDifferences();
		System.out.println();
	}

	/**
	 * Display the ruler of this <tt>myGolomRuler</tt>.
	 */
	public void displayRulers(){
		//check if the stack is empty
		boolean empty = this.rulers.my_is_empty();
		if (!empty) {
			//peek and pop the top element
			int top = this.rulers.my_peek();
			this.rulers.my_pop();

			//Recursive part
			this.displayRulers();
			System.out.print(top + " ");

			//rebuild the stack
			this.rulers.my_push(top);
		}
	}

	/**
	 * Display the differences of this <tt>myGolomRuler</tt>.
	 */
	public void displayDifferences(){
		String res = "";
		int size = this.differences.my_get_length();
		for (int i = 0; i < size ; i++){
			res += i + ": " + this.differences.my_get_element(i) + "  ";
		}
		System.out.println(res);
	}
}
