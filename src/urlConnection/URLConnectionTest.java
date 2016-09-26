package urlConnection;

import java.io.*;
import java.net.*;
import java.util.*;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import sun.misc.*;
public class URLConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			String urlName;
			if(args.length > 0)
			{
				urlName = args[0];
			}else
			{
				urlName = "http://test.com";
			}
			
			URL url = new URL(urlName);
			URLConnection connection = url.openConnection();
			
			if(args.length > 2)
			{
				String username = args[1];
				String passwd = args[2];
				String input = username + ":" + passwd;
				String encoding = Base64.encode(input.getBytes());
				connection.setRequestProperty("Authorization","Basic" + encoding);
			}
			
			connection.connect();
			
			Map<String,List<String>>headers = connection.getHeaderFields();
			for(Map.Entry<String, List<String>> entry : headers.entrySet())
			{
				String key = entry.getKey();
				for(String value : entry.getValue())
				{
					System.out.println(key+":" + value);
				}
			}
			
			System.out.println("----------------------");
			System.out.println("getContentType" +":"+ connection.getContentType());
			System.out.println("getContentLength:" + connection.getContentLength());
			System.out.println("getContentEncoding" + connection.getContentEncoding());
			System.out.println("getDate:"+connection.getDate());
			System.out.println("getExpiration:"+connection.getExpiration());
			System.out.println("getLastModifiedL:"+connection.getLastModified());
			
			Scanner in = new Scanner(connection.getInputStream());
			
			for(int n = 1;in.hasNextLine() && n <=10;n++)
			{
				System.out.println(in.nextLine());
			}
			if(in.hasNextLine())
			{
				System.out.println("...");
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

}
