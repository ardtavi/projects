
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Connect {
	private static Connection connection;

	public static void connectDatabase()
	{
		String url = "jdbc:mysql://localhost:3306/restaurant";
		String username = "root";
		String password = "1234";

		System.out.println("Connecting database...");
		
		try  {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			/*JOptionPane optionPane = new JOptionPane("Cannot connect to database!", JOptionPane.ERROR_MESSAGE);    
			JDialog dialog = optionPane.createDialog("Error");
			dialog.setAlwaysOnTop(true);
			dialog.setVisible(true);*/
			System.out.println("Cannot connect to database!");
		}	
	}
	
	
	

	
	
}
