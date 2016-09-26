package query;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import org.apache.derby.*;
import org.apache.derby.jdbc.EmbeddedDriver;

public class QueryTest {
	private static final String allQuery = "SELECT Books.Price,Books.Title FROM Books";
	
	private static final String authorPublisherQuery = "SELECT Books.Price,Books.Title"
	+ " FROM Books,BooksAuthors,Publishers,Authors"
	+ " WHERE Authors.Author_ID = BooksAuthors.Author_ID AND BooksAuthors.ISBN = Books.ISBN"
	+ " AND Books.Publishers_ID = Publishers.Publishers_ID AND Authors.Name = ?"
	+ " AND Publishers.Name = ?";
	
	private static final String authorQuery = 
			"SELECT Books.Price,Books.Title FROM Books,BooksAuthors,Authors"
			+" WHERE Authors.Author_ID = BooksAuthors.Author_ID AND BooksAuthors.ISBN = Books.ISBN"
			+" AND Authors.Name = ? ";
	
	private static final String publisherQuery = 
			"SELECT Books.Price,Books.Title FROM Books,Publishers"
			+" WHERE Books.Publisher_ID = Publishers.Publihsers_ID AND Publishers.name = ?";
	
	private static final String priceUpdate = "UPDATE Books " + " SET Price = Price + ?"
	+" WHERE Books.Publishers_ID = (SELECT Publishers_ID FROM Publishers WHERE Name = ?)";
	
	private static Scanner in;
	private static Connection conn;
	private static ArrayList<String> authors = new ArrayList<>();
	private static ArrayList<String> publishers = new ArrayList<>();
	
	public static void main(String[] args) throws IOException,InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		EmbeddedDriver.class.newInstance();
		try
		{
			conn = getConnection();
			in = new Scanner(System.in);
			authors.add("Any");
			publishers.add("Any");
			try(Statement stat = conn.createStatement())
			{
				String query = "SELECT Name FROM Authors";
				try(ResultSet rs = stat.executeQuery(query))
				{
					while(rs.next())
					{
						authors.add(rs.getString(1));
					}
				}
				
				query = "SELECT Name FROM Publishers";
				try(ResultSet rs = stat.executeQuery(query))
				{
					while(rs.next())
					{
						publishers.add(rs.getString(1));
					}
				}
				
				boolean done =false;
				while(!done)
				{
					System.out.print("Q)uery C)change E)exit");
					String input = in.next().toUpperCase();
					if(input.equals("Q"))
					{
						executeQuery();
					}else if(input.equals("C"))
					{
						changePrices();
					}else
					{
						done = true;
					}
				}
			}		
		}
		catch (SQLException e) {
			// TODO: handle exception
			for (Throwable t:e)
			{
				System.out.println(t.getMessage());
			}
		}
	}
	
	private static void executeQuery() throws SQLException
	{
		String author = select("Authors:",authors);
		String publisher = select("Publishers:",publishers);
		PreparedStatement stat;
		if(!author.equals("Any") && !publisher.equals("Any"))
		{
			stat = conn.prepareStatement(authorPublisherQuery);
			stat.setString(1,author);
			stat.setString(2,publisher);
		}else if(!author.equals("Any") && publisher.equals("Any"))
		{
			stat = conn.prepareStatement(authorQuery);
			stat.setString(1,author);
		}else if(author.equals("Any") && !publisher.equals("Any"))
		{
			stat = conn.prepareStatement(publisherQuery);
			stat.setString(1,publisher);
		}
		else
		{
			stat = conn.prepareStatement(allQuery);
		}
		
		try(ResultSet rs = stat.executeQuery())
		{
			while(rs.next())
			{
				System.out.println(rs.getString(1) + "," + rs.getString(2));
			}
		}
	}
	
	private static void changePrices() throws SQLException
	{
		String publisher = select("Publishers:",publishers.subList(1,publishers.size()));
		System.out.print("Change prices by:");
		double priceChange = in.nextDouble();
		PreparedStatement stat = conn.prepareStatement(priceUpdate);
		stat.setDouble(1,priceChange);
		stat.setString(2, publisher);
		int r = stat.executeUpdate();
		System.out.println(r + " records updated.");
		
		
	}

	public static String select(String prompt,List<String> options)
	{
		while(true)
		{
			System.out.println(prompt);
			for(int i = 0;i<options.size();i++)
			{
				System.out.printf("%2d) %s%n",i+1,options.get(i));
			}
			int sel = in.nextInt();
			if(sel > 0 && sel <= options.size())
			{
				return options.get(sel - 1);
			}
		}
	}
		
	public static Connection getConnection() throws SQLException,IOException
	{
//		Properties props =  new Properties();
//		try(InputStream in = Files.newInputStream(Paths.get("database.properties")))
//		{
//			props.load(in);
//		}
//		
//		String url = props.getProperty("jdbc.url");
		return DriverManager.getConnection("jdbc:derby:C:\\Users\\Sam\\LearnJAVA\\DB\\COREJAVA");
	}
}
