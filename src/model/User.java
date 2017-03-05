package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User{

//	public static final String FirstName = "FirstName";
//	public static final String[] Columns = { FirstName};
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private Role role;

	public User(ResultSet rs) throws SQLException{
		
		//	firstName = rs.getString(FirstName);
	}
	
	public User(String firstName, String lastName, String username, String email, int role){
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.role = Role.values()[role];
		
	}
	
	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public Role getRole(){
		return role;
	}

	public void setRole(Role role){
		this.role = role;
	}
}
