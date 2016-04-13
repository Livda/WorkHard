
public interface myList<T> {
	
	//-------------------------------------------------------------------
	// Create an empty MyList: create_empty
	//-------------------------------------------------------------------
	//public myList create_empty(); --> Java does not support constructors in interfaces
		
	//-------------------------------------------------------------------
	// Basic Operation --> Get number of elements in MyList: my_get_length
	//-------------------------------------------------------------------	
	public int my_get_length();
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get element at of MyList at a concrete position: my_get_element
	//-------------------------------------------------------------------
	public T my_get_element(int index) throws myException;
		
	//-------------------------------------------------------------------
	// Basic Operation --> Add element to MyList at a concrete position: my_add_element 
	//-------------------------------------------------------------------
	public void my_add_element(int index, T element) throws myException;
	
	//-------------------------------------------------------------------
	// Basic Operation --> Remove element of MyList at a concrete position: my_remove_element 
	//-------------------------------------------------------------------	
	public void my_remove_element(int index) throws myException;
	
}