package user;

public class Customer extends User {

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Customer(String name, String email, String password) {
		super(name, email, password);
		// TODO Auto-generated constructor stub
	}


	public Customer(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	public void register(Customer c) {
		System.out.println("Creezi cont");
	}
}
