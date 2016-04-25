package part1;
/**
 * The <code>Stack</code> class represents a last-in-first-out
 * (LIFO) stack of objects. The usual
 * <tt>push</tt> and <tt>pop</tt> operations are provided, as well as a
 * method to <tt>peek</tt> at the top item on the stack, and a method to test
 * for whether the stack is <tt>empty</tt>.
 * @author Ignacio Castiñeiras
 * @param <T>
 */

public class myStack_as_myList<T> implements myStack<T> {
	/**
	 * internal list
	 */
	private myList<T> myInternalList;

	/**
	 * Create an empty stack
	 */
	public myStack_as_myList(){
		this.myInternalList = new myListArrayList<T>();
	}

	public boolean my_is_empty(){
		return (this.myInternalList.my_get_length() == 0);
	}
	
	public T my_peek() throws myException{
		try{
			return this.myInternalList.my_get_element(0);
		}
		catch(Exception ex){
			throw new myException("Trying to Access to an Empty myStack");
		}
	}

	public void my_push(T element) throws myException{
		try{
			this.myInternalList.my_add_element(0, element);
		}
		catch(Exception ex){
			throw new myException("Trying to Add to a Full myStack");
		}		
	}
	
	public void my_pop() throws myException{
		try{
			this.myInternalList.my_remove_element(0);
		}
		catch(Exception ex){
			throw new myException("Trying to Remove from an Empty myStack");
		}				
	}
	
}
