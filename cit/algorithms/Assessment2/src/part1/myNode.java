package part1;
/**
 * 
 * @author Ignacio Castiñeiras
 *
 * @param <T>
 */
public class myNode<T> {
	/**
	 * attributes
	 */
	private T info;
	private myNode<T> left;	
	private myNode<T> right;
	
	/**
	 * Create a node with an object as root
	 * @param n the object
	 */
	public myNode(T n){
		this.info = n;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Get the info of this node
	 * @return the info of the node
	 */
	public T getInfo(){
		return this.info;
	}

	/**
	 * Get the left node of this node
	 * @return the left node of this node
	 */
	public myNode<T> getLeft(){
		return this.left;
	}	

	/**
	 * Get the right node of this node
	 * @return the right node of this node
	 */
	public myNode<T> getRight(){
		return this.right;
	}
	
	/**
	 * Set the info of this node
	 * @param n the new info
	 */
	public void setInfo(T n){
		this.info = n;
	}
	
	/**
	 * Set the left node of this node
	 * @param n the new node
	 */
	public void setLeft(myNode<T> n){
		this.left = n;
	}
	
	/**
	 * Set the right node of this node
	 * @param n the new node
	 */
	public void setRight(myNode<T> n){
		this.right = n;
	}
	
}
