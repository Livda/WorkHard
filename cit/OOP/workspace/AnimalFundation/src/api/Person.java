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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
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
