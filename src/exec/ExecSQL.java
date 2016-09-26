package exec;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import org.apache.derby.jdbc.EmbeddedDriver;

public class ExecSQL {

	public static void main(String[] args) throws IOException,InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		EmbeddedDriver.class.newInstance();
		try
		{
			Scanner in = args.length == 0 ? new Scanner(System.in):new Scanner(Paths.get(args[0]));
			try(Connection conn = getConnection())
			{
				Statement stat = conn.createStatement();
				while(true)
				{
					if(args.length == 0)
					{
						System.out.println("Enter command or EXIT to exit:");
					}
					if(!in.hasNextLine())
					{
						return;
					}
					
					String line = in.nextLine();
					if(line.equalsIgnoreCase("EXIT"))
					{
						return;
					}
					if(line.trim().endsWith(";"))
					{
						line = line.trim();
						line = line.substring(0,line.length() - 1);
					}
					
					try
					{
						boolean isResult = stat.execute(line);
						if(isResult)
						{
							ResultSet rs = stat.getResultSet();
							showResultSet(rs);
						}else
						{
							int updateCount = stat.getUpdateCount();
							System.out.println(updateCount + "rows updateed");
						}
					}catch(SQLException ex)
					{
						for(Throwable e:ex)
						{
							e.printStackTrace();
						}
					}
				}
			}
			
		}
		catch(SQLException ex)
		{
			for(Throwable e:ex)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static Connection getConnection() throws SQLException,IOException
	{
		Properties props =  new Properties();
		try(InputStream in = Files.newInputStream(Paths.get("database.properties")))
		{
			props.load(in);
		}
		
		String url = props.getProperty("jdbc.url");
		return DriverManager.getConnection(url);
	}
	
	public static void showResultSet(ResultSet result) throws SQLException
	{
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		for(int i = 1;i<columnCount;i++)
		{
			if(i > 1 )
			{
				System.out.println();
			}
			System.out.print(metaData.getColumnLabel(i));
			System.out.println();
		}
		
		while(result.next())
		{
			for(int i = 1;i< columnCount;i++)
			{
				if(i > 1)
				{
					System.out.print(",");
				}
				System.out.print(result.getString(i));
			}
			System.out.println();
		}
	}
}
