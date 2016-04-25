package part2;

public class myMain_A02_Part2 {
			

	//-------------------------------------------------------------------
	// initialise_tree 
	//-------------------------------------------------------------------	
	public static void initialise_tree(myBinarySearchTree<Integer, String> t, boolean mode){
		t.my_insert(8, "A");
		
		t.my_insert(5, "B");
		
		t.my_insert(2, "C");

		t.my_insert(1, "D");
		
		t.my_insert(3, "E");
				
		t.my_insert(7, "G");
		
		t.my_insert(6, "H");
		
		t.my_insert(11, "I");
		
		t.my_insert(10, "J");
		
		if (mode == false)
			t.my_insert(9, "K");
	}
	
	//-------------------------------------------------------------------
	// MAIN METHOD 
	//-------------------------------------------------------------------	
	public static void main(String[] args) {
		myBinarySearchTree<Integer, String> myTree1 = new myBinarySearchTreeImpl<Integer, String>(null);
		myBinarySearchTree<Integer, String> myTree2 = new myBinarySearchTreeImpl<Integer, String>(null);
		
		initialise_tree(myTree1, true);
		initialise_tree(myTree2, false);
		
		System.out.println("Nodes of myTree1 at level 1? " + myTree1.my_count_at_level(1));		
		System.out.println("Nodes of myTree1 at level 2? " + myTree1.my_count_at_level(2));
		System.out.println("Nodes of myTree1 at level 3? " + myTree1.my_count_at_level(3));
		System.out.println("Nodes of myTree1 at level 4? " + myTree1.my_count_at_level(4));
		System.out.println("Nodes of myTree2 at level 4? " + myTree2.my_count_at_level(4));		
		System.out.println("Nodes of myTree1 at level 5? " + myTree1.my_count_at_level(5));
		
		System.out.println("Is myTree1 balanced? " + myTree1.my_is_balanced());
		System.out.println("Is myTree2 balanced? " + myTree2.my_is_balanced());		
		
		System.out.println("How many nodes on myTree1 are smaller than 1? " + myTree1.my_count_smaller_nodes(1));		
		System.out.println("How many nodes on myTree1 are smaller than 5? " + myTree1.my_count_smaller_nodes(5));
		System.out.println("How many nodes on myTree1 are smaller than 11? " + myTree1.my_count_smaller_nodes(11));		
	}
		
}














