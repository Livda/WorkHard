package part2;

public class myBinarySearchNode<T1 extends Comparable<? super T1>, T2> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private T1 key;
	private T2 info;
	private myBinarySearchNode<T1, T2> left;	
	private myBinarySearchNode<T1, T2> right;
	
	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	public myBinarySearchNode(T1 k, T2 i){
		this.key = k;
		this.info = i;
		this.left = null;
		this.right = null;
	}
	
	//--------------------------------------------------
	// Get methods
	//--------------------------------------------------
	public T1 getKey(){
		return this.key;
	}
	
	public T2 getInfo(){
		return this.info;
	}

	public myBinarySearchNode<T1, T2> getLeft(){
		return this.left;
	}	

	public myBinarySearchNode<T1, T2> getRight(){
		return this.right;
	}
	
	//--------------------------------------------------
	// Set methods
	//--------------------------------------------------
	public void setKey(T1 k){
		this.key = k;
	}
	
	public void setInfo(T2 i){
		this.info = i;
	}
	
	public void setLeft(myBinarySearchNode<T1, T2> n){
		this.left = n;
	}
	
	public void setRight(myBinarySearchNode<T1, T2> n){
		this.right = n;
	}
	
}
