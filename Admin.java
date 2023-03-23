package user;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin extends User {

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String name, String email, String password) {
		super(name, email, password);
		// TODO Auto-generated constructor stub
	}

	public Admin(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	public void seeEvents() {
		System.out.println("Evenimentele clientilor: ");
		Connection conn= null;
		String f1,f2,f3,f4,f5,f6,f7,f8;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/events","root","");
			String query = "Select * FROM event";
			Statement stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(query);
			while (rs.next()){
				f1 = rs.getString(1); 
				f2 = rs.getString(2);
				f3 = rs.getString(3); 
				f4 = rs.getString(4);
				f5 = rs.getString(5);
				f6 = rs.getString(6);
				f7 = rs.getString(7);
				f8 = rs.getString(8);
				System.out.println("Eveniment: " + f4 + " al clientului: " + f2 + " in data de " + f3 + " numarul de invitati: " + f5 + " in sala: " + f7);
			}
		} catch(ClassNotFoundException e) {
	    	e.printStackTrace();
	    	}catch(SQLException e) {
	    		e.printStackTrace();
	    		}catch (Exception e) {
	    			e.printStackTrace();
	    			}
	}
	
}
