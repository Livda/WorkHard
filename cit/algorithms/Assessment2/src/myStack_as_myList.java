

public class myStack_as_myList<T> implements myStack<T> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private myList<T> myInternalList;
	
	//-------------------------------------------------------------------
	// Create an empty myStack: my_create_empty
	//-------------------------------------------------------------------
	//public myList my_create_empty(){}

	public myStack_as_myList(){
		this.myInternalList = new myListArrayList<T>();
	}
		
	//-------------------------------------------------------------------
	// Basic Operation --> Check if myStack is empty: my_is_empty
	//-------------------------------------------------------------------	
	public boolean my_is_empty(){
		return (this.myInternalList.my_get_length() == 0);
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get top element from myStack: my_peek
	//-------------------------------------------------------------------
	public T my_peek() throws myException{
		try{
			return this.myInternalList.my_get_element(0);
		}
		catch(Exception ex){
			throw new myException("Trying to Access to an Empty myStack");
		}
	}
		
	//-------------------------------------------------------------------
	// Basic Operation --> Add element to top of myStack: my_push 
	//-------------------------------------------------------------------
	public void my_push(T element) throws myException{
		try{
			this.myInternalList.my_add_element(0, element);
		}
		catch(Exception ex){
			throw new myException("Trying to Add to a Full myStack");
		}		
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Remove element from top of myStack: my_pop 
	//-------------------------------------------------------------------	
	public void my_pop() throws myException{
		try{
			this.myInternalList.my_remove_element(0);
		}
		catch(Exception ex){
			throw new myException("Trying to Remove from an Empty myStack");
		}				
	}
	
}
