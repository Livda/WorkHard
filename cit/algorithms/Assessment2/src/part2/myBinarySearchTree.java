package part2;

public interface myBinarySearchTree<T1 extends Comparable<? super T1>, T2> {

	//-------------------------------------------------------------------
	// 1. Basic Operation --> Create a non-empty myBinarySearchTree from a myBinarySearchNode: create_from_binary_search_node()
	//-------------------------------------------------------------------
	//public myBinarySearchTree create_from_binary_search_node(myBinarySearchNode<T> n); --> Java does not support constructors in interfaces

	//-------------------------------------------------------------------
	// 2. Basic Operation --> Check if myBinarySearchTree is empty: my_is_empty
	//-------------------------------------------------------------------
	public boolean my_is_empty();
	
	//-------------------------------------------------------------------
	// 3. Basic Operation --> Get root of myBinarySearchTree: my_root
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> my_root();
	
	//-------------------------------------------------------------------
	// 4. Basic Operation --> Get left tree of myBinarySearchTree: my_left_tree
	//-------------------------------------------------------------------
	public myBinarySearchTree<T1, T2> my_left_tree() throws myException;
		
	//-------------------------------------------------------------------
	// 5. Basic Operation --> Get left tree of myBinarySearchTree: my_right_tree
	//-------------------------------------------------------------------
	public myBinarySearchTree<T1, T2> my_right_tree() throws myException;
	
	//-------------------------------------------------------------------
	// 6. Basic Operation --> Find element at myBinarySearchTree: my_find
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1,T2> my_find(T1 key);
		
	//-------------------------------------------------------------------
	// 7. Basic Operation --> Insert element at myBinarySearchTree: my_insert
	//-------------------------------------------------------------------	
	public myBinarySearchTree<T1, T2> my_insert(T1 key, T2 info);	

	//-------------------------------------------------------------------
	// 8. Basic Operation --> Remove element at myBinarySearchTree: my_remove
	//-------------------------------------------------------------------	
	public myBinarySearchTree<T1, T2> my_remove(T1 key);	
		
	//-------------------------------------------------------------------
	// 9. Additional Operation --> Check the max number of levels of myBinarySearchTree: my_length
	//-------------------------------------------------------------------
	public int my_length();
	
	//-------------------------------------------------------------------
	// 10. Additional Operation --> Count the amount of nodes of myBinarySearchTree: my_node_count
	//-------------------------------------------------------------------
	public int my_node_count();
	
	//-------------------------------------------------------------------
	// 11. Additional Operation --> Count the amount of leaf nodes of myBinarySearchTree: my_leaf_count
	//-------------------------------------------------------------------
	public int my_leaf_count();

	//-------------------------------------------------------------------
	// 12. Traversal Operation --> Traverse myBinarySearchTree in in-order: my_inorder
	//-------------------------------------------------------------------
	public myList<T2> my_inorder();
	
	//-------------------------------------------------------------------
	// 13. Traversal Operation --> Traverse myBinarySearchTree in pre-order: my_preorder
	//-------------------------------------------------------------------
	public myList<T2> my_preorder();

	//-------------------------------------------------------------------
	// 14. Traversal Operation --> Traverse myBinarySearchTree in post-order: my_postorder
	//-------------------------------------------------------------------
	public myList<T2> my_postorder();
			
	//-------------------------------------------------------------------
	// 15. Additional Operation --> Get maximum element at myBinarySearchTree: my_maximum
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> my_maximum() throws myException;	

	//-------------------------------------------------------------------
	// 16. Additional Operation --> Get minimum element at myBinarySearchTree: my_minimum
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> my_minimum() throws myException;	

	//-------------------------------------------------------------------
	// 17. Assignment 2 - Operation 1 --> Count how many nodes are there at level k of myBinarySearchTree: my_count_at_level
	//-------------------------------------------------------------------	
	public int my_count_at_level(int level);
	
	//-------------------------------------------------------------------
	// 18. Assignment 2 - Operation 2 --> Check if myBinarySearchTree is balanced: my_is_balanced
	//-------------------------------------------------------------------	
	public boolean my_is_balanced();
	
	//-------------------------------------------------------------------
	// 19. Assignment 2 - Operation 3 --> Count how many nodes are smaller in myBinarySearchTree: my_count_smaller_nodes
	//-------------------------------------------------------------------	
	public int my_count_smaller_nodes(T1 key);
	
}
