package api;

public class Person {
	private String name;
	private String adress;
	private String phone;
	private String email;
	
	public Person(){
		name = null;
		adress = null;
		phone = null;
		email = null;
	}
	
	public Person(String name, String adress, String phone, String email){
		this.name = name;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", adress=" + adress + ", phone=" + phone + ", email=" + email + "]";
	}

	public void print() {
		System.out.println(this.toString());
	}
}
