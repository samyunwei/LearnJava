package dataExchange;

public class User
{	
	private String username;
	private String userpassword;
	
	public User(String theusername,String theuserpassword)
	{
		username = theusername;
		userpassword = theuserpassword;
	}
	
	public String getName()
	{
		return username;
	}
	
	public String getPassword()
	{
		return userpassword;
	}
}