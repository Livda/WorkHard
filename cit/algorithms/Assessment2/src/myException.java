
public class myException extends java.lang.RuntimeException {

	private static final long serialVersionUID = 1L;

	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	public myException(){
		super("ADT Operation Error");
	}
	
	public myException(String s){
		super(s);
	}
	
}
