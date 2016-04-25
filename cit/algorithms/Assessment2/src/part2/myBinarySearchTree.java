package part2;

public interface myBinarySearchTree<T1 extends Comparable<? super T1>, T2> {

	//-------------------------------------------------------------------
	// 1. Basic Operation --> Create a non-empty myBinarySearchTree from a myBinarySearchNode: create_from_binary_search_node()
	//-------------------------------------------------------------------
	//public myBinarySearchTree create_from_binary_search_node(myBinarySearchNode<T> n); --> Java does not support constructors in interfaces

	//-------------------------------------------------------------------
	// 2. Basic Operation --> Check if myBinarySearchTree is empty: my_is_empty
	//-------------------------------------------------------------------
	public boolean isEmpty();
	
	//-------------------------------------------------------------------
	// 3. Basic Operation --> Get root of myBinarySearchTree: my_root
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> getRoot();
	
	//-------------------------------------------------------------------
	// 4. Basic Operation --> Get left tree of myBinarySearchTree: my_left_tree
	//-------------------------------------------------------------------
	public myBinarySearchTree<T1, T2> getLeftTree() throws myException;
		
	//-------------------------------------------------------------------
	// 5. Basic Operation --> Get left tree of myBinarySearchTree: my_right_tree
	//-------------------------------------------------------------------
	public myBinarySearchTree<T1, T2> getRightTree() throws myException;
	
	//-------------------------------------------------------------------
	// 6. Basic Operation --> Find element at myBinarySearchTree: my_find
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1,T2> find(T1 key);
		
	//-------------------------------------------------------------------
	// 7. Basic Operation --> Insert element at myBinarySearchTree: my_insert
	//-------------------------------------------------------------------	
	public myBinarySearchTree<T1, T2> insert(T1 key, T2 info);	

	//-------------------------------------------------------------------
	// 8. Basic Operation --> Remove element at myBinarySearchTree: my_remove
	//-------------------------------------------------------------------	
	public myBinarySearchTree<T1, T2> remove(T1 key);	
		
	//-------------------------------------------------------------------
	// 9. Additional Operation --> Check the max number of levels of myBinarySearchTree: my_length
	//-------------------------------------------------------------------
	public int length();
	
	//-------------------------------------------------------------------
	// 10. Additional Operation --> Count the amount of nodes of myBinarySearchTree: my_node_count
	//-------------------------------------------------------------------
	public int nodeCount();
	
	//-------------------------------------------------------------------
	// 11. Additional Operation --> Count the amount of leaf nodes of myBinarySearchTree: my_leaf_count
	//-------------------------------------------------------------------
	public int leafCount();

	//-------------------------------------------------------------------
	// 12. Traversal Operation --> Traverse myBinarySearchTree in in-order: my_inorder
	//-------------------------------------------------------------------
	public myList<T2> inorder();
	
	//-------------------------------------------------------------------
	// 13. Traversal Operation --> Traverse myBinarySearchTree in pre-order: my_preorder
	//-------------------------------------------------------------------
	public myList<T2> preorder();

	//-------------------------------------------------------------------
	// 14. Traversal Operation --> Traverse myBinarySearchTree in post-order: my_postorder
	//-------------------------------------------------------------------
	public myList<T2> postorder();
			
	//-------------------------------------------------------------------
	// 15. Additional Operation --> Get maximum element at myBinarySearchTree: my_maximum
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> maximum() throws myException;	

	//-------------------------------------------------------------------
	// 16. Additional Operation --> Get minimum element at myBinarySearchTree: my_minimum
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> minimum() throws myException;	

	//-------------------------------------------------------------------
	// 17. Assignment 2 - Operation 1 --> Count how many nodes are there at level k of myBinarySearchTree: my_count_at_level
	//-------------------------------------------------------------------	
	public int countAtLevel(int level);
	
	//-------------------------------------------------------------------
	// 18. Assignment 2 - Operation 2 --> Check if myBinarySearchTree is balanced: my_is_balanced
	//-------------------------------------------------------------------	
	public boolean isBalanced();
	
	//-------------------------------------------------------------------
	// 19. Assignment 2 - Operation 3 --> Count how many nodes are smaller in myBinarySearchTree: my_count_smaller_nodes
	//-------------------------------------------------------------------	
	public int countSmallerNodes(T1 key);
	
}
