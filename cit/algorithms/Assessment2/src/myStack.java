
public interface myStack<T> {
	
	//-------------------------------------------------------------------
	// Create an empty myStack: my_create_empty
	//-------------------------------------------------------------------
	//public myStack my_create_empty(); --> Java does not support constructors in interfaces
		
	//-------------------------------------------------------------------
	// Basic Operation --> Check if myStack is empty: my_is_empty
	//-------------------------------------------------------------------	
	public boolean my_is_empty();
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get top element from myStack: my_peek
	//-------------------------------------------------------------------
	public T my_peek() throws myException;
		
	//-------------------------------------------------------------------
	// Basic Operation --> Add element to top of myStack: my_push 
	//--	-----------------------------------------------------------------
	public void my_push(T element) throws myException;
	
	//-------------------------------------------------------------------
	// Basic Operation --> Remove element from top of myStack: my_pop 
	//-------------------------------------------------------------------	
	public void my_pop() throws myException;
			
}