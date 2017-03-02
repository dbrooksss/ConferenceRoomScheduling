package data; // model, view, controller

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class RunQueries{
	
	private final String CHECKLOGIN = "SELECT USERNAME, PASSWORD FROM [USER] WHERE USERNAME=? AND PASSWORD=? "
			+ "COLLATE SQL_Latin1_General_CP1_CS_AS"; // case sensitive

	public void checkLogin(String username, String password){
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
				
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
//						ConferenceRoomSelector window = new ConferenceRoomSelector();
//						window.setVisible(true);
					}
				});
				
				JOptionPane.showMessageDialog(null, "Match Found!");
				for(int i = 1; i <= columnCount; i++){
					System.out.println(metadata.getColumnName(i) + ": " + rs.getString(i));
				}
			}else{
				JOptionPane.showMessageDialog(null, "Match Not Found!");
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
	}
}

// Object[] row = new Object[columnCount];
// for (int i = 1; i <= columnCount; ++i) {
// row[i - 1] = rs.getString(i); // Or even rs.getObject()
// System.out.println(row.toString());
// }
// model.addRow(row); // make new object
