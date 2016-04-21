package servletsEx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	public  static Connection getDBConnection(String server){
		Connection con= null;
		try {
			
			if(server.equalsIgnoreCase("sqlserver")){
				ResourceBundle rb = ResourceBundle.getBundle("servletsEx.utils.sqlserver");
				Class.forName(rb.getString("driverClass"));
				con = DriverManager.getConnection(rb.getString("url"));
			}
			
			if(server.equalsIgnoreCase("mysql")){
				Class.forName("com.mysql.jdbc.Driver");
				//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ash_exp","nani","Welcome1@");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*System.out.println("In DB utils . . . .");
		System.out.println(con.hashCode());
		System.out.println(con);*/
		return con;
	}
	
	public static void main(String[] args) {
		Connection con = DBUtils.getDBConnection("sqlserver");
		System.out.println(con);
	}
}
