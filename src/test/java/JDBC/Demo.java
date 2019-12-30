package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {

	public static void main(String[] args)  {
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "qafox?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "root";
		String password = "root";
		
		Connection connection = null;
		
		try {
			
			//Create an object for Driver Class
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver).newInstance();
			
			//Connect to qafox Database
			connection = DriverManager.getConnection(url+dbName, username, password);
			
			//Verify the connection and execute SQL Statements
			if(!connection.isClosed()) {
				
				System.out.println("Successfully connected to qafox Database");
				
				//Fire SQL Selection Statements
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from Employee");
				
				while(resultSet.next()) {
					
					System.out.println(resultSet.getString("Name")+"--"+resultSet.getString("Location")+"--"+resultSet.getInt("Experience"));
									
				}
				
				System.out.println("-------------------------------------------");
			
				PreparedStatement prepareStatement = connection.prepareStatement("select * from Employee where Name=? and Experience=?");
				prepareStatement.setString(1,"Gopal");
				prepareStatement.setInt(2,8);
				ResultSet pResultSet = prepareStatement.executeQuery();
				
				while(pResultSet.next()) {
					
					System.out.println(pResultSet.getString("Name")+"--"+pResultSet.getString("Location")+"--"+pResultSet.getInt("Experience"));
					
				}
			
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}finally {
			
			try {
				if(!connection.isClosed()) {
					
					//Close the connection
					connection.close();
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}		
				
		
		}
}
