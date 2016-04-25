package part1;
/**
 * {@code MyException} is an exception create for the needs of the labs
 * @author Ignacio Castiñeiras
 */
public class myException extends java.lang.RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an <code>myException</code> with no 
	 * detail message.
	 */
	public myException(){
		super("ADT Operation Error");
	}
	
	/**
     * Constructs an <code>myException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
	public myException(String s){
		super(s);
	}
	
}
