package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseObject{

	private static final String URL = "jdbc:sqlserver://localhost;database=ConferenceRoom;integratedSecurity=true;";
	private static Connection connection;

	public DatabaseObject(){

		try{
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(driver);
			connection = DriverManager.getConnection(URL);

		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnection(){
		return connection;
	}
}