package part1;
/**
 * 
 * @author Ignacio Castiñeiras
 *
 * @param <T>
 */
public interface myList<T> {

	/**
	 * <b><i>Basic Operation</b></i>
	 * <p>Get number of elements in <tt>MyList</tt>.
	 * 
	 * @return the number of elements in the list
	 */
	public int my_get_length();
	
	/**
	 * <b><i>Basic Operation</b></i>
	 * <p>Returns the element at the specified position in this list
	 * 
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 * @throws myException if the index is out of range (<tt>index 
	 * &lt; 0 || index &gt; size()</tt>)
	 */
	public T my_get_element(int index) throws myException;
		
	/**
	 * <b><i>Basic Operation</b></i>
	 * <p>Inserts the specified element at the specified position in this
     * list.
	 * @param index index at which the specified element is to be inserted
	 * @param element element to be inserted
	 * @throws myException if the index is out of range (<tt>index 
	 * &lt; 0 || index &gt; size()</tt>)
	 */
	public void my_add_element(int index, T element) throws myException;
	
	/**
	 * <b><i>Basic Operation</b></i>
	 * <p>Removes the element at the specified position in this list.
	 * @param index the index of the element to be removed
	 * @throws myException if the index is out of range (<tt>index 
	 * &lt; 0 || index &gt; size()</tt>)
	 */
	public void my_remove_element(int index) throws myException;
	
}