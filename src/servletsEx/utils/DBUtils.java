package servletsEx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	public  static Connection getDBConnection(){
		Connection con= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ash_exp","nani","Welcome1@");
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
}
