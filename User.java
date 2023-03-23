package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.xml.transform.Source;

public abstract class User {
	String name, email, password;

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	public boolean login(User u, String rol) {
		Connection conn = null;
		boolean ok = false, okuser = false, okpass = false;
		String f1,f2,f3,f4,f5;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/events","root","");
			String query = "Select * FROM users";
			Statement stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(query);
			while (rs.next()){
				f1 = rs.getString(1); // id;
				f2 = rs.getString(2); // role - admin or client;
				f3 = rs.getString(3); // name;
				f4 = rs.getString(4); // email;
				f5 = rs.getString(5); // password;
				if(u.email.equals(f4) && u.password.equals(f5) && rol.equals(f2))
					{
					  ok = true;
					  u.name = f3; 
					}
				if(u.email.equals(f4))
					okuser = true;
				if(u.password.equals(f5))
					okpass = true;
				} //end while
			conn.close();
			rs.close();
			stmt.close();
			} //end try
		    catch(ClassNotFoundException e) {
		    	e.printStackTrace();
		    	}catch(SQLException e) {
		    		e.printStackTrace();
		    		}catch (Exception e) {
		    			e.printStackTrace();
		    			}
		if(okuser == false)
			System.out.print("Email gresit, ");
		if(okpass == false)
			System.out.print("Parola gresita, ");
		return ok;
	}
}
