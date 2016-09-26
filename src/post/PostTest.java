package post;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;


public class PostTest {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Properties props = new Properties();
		try(InputStream in = Files.newInputStream(Paths.get(args[0])))
		{
			props.load(in);
		}
		String url = props.remove("url").toString();
		String result = doPost(url,props);
		System.out.println(result);
	}
	
	
	public static String doPost(String urlString,Map<Object,Object> nameValues) throws IOException
	{
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		
		try(PrintWriter out = new PrintWriter(connection.getOutputStream()))
		{
		boolean first = true;
		for(Map.Entry<Object, Object> pair : nameValues.entrySet())
		{
			if(first)
			{
				first = false;
			}else
			{
				out.print('&');
			}
			
			String name = pair.getKey().toString();
			String value = pair.getValue().toString();
			out.print(name);
			out.print('=');
			out.print(URLEncoder.encode(value,"UTF-8"));
		}
		
		StringBuilder response = new StringBuilder();
		try(Scanner in = new Scanner(connection.getInputStream()))
		{
			while(in.hasNextLine())
			{
				response.append(in.nextLine());
				response.append("\n");
			}
		}catch (IOException e) {
			// TODO: handle exception
			if(!(connection instanceof HttpURLConnection))
			{
				throw e;
			}
			InputStream err = ((HttpURLConnection)connection).getErrorStream();
			if(err == null)
			{
				throw e;
			}
			Scanner in = new Scanner(err);
			response.append(in.nextLine());
			response.append("\n");
		}
		
		return response.toString();
		}
	}
}
