package test;
import java.nio.file.*;
import java.sql.*;
import java.io.*;
import java.util.*;


public class TestDB {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		// TODO Auto-generated method stub
		try
		{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			runTest();
		}
		catch(SQLException ex)
		{
			for(Throwable t: ex)
			{
				t.printStackTrace();
			}
		}
		
	}
	
	public static void runTest() throws SQLException,IOException
	{
		try(Connection conn = getConnection())
		{
			Statement stat = conn.createStatement();
			stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
			stat.execute("INSERT INTO Greetings VALUES ('Hello,World')");
			
			try(ResultSet result = stat.executeQuery("SELECT * FROM Greetings"))
			{
				if(result.next())
				{
					System.out.println(result.getString(1));
				}
			}
			stat.execute("DROP TABLE Greetings");
			conn.close();
		}
	}
	
	public static Connection getConnection() throws SQLException,IOException
	{
		Properties props = new Properties();
		try(InputStream in = Files.newInputStream(Paths.get("database.properties")))
		{
			props.load(in);
		}
//		String drivers = props.getProperty("jdbc.dirvers");
//		if(drivers != null)
//		{
//			System.setProperty("jdbc.drivers",drivers);
//		}
//		String url = props.getProperty("jdbc.url");
//		String username = props.getProperty("jdbc.username");
//		String password = props.getProperty("jdbc.password");

		
		return DriverManager.getConnection("jdbc:derby:C:\\Users\\Sam\\LearnJAVA\\DB\\COREJAVA");
	}
}
