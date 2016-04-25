package part1;
/**
 * The <code>Stack</code> class represents a last-in-first-out
 * (LIFO) stack of objects. The usual
 * <tt>push</tt> and <tt>pop</tt> operations are provided, as well as a
 * method to <tt>peek</tt> at the top item on the stack, and a method to test
 * for whether the stack is <tt>empty</tt>.
 * @author Ignacio Castiñeiras
 *
 * @param <T>
 */
public interface myStack<T> {

	/**
	 * <b><i>Basic Operation</b></i>
     * <p>Tests if this stack is empty.
     *
     * @return  <code>true</code> if and only if this stack contains
     *          no items; <code>false</code> otherwise.
     */
	public boolean my_is_empty();
	
	/**
	 * <b><i>Basic Operation</b></i>
     * <p>Looks at the object at the top of this stack without removing it
     * from the stack.
     *
     * @return  the object at the top of this stack.
     * @throws  myException if this stack is empty.
     */
	public T my_peek() throws myException;
		
	/**
	 * <b><i>Basic Operation</b></i>
     * <p>Pushes an item onto the top of this stack.
     *
     * @param element the item to be pushed onto this stack.
     */
	public void my_push(T element) throws myException;
	
	/**
	 * <b><i>Basic Operation</b></i>
	 * <p>Removes the object at the top of this stack.
	 * @throws myException if this stack is empty.
	 */
	public void my_pop() throws myException;
			
}