package part1;

import java.util.ArrayList;
/**
 * 
 * @author Ignacio Castiñeiras
 *
 * @param <T>
 */

public class myListArrayList<T> implements myList<T> {
	/**
	 * the internal list 
	 */
	private ArrayList<T> items; 

	/**
	 * Constructs an empty list
	 */
	public myListArrayList(){
		this.items = new ArrayList<T>();
	}

	public int my_get_length(){
		return this.items.size();
	}
	
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

	public void my_add_element(int index, T element) throws myException{
		//1. If the index is a valid one we access to the element and return it.
		if ((index >= 0) && (index <= this.my_get_length()))
			this.items.add(index, element);
		//2. If the index is a wrong one. 
		else
			throw new myException("Invalid Index. The ADT does not have such an Index Position");	
	}
	
	public void my_remove_element(int index) throws myException{
		//1. If the index is a valid one we access to the element and return it.
		if ((index >= 0) && (index < this.my_get_length()))
			this.items.remove(index);
		//2. If the index is a wrong one. 
		else
			throw new myException("Invalid Index. The ADT does not have such an Index Position");	
	}
	
}

