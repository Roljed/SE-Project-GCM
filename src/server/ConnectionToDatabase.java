package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @version 1
 * @author Avi Ayeli
 */

public class ConnectionToDatabase {

	static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static private final String DB = "xdhLgvyRnN";
	static private final String DB_URL = "jdbc:mysql://remotemysql.com/"+ DB + "?useSSL=false";
	static private final String USER = "xdhLgvyRnN";
	static private final String PASS = "uNtE7bXJvV";

	public static Connection connectToDatabase()
	{
		try 
		{
			Class.forName(JDBC_DRIVER).newInstance();
		} catch (Exception ex) {}

		Connection con=null;

		try 
		{
			con = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException ex) 
		{
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return con;
	}

	public static String SignIn (String nameUser, String password)
	{
		Connection conn= connectToDatabase();
		Statement stmt;
		String name="";
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT Password FROM User_Database WHERE UserName = '" + nameUser + "'");
			if((!rs.next()) || (!rs.getString("Password").equals(password)))
				return "Worng user name or password";
			rs=stmt.executeQuery("SELECT SignIn FROM User_Database WHERE UserName = '" + nameUser + "'");
			if ((!rs.next()) || (rs.getString("SignIn").equals("YES")))
				return "User already connected";
			stmt.executeUpdate("UPDATE User_Database SET SignIn = 'YES' WHERE UserName='"+ nameUser +"'");
			rs=stmt.executeQuery("SELECT PersonalName FROM User_Database WHERE UserName = '" + nameUser + "'");
			rs.next();
			name=rs.getString("PersonalName");
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Welcome " + name;
	}
	
	public static boolean SignOut (String nameUser)
	{
		Connection conn= connectToDatabase();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try 
		{
			stmt.executeUpdate("UPDATE User_Database SET SignIn = 'NO' WHERE UserName='"+ nameUser +"'");
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean AddClient (String nameUser, String namePersonal, String password, String phoneNumber, String email, String role)
	{
		Connection conn = connectToDatabase();
		Statement stmt;
		if (SearchForUserName(nameUser))
			return false; /* User name is taken */
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("INSERT INTO User_Database VALUES ('" + nameUser + "' , '" + namePersonal + "' , '" + password + "' , '" 
					+ phoneNumber + "', '" + email + "', '" + role + "', 'NO')");	 	
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public static boolean RemoveClient (String nameUser)
	{
		Connection conn= connectToDatabase();
		Statement stmt;
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT UserName FROM User_Database WHERE UserName = '" + nameUser + "'");
			if(!rs.next()) 
				return false;
			stmt.executeUpdate("DELETE FROM User_Database WHERE UserName = '" + nameUser + "'");
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean SearchForUserName (String nameUser)
	{
		Connection conn= connectToDatabase();
		Statement stmt;
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT UserName FROM User_Database WHERE UserName = '" + nameUser + "'");
			if(!rs.next()) 
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}