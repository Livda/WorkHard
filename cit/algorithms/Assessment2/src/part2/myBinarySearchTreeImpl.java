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
			if (this.getLeftTree().isEmpty() == false){
				//2.1.1. We get the maximum element from the left subtree
				myBinarySearchNode<T1, T2> newNode = this.getLeftTree().maximum();
				
				//2.1.2. We compare the root element to it, to ensure it is bigger
				if ((newNode.getKey()).compareTo(this.getRoot().getKey()) >= 0)
					throw new myException("The tree being constructed is not ordered, as the root is smaller than the elements of its left subtree");
			}

			//2.2. If the right subtree is not empty
			if (this.getRightTree().isEmpty() == false){
				//2.2.1. We get the minimum element from the right subtree
				myBinarySearchNode<T1, T2> newNode = this.getRightTree().minimum();
				
				//2.2.2. We compare the root element to it, to ensure it is smaller
				if ((newNode.getKey()).compareTo(this.getRoot().getKey()) <= 0)
					throw new myException("The tree being constructed is not ordered, as the root is bigger than the elements of its right subtree");
			}

		}
		
	}

	//-------------------------------------------------------------------
	// 2. Basic Operation --> Check if myBinarySearchTree is empty: my_is_empty
	//-------------------------------------------------------------------
	public boolean isEmpty(){
		return (this.root == null);
	}	
		
	//-------------------------------------------------------------------
	// 3. Basic Operation --> Get root of myBinarySearchTree: my_root
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> getRoot() {
		return this.root;
	}
	
	//-------------------------------------------------------------------
	// 4. Basic Operation --> Get left tree of myBinarySearchTree: my_left_tree
	//-------------------------------------------------------------------
	public myBinarySearchTree<T1, T2> getLeftTree() throws myException{
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
	public myBinarySearchTree<T1, T2> getRightTree() throws myException{
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
	public myBinarySearchNode<T1,T2> find(T1 key){
		myBinarySearchNode<T1, T2> res;
		
		//1.1 If the tree is empty, then the element is not found 
		if (this.isEmpty() == true)
			res = null;
		//1.2. If it is not empty
		else{
			//1.2.1. We compare the element to the root of the tree
			int k = key.compareTo(this.getRoot().getKey()); 
			//Case A: If they are equal, is because we have found the node
			if (k == 0)
				res = this.getRoot();
			//Otherwise, we have to keep looking
			else
				//Case B: If the element is smaller than the root, we keep looking in the left subtree.
				if (k < 0)
					res = this.getLeftTree().find(key);
				//Case C: If the element is bigger than the root, we keep looking in the right subtree.
				else
					res = this.getRightTree().find(key);
		}
		
		//2. We return res
		return res;				
	}
	
	//-------------------------------------------------------------------
	// 7. Basic Operation --> Insert element at myBinarySearchTree: my_insert
	//-------------------------------------------------------------------	
	public myBinarySearchTree<T1, T2> insert(T1 key, T2 info){
		myBinarySearchTree<T1, T2> res;
		
		//1.1 If the tree is empty, then we insert the element by creating a new tree just containing this element 
		if (this.isEmpty() == true){
			//1.1.1. We create a new node with the element 
			myBinarySearchNode<T1, T2> node = new myBinarySearchNode<T1, T2>(key, info);
			
			//1.1.2. We link the root of the current tree to the new node just created
			this.root = node;
		}
		//1.2. If the tree is not empty
		else{
			//1.2.1. We compare the element to the root of the tree
			int k = key.compareTo(this.getRoot().getKey()); 
			//Case A: If they are equal, we do nothing as the node is already in the three
			if (k == 0){
				this.getRoot().setInfo(info);
			}
			//Otherwise, we have to keep looking
			else{
				//Case B: If the element is smaller than the root, we insert the node in the left subtree.
				if (k < 0){
					myBinarySearchTree<T1, T2> myTree1 = this.getLeftTree().insert(key, info);
					this.root.setLeft(myTree1.getRoot()); 
				}
				//Case C: If the element is bigger than the root, we insert the node in the right subtree.
				else{
					myBinarySearchTree<T1, T2> myTree1 = this.getRightTree().insert(key, info);
					this.root.setRight(myTree1.getRoot()); 
				}
			}
		}
		
		//2 We make res to point to the root of the tree and we return it
		res = new myBinarySearchTreeImpl<T1, T2>(this.getRoot()); 			
		return res;				
	}

	//-------------------------------------------------------------------
	// 8. Basic Operation --> Remove element at myBinarySearchTree: my_remove
	//-------------------------------------------------------------------	
	public myBinarySearchTree<T1, T2> remove(T1 key){
		myBinarySearchTree<T1, T2> res;
		
		//1. If the tree is non-empty
		if (this.isEmpty() == false){
			//1.1. We compare the element to the root of the tree
			int k = key.compareTo(this.getRoot().getKey()); 
			
			//Case A: If they are equal, is because we have found the node
			if (k == 0){
				boolean b1 = this.getLeftTree().isEmpty();
				boolean b2 = this.getRightTree().isEmpty();				
				
				//Case A1: If both subtrees are non-empty: We create a new tree with minimum of right subtree as root. Also, we remove the minimum of right subtree from the proper right subtree
				if ((b1 == false) && (b2 == false)){
					//A1.1. We get the minimum from the right subtree
					myBinarySearchNode<T1, T2> min = this.getRightTree().minimum();
					
					//A1.2. We create our new tree 
					myBinarySearchNode<T1, T2> node = new myBinarySearchNode<T1, T2>(min.getKey(), min.getInfo());
					node.setLeft(this.getLeftTree().getRoot());					
					myBinarySearchTree<T1, T2> myNewRightSubtree = this.getRightTree().remove(min.getKey());					
					node.setRight(myNewRightSubtree.getRoot());					

					//A1.4. We link the root of the current tree to the root of the new tree
					this.root = node;										
				}
				//Case A2: If left subtree is empty, the resulting tree will be the right subtree
				if ((b1 == true) && (b2 == false))
					this.root = this.getRightTree().getRoot();					
				//Case A3: If right subtree is empty, the resulting tree will be the left subtree				
				if ((b1 == false) && (b2 == true))
					this.root = this.getLeftTree().getRoot();					
				//Case A4: If both subtrees are empty, the resulting tree will be an empty tree		
				if ((b1 == true) && (b2 == true))
					this.root = null;
			}
			//Otherwise, we have to keep removing it in the subtrees
			else{
				//Case B: If the element is smaller than the root, we remove the node in the left subtree.
				if (k < 0){
					myBinarySearchTree<T1, T2> myTree1 = this.getLeftTree().remove(key);
					this.root.setLeft(myTree1.getRoot());
				}
				//Case C: If the element is bigger than the root, we remove the node in the right subtree.
				else{
					myBinarySearchTree<T1, T2> myTree1 = this.getRightTree().remove(key);
					this.root.setRight(myTree1.getRoot());
				}
			}
		}
		
		//2 We make res to point to the root of the tree and we return it
		res = new myBinarySearchTreeImpl<T1, T2>(this.getRoot()); 			
		return res;				
	}
		
	//-------------------------------------------------------------------
	// 9. Additional Operation --> Check the max number of levels of myBinarySearchTree: my_length
	//-------------------------------------------------------------------
	public int length(){
		int num;
		
		if (this.isEmpty() == true)
			num = 0;
		else{
			int l1 = this.getLeftTree().length();
			int l2 = this.getRightTree().length();
			num = Math.max(l1,l2) + 1;
		}
		
		return num;
	}

	//-------------------------------------------------------------------
	// 10. Additional Operation --> Count the amount of nodes of myBinarySearchTree: my_node_count
	//-------------------------------------------------------------------
	public int nodeCount(){
		int num;
		
		if (this.isEmpty() == true)
			num = 0;
		else{
			int c1 = this.getLeftTree().nodeCount();
			int c2 = this.getRightTree().nodeCount();
			num = c1 + c2 + 1;
		}
		
		return num;	
	}

	//-------------------------------------------------------------------
	// 11. Additional Operation --> Count the amount of leaf nodes of myBinarySearchTree: my_leaf_count
	//-------------------------------------------------------------------
	public int leafCount(){
		int num;
		
		if (this.isEmpty() == true)
			num = 0;
		else{
			if ((this.getLeftTree().isEmpty() == true) && (this.getRightTree().isEmpty() == true))
				num = 1;
			else{
				int l1 = this.getLeftTree().leafCount();
				int l2 = this.getRightTree().leafCount();
				num = l1 + l2;
			}
		}
		
		return num;	
	}
	
	//-------------------------------------------------------------------
	// 12. Traversal Operation --> Traverse myBinarySearchTree in in-order: my_inorder
	//-------------------------------------------------------------------
	public myList<T2> inorder(){
		//1. We create the empty list
		myList<T2> list = new myListArrayList<T2>();
		
		//2. If the root node is not empty
		if (this.isEmpty() == false){
			//2.1. We create the list of traversing the left and right sub-trees
			myList<T2> list1 = this.getLeftTree().inorder();
			myList<T2> list2 = this.getRightTree().inorder();	
	
			//2.2. We add the elements of list1 to list
			int k1 = list1.my_get_length();
			for (int i = 0; i < k1; i++)
				list.my_add_element(i, list1.my_get_element(i));
			
			//2.3. We add the root element to list
			list.my_add_element(k1, this.getRoot().getInfo());
			
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
	public myList<T2> preorder(){
		//1. We create the empty list
		myList<T2> list = new myListArrayList<T2>();
		
		//2. If the root node is not empty
		if (this.isEmpty() == false){
			//2.1. We create the list of traversing the left and right sub-trees
			myList<T2> list1 = this.getLeftTree().preorder();
			myList<T2> list2 = this.getRightTree().preorder();	
	
			//2.2. We add the root element to list
			list.my_add_element(0, this.getRoot().getInfo());

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
	public myList<T2> postorder(){
		//1. We create the empty list
		myList<T2> list = new myListArrayList<T2>();
		
		//2. If the root node is not empty
		if (this.isEmpty() == false){
			//2.1. We create the list of traversing the left and right sub-trees
			myList<T2> list1 = this.getLeftTree().postorder();
			myList<T2> list2 = this.getRightTree().postorder();	
	
			//2.2. We add the elements of list1 to list
			int k1 = list1.my_get_length();
			for (int i = 0; i < k1; i++)
				list.my_add_element(i, list1.my_get_element(i));
			
			//2.3. We add the elements of list2 to list
			int k2 = list2.my_get_length();			
			for (int i = 0; i < k2; i++)
				list.my_add_element((k1+i), list2.my_get_element(i));						

			//2.4. We add the root element to list
			list.my_add_element(k1+k2, this.getRoot().getInfo());
		}
		
		//3. We return the list
		return list;	
	}
	
	//-------------------------------------------------------------------
	// 15. Additional Operation --> Get maximum element at myBinarySearchTree: my_maximum
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> maximum() throws myException{
		myBinarySearchNode<T1, T2> res;
		
		//1.1 If the tree is empty, then there is no minimum, so an exception is to be raised 
		if (this.isEmpty() == true)
			throw new myException("The tree is empty, so it has no maximum");
		//1.2. If it is not empty
		else{
			//1.2.1. We get the right subtree
			myBinarySearchTree<T1, T2> r = this.getRightTree();
			
			//1.2.1. If the right tree is empty, then the maximum is the root node
			if (r.isEmpty() == true)
				res = this.getRoot();
			//1.2.2. If the right tree is non-empty, we keep looking for the maximum in the right subtree.
			else
				res = r.maximum();
		}				
		
		//2. We return res
		return res;				
	}

	//-------------------------------------------------------------------
	// 16. Additional Operation --> Get minimum element at myBinarySearchTree: my_minimum
	//-------------------------------------------------------------------	
	public myBinarySearchNode<T1, T2> minimum() throws myException{
		myBinarySearchNode<T1, T2> res;
		
		//1.1 If the tree is empty, then there is no minimum, so an exception is to be raised 
		if (this.isEmpty() == true)
			throw new myException("The tree is empty, so it has no minimum");
		//1.2. If it is not empty
		else{
			//1.2.1. We get the left subtree
			myBinarySearchTree<T1, T2> l = this.getLeftTree();
			
			//1.2.1. If the left tree is empty, then the minimum is the root node
			if (l.isEmpty() == true)
				res = this.getRoot();
			//1.2.2. If the left tree is non-empty, we keep looking for the minimum in the left subtree.
			else
				res = l.minimum();
		}				
		
		//2. We return res
		return res;				
	}

	//-------------------------------------------------------------------
	// 17. Assignment 2 - Operation 1 --> Count how many nodes are there at level k of myBinarySearchTree: my_count_at_level
	//-------------------------------------------------------------------	
	public int countAtLevel(int level){
		if (level == 1){
			return 1;
		}

		int res = 0;
		myBinarySearchTree<T1, T2> left = this.getLeftTree();
		myBinarySearchTree<T1, T2> right = this.getRightTree();
		if(!left.isEmpty()) res += left.countAtLevel(level-1);
		if (!right.isEmpty()) res += right.countAtLevel(level-1);
		
		return res;				
	}

	//-------------------------------------------------------------------
	// 18. Assignment 2 - Operation 2 --> Check if myBinarySearchTree is balanced: my_is_balanced
	//-------------------------------------------------------------------	
	public boolean isBalanced(){
		myBinarySearchTree<T1, T2> left = this.getLeftTree();
		myBinarySearchTree<T1, T2> right = this.getRightTree();
		
		int leftLength;
		if (left.isEmpty()){
			leftLength = 0;
		} else {
			leftLength = left.length();
		}
		
		int rightLength;
		if (right.isEmpty()){
			rightLength = 0;
		} else {
			rightLength = right.length();
		}
		
		int difference = Math.abs(leftLength - rightLength);
		if (difference > 1){
			return false;
		} else {
			boolean res = true;
			if (!left.isEmpty()) res &= left.isBalanced();
			if (!right.isEmpty()) res &= right.isBalanced();
			return res;
		}
	}
	
	//-------------------------------------------------------------------
	// 19. Assignment 2 - Operation 3 --> Count how many nodes are smaller in myBinarySearchTree: my_count_smaller_nodes
	//-------------------------------------------------------------------	
	public int countSmallerNodes(T1 key){
		myBinarySearchTree<T1, T2> left = this.getLeftTree();
		myBinarySearchTree<T1, T2> right = this.getRightTree();
		
		T1 rootKey = this.root.getKey();
		int compare = key.compareTo(rootKey);
		
		
		if(compare > 0){
			int res = 1;
			if (!left.isEmpty()){
				res += left.countSmallerNodes(key);
			}
			if (!right.isEmpty()){
				res += right.countSmallerNodes(key);
			}
			return res;
		} else {
			if (left.isEmpty()){
				return 0;
			} else {
				return left.countSmallerNodes(key);
			}
		}
	}
}
