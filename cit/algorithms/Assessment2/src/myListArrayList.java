
import java.util.ArrayList;

public class myListArrayList<T> implements myList<T> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private ArrayList<T> items; 

	//-------------------------------------------------------------------
	// Basic Operation --> Create an empty myList: my_create_empty
	//-------------------------------------------------------------------
	//public myList my_create_empty(){}

	public myListArrayList(){
		this.items = new ArrayList<T>();
	}

	//-------------------------------------------------------------------
	// Basic Operation --> Get number of elements in MyList: my_get_length
	//-------------------------------------------------------------------	
	public int my_get_length(){
		return this.items.size();
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get element at of MyList at a concrete position: my_get_element
	//-------------------------------------------------------------------
	public T my_get_element(int index) throws myException{
		//1. If the index is a valid one we access to the element and return it.
		if ((index >= 0) && (index < this.my_get_length())){
			T value = this.items.get(index);
			return value;
		}
		//2. If the index is a wrong one. 
		else
			throw new myException("Invalid Index. The ADT does not have such an Index Position");		
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Add element to MyList at a concrete position: my_add_element 
	//-------------------------------------------------------------------
	public void my_add_element(int index, T element) throws myException{
		//1. If the index is a valid one we access to the element and return it.
		if ((index >= 0) && (index <= this.my_get_length()))
			this.items.add(index, element);
		//2. If the index is a wrong one. 
		else
			throw new myException("Invalid Index. The ADT does not have such an Index Position");	
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Remove element of MyList at a concrete position: my_remove_element 
	//-------------------------------------------------------------------	
	public void my_remove_element(int index) throws myException{
		//1. If the index is a valid one we access to the element and return it.
		if ((index >= 0) && (index < this.my_get_length()))
			this.items.remove(index);
		//2. If the index is a wrong one. 
		else
			throw new myException("Invalid Index. The ADT does not have such an Index Position");	
	}
	
}

