
public class myNode<T> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private T info;
	private myNode<T> left;	
	private myNode<T> right;
	
	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	public myNode(T n){
		this.info = n;
		this.left = null;
		this.right = null;
	}
	
	//--------------------------------------------------
	// Get methods
	//--------------------------------------------------
	public T getInfo(){
		return this.info;
	}

	public myNode<T> getLeft(){
		return this.left;
	}	

	public myNode<T> getRight(){
		return this.right;
	}
	
	//--------------------------------------------------
	// Set methods
	//--------------------------------------------------
	public void setInfo(T n){
		this.info = n;
	}
	
	public void setLeft(myNode<T> n){
		this.left = n;
	}
	
	public void setRight(myNode<T> n){
		this.right = n;
	}
	
}
