package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import model.User;

public class UserRepository{

	private final static String CHECKLOGIN = "SELECT FIRSTNAME, LASTNAME, USERNAME, EMAIL, ROLE FROM [USER] WHERE USERNAME=? AND PASSWORD=? "
			+ "COLLATE SQL_Latin1_General_CP1_CS_AS"; // case sensitive

	public User login(String username, String password){
		Connection con = null;

		try{

			DatabaseObject dbo = new DatabaseObject();
			con = dbo.getConnection();

			PreparedStatement statement = con.prepareStatement(CHECKLOGIN);
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();

			if(rs.next()){
				//JOptionPane.showMessageDialog(null, "Match Found!");
				for(int i = 1; i <= columnCount;){
					//System.out.println(metadata.getColumnName(i) + ": " + rs.getString(i));
					
					User user = new User(rs.getString("firstname"),rs.getString("lastname"), rs.getString("username"), rs.getString("email"), Integer.parseInt(rs.getString("role")));
					return user;	
				}
			}else{
				//JOptionPane.showMessageDialog(null, "Match Not Found!");
			}
		}catch(SQLException e){
			System.out.println("Unable to Connect: " + e.getMessage());
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return new User("boyyyyyyyyy", "lastname", "username", "me@home.com", 1);
	}
}
