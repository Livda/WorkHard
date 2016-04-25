package part2;

public class myBinarySearchTreeImpl<T1 extends Comparable<? super T1>, T2> implements myBinarySearchTree<T1, T2> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	protected myBinarySearchNode<T1, T2> root;
	
	//-------------------------------------------------------------------
	// 1. Basic Operation --> Create a non-empty myBinarySearchTree from a myBinarySearchNode: create_from_binary_search_node()
	//-------------------------------------------------------------------
	//public myBinarySearchTree create_from_element_and_trees(myBinarySearchNode<T> n); --> Java does not support constructors in interfaces

	public myBinarySearchTreeImpl(myBinarySearchNode<T1, T2> node) throws myException{
		if (node == null)
			this.root = null;
		else
			this.root = node;	

		//2. However, there is a detail. Now we have to check that the tree we are constructing is ordered as well.
		if (this.root != null){
			//2.1. If the left subtree is not empty
			if (this.my_left_tree().my_is_empty() == false){
				//2.1.1. We get the maximum element from the left subtree
				myBinarySearchNode<T1, T2> newNode = this.my_left_tree().my_maximum();
				
				//2.1.2. We compare the root element to it, to ensure it is bigger
				if ((newNode.getKey()).compareTo(this.my_root().getKey()) >= 0)
					throw new myException("The tree being constructed is not ordered, as the root is smaller than the elements of its left subtree");
			}

			//2.2. If the right subtree is not empty
			if (this.my_right_tree().my_is_empty() == false){
				//2.2.1. We get the minimum element from the right subtree
				myBinarySearchNode<T1, T2> newNode = this.my_right_tree().my_minimum();
				
				//2.2.2. We compare the root element to it, to ensure it is smaller
				if ((newNode.getKey()).compareTo(this.my_root().getKey()) <= 0)
					throw new myException("The tree being constructed is not ordered, as the root is bigger than the elements of its right subtree");
			}

		}
		
	}

	//-------------------------------------------------------------------
	// 2. Basic Operation --> Check if myBinarySearchTree is empty: my_is_empty
	//-------------------------------------------------------------------
	public boolean my_is_empty(){
		return (this.root == null);
	}	
		
	//-------------------------------------------------------------------
	// 3. Basic Operation --> Get root of myBinarySearchTree: my_root
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> my_root() {
		return this.root;
	}
	
	//-------------------------------------------------------------------
	// 4. Basic Operation --> Get left tree of myBinarySearchTree: my_left_tree
	//-------------------------------------------------------------------
	public myBinarySearchTree<T1, T2> my_left_tree() throws myException{
		if (this.root != null){
			myBinarySearchTree<T1, T2> res = new myBinarySearchTreeImpl<T1, T2>(this.root.getLeft());
			return res;
		}
		else
			throw new myException("The tree is empty, so there is no left subtree");			
	}

	//-------------------------------------------------------------------
	// 5. Basic Operation --> Get left tree of myBinarySearchTree: my_right_tree
	//-------------------------------------------------------------------
	public myBinarySearchTree<T1, T2> my_right_tree() throws myException{
		if (this.root != null){
			myBinarySearchTree<T1, T2> res = new myBinarySearchTreeImpl<T1, T2>(this.root.getRight());
			return res;
		}
		else
			throw new myException("The tree is empty, so there is no left subtree");			
	}

	//-------------------------------------------------------------------
	// 6. Basic Operation --> Find element at myBinarySearchTree: my_find
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1,T2> my_find(T1 key){
		myBinarySearchNode<T1, T2> res;
		
		//1.1 If the tree is empty, then the element is not found 
		if (this.my_is_empty() == true)
			res = null;
		//1.2. If it is not empty
		else{
			//1.2.1. We compare the element to the root of the tree
			int k = key.compareTo(this.my_root().getKey()); 
			//Case A: If they are equal, is because we have found the node
			if (k == 0)
				res = this.my_root();
			//Otherwise, we have to keep looking
			else
				//Case B: If the element is smaller than the root, we keep looking in the left subtree.
				if (k < 0)
					res = this.my_left_tree().my_find(key);
				//Case C: If the element is bigger than the root, we keep looking in the right subtree.
				else
					res = this.my_right_tree().my_find(key);
		}
		
		//2. We return res
		return res;				
	}
	
	//-------------------------------------------------------------------
	// 7. Basic Operation --> Insert element at myBinarySearchTree: my_insert
	//-------------------------------------------------------------------	
	public myBinarySearchTree<T1, T2> my_insert(T1 key, T2 info){
		myBinarySearchTree<T1, T2> res;
		
		//1.1 If the tree is empty, then we insert the element by creating a new tree just containing this element 
		if (this.my_is_empty() == true){
			//1.1.1. We create a new node with the element 
			myBinarySearchNode<T1, T2> node = new myBinarySearchNode<T1, T2>(key, info);
			
			//1.1.2. We link the root of the current tree to the new node just created
			this.root = node;
		}
		//1.2. If the tree is not empty
		else{
			//1.2.1. We compare the element to the root of the tree
			int k = key.compareTo(this.my_root().getKey()); 
			//Case A: If they are equal, we do nothing as the node is already in the three
			if (k == 0){
				this.my_root().setInfo(info);
			}
			//Otherwise, we have to keep looking
			else{
				//Case B: If the element is smaller than the root, we insert the node in the left subtree.
				if (k < 0){
					myBinarySearchTree<T1, T2> myTree1 = this.my_left_tree().my_insert(key, info);
					this.root.setLeft(myTree1.my_root()); 
				}
				//Case C: If the element is bigger than the root, we insert the node in the right subtree.
				else{
					myBinarySearchTree<T1, T2> myTree1 = this.my_right_tree().my_insert(key, info);
					this.root.setRight(myTree1.my_root()); 
				}
			}
		}
		
		//2 We make res to point to the root of the tree and we return it
		res = new myBinarySearchTreeImpl<T1, T2>(this.my_root()); 			
		return res;				
	}

	//-------------------------------------------------------------------
	// 8. Basic Operation --> Remove element at myBinarySearchTree: my_remove
	//-------------------------------------------------------------------	
	public myBinarySearchTree<T1, T2> my_remove(T1 key){
		myBinarySearchTree<T1, T2> res;
		
		//1. If the tree is non-empty
		if (this.my_is_empty() == false){
			//1.1. We compare the element to the root of the tree
			int k = key.compareTo(this.my_root().getKey()); 
			
			//Case A: If they are equal, is because we have found the node
			if (k == 0){
				boolean b1 = this.my_left_tree().my_is_empty();
				boolean b2 = this.my_right_tree().my_is_empty();				
				
				//Case A1: If both subtrees are non-empty: We create a new tree with minimum of right subtree as root. Also, we remove the minimum of right subtree from the proper right subtree
				if ((b1 == false) && (b2 == false)){
					//A1.1. We get the minimum from the right subtree
					myBinarySearchNode<T1, T2> min = this.my_right_tree().my_minimum();
					
					//A1.2. We create our new tree 
					myBinarySearchNode<T1, T2> node = new myBinarySearchNode<T1, T2>(min.getKey(), min.getInfo());
					node.setLeft(this.my_left_tree().my_root());					
					myBinarySearchTree<T1, T2> myNewRightSubtree = this.my_right_tree().my_remove(min.getKey());					
					node.setRight(myNewRightSubtree.my_root());					

					//A1.4. We link the root of the current tree to the root of the new tree
					this.root = node;										
				}
				//Case A2: If left subtree is empty, the resulting tree will be the right subtree
				if ((b1 == true) && (b2 == false))
					this.root = this.my_right_tree().my_root();					
				//Case A3: If right subtree is empty, the resulting tree will be the left subtree				
				if ((b1 == false) && (b2 == true))
					this.root = this.my_left_tree().my_root();					
				//Case A4: If both subtrees are empty, the resulting tree will be an empty tree		
				if ((b1 == true) && (b2 == true))
					this.root = null;
			}
			//Otherwise, we have to keep removing it in the subtrees
			else{
				//Case B: If the element is smaller than the root, we remove the node in the left subtree.
				if (k < 0){
					myBinarySearchTree<T1, T2> myTree1 = this.my_left_tree().my_remove(key);
					this.root.setLeft(myTree1.my_root());
				}
				//Case C: If the element is bigger than the root, we remove the node in the right subtree.
				else{
					myBinarySearchTree<T1, T2> myTree1 = this.my_right_tree().my_remove(key);
					this.root.setRight(myTree1.my_root());
				}
			}
		}
		
		//2 We make res to point to the root of the tree and we return it
		res = new myBinarySearchTreeImpl<T1, T2>(this.my_root()); 			
		return res;				
	}
		
	//-------------------------------------------------------------------
	// 9. Additional Operation --> Check the max number of levels of myBinarySearchTree: my_length
	//-------------------------------------------------------------------
	public int my_length(){
		int num;
		
		if (this.my_is_empty() == true)
			num = 0;
		else{
			int l1 = this.my_left_tree().my_length();
			int l2 = this.my_right_tree().my_length();
			num = Math.max(l1,l2) + 1;
		}
		
		return num;
	}

	//-------------------------------------------------------------------
	// 10. Additional Operation --> Count the amount of nodes of myBinarySearchTree: my_node_count
	//-------------------------------------------------------------------
	public int my_node_count(){
		int num;
		
		if (this.my_is_empty() == true)
			num = 0;
		else{
			int c1 = this.my_left_tree().my_node_count();
			int c2 = this.my_right_tree().my_node_count();
			num = c1 + c2 + 1;
		}
		
		return num;	
	}

	//-------------------------------------------------------------------
	// 11. Additional Operation --> Count the amount of leaf nodes of myBinarySearchTree: my_leaf_count
	//-------------------------------------------------------------------
	public int my_leaf_count(){
		int num;
		
		if (this.my_is_empty() == true)
			num = 0;
		else{
			if ((this.my_left_tree().my_is_empty() == true) && (this.my_right_tree().my_is_empty() == true))
				num = 1;
			else{
				int l1 = this.my_left_tree().my_leaf_count();
				int l2 = this.my_right_tree().my_leaf_count();
				num = l1 + l2;
			}
		}
		
		return num;	
	}
	
	//-------------------------------------------------------------------
	// 12. Traversal Operation --> Traverse myBinarySearchTree in in-order: my_inorder
	//-------------------------------------------------------------------
	public myList<T2> my_inorder(){
		//1. We create the empty list
		myList<T2> list = new myListArrayList<T2>();
		
		//2. If the root node is not empty
		if (this.my_is_empty() == false){
			//2.1. We create the list of traversing the left and right sub-trees
			myList<T2> list1 = this.my_left_tree().my_inorder();
			myList<T2> list2 = this.my_right_tree().my_inorder();	
	
			//2.2. We add the elements of list1 to list
			int k1 = list1.my_get_length();
			for (int i = 0; i < k1; i++)
				list.my_add_element(i, list1.my_get_element(i));
			
			//2.3. We add the root element to list
			list.my_add_element(k1, this.my_root().getInfo());
			
			//2.4. We add the elements of list2 to list
			int k2 = list2.my_get_length();			
			for (int i = 0; i < k2; i++)
				list.my_add_element((k1+1+i), list2.my_get_element(i));						
		}
		
		//3. We return the list
		return list;	
	}
	
	//-------------------------------------------------------------------
	// 13. Traversal Operation --> Traverse myBinarySearchTree in pre-order: my_preorder
	//-------------------------------------------------------------------
	public myList<T2> my_preorder(){
		//1. We create the empty list
		myList<T2> list = new myListArrayList<T2>();
		
		//2. If the root node is not empty
		if (this.my_is_empty() == false){
			//2.1. We create the list of traversing the left and right sub-trees
			myList<T2> list1 = this.my_left_tree().my_preorder();
			myList<T2> list2 = this.my_right_tree().my_preorder();	
	
			//2.2. We add the root element to list
			list.my_add_element(0, this.my_root().getInfo());

			//2.3. We add the elements of list1 to list
			int k1 = list1.my_get_length();
			for (int i = 0; i < k1; i++)
				list.my_add_element(i+1, list1.my_get_element(i));
			
			//2.4. We add the elements of list2 to list
			int k2 = list2.my_get_length();			
			for (int i = 0; i < k2; i++)
				list.my_add_element((k1+1+i), list2.my_get_element(i));						
		}
		
		//3. We return the list
		return list;	
	}

	//-------------------------------------------------------------------
	// 14. Traversal Operation --> Traverse myBinarySearchTree in post-order: my_postorder
	//-------------------------------------------------------------------
	public myList<T2> my_postorder(){
		//1. We create the empty list
		myList<T2> list = new myListArrayList<T2>();
		
		//2. If the root node is not empty
		if (this.my_is_empty() == false){
			//2.1. We create the list of traversing the left and right sub-trees
			myList<T2> list1 = this.my_left_tree().my_postorder();
			myList<T2> list2 = this.my_right_tree().my_postorder();	
	
			//2.2. We add the elements of list1 to list
			int k1 = list1.my_get_length();
			for (int i = 0; i < k1; i++)
				list.my_add_element(i, list1.my_get_element(i));
			
			//2.3. We add the elements of list2 to list
			int k2 = list2.my_get_length();			
			for (int i = 0; i < k2; i++)
				list.my_add_element((k1+i), list2.my_get_element(i));						

			//2.4. We add the root element to list
			list.my_add_element(k1+k2, this.my_root().getInfo());
		}
		
		//3. We return the list
		return list;	
	}
	
	//-------------------------------------------------------------------
	// 15. Additional Operation --> Get maximum element at myBinarySearchTree: my_maximum
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> my_maximum() throws myException{
		myBinarySearchNode<T1, T2> res;
		
		//1.1 If the tree is empty, then there is no minimum, so an exception is to be raised 
		if (this.my_is_empty() == true)
			throw new myException("The tree is empty, so it has no maximum");
		//1.2. If it is not empty
		else{
			//1.2.1. We get the right subtree
			myBinarySearchTree<T1, T2> r = this.my_right_tree();
			
			//1.2.1. If the right tree is empty, then the maximum is the root node
			if (r.my_is_empty() == true)
				res = this.my_root();
			//1.2.2. If the right tree is non-empty, we keep looking for the maximum in the right subtree.
			else
				res = r.my_maximum();
		}				
		
		//2. We return res
		return res;				
	}

	//-------------------------------------------------------------------
	// 16. Additional Operation --> Get minimum element at myBinarySearchTree: my_minimum
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> my_minimum() throws myException{
		myBinarySearchNode<T1, T2> res;
		
		//1.1 If the tree is empty, then there is no minimum, so an exception is to be raised 
		if (this.my_is_empty() == true)
			throw new myException("The tree is empty, so it has no minimum");
		//1.2. If it is not empty
		else{
			//1.2.1. We get the left subtree
			myBinarySearchTree<T1, T2> l = this.my_left_tree();
			
			//1.2.1. If the left tree is empty, then the minimum is the root node
			if (l.my_is_empty() == true)
				res = this.my_root();
			//1.2.2. If the left tree is non-empty, we keep looking for the minimum in the left subtree.
			else
				res = l.my_minimum();
		}				
		
		//2. We return res
		return res;				
	}

	//-------------------------------------------------------------------
	// 17. Assignment 2 - Operation 1 --> Count how many nodes are there at level k of myBinarySearchTree: my_count_at_level
	//-------------------------------------------------------------------	
	public int my_count_at_level(int level){
		int res = 0;

		return res;				
	}

	//-------------------------------------------------------------------
	// 18. Assignment 2 - Operation 2 --> Check if myBinarySearchTree is balanced: my_is_balanced
	//-------------------------------------------------------------------	
	public boolean my_is_balanced(){
		boolean res = false;
		
		return res;	
	}
	
	//-------------------------------------------------------------------
	// 19. Assignment 2 - Operation 3 --> Count how many nodes are smaller in myBinarySearchTree: my_count_smaller_nodes
	//-------------------------------------------------------------------	
	public int my_count_smaller_nodes(T1 key){
		int res = 0;

		return res;		
	}	
	
}
