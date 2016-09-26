package socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketTest {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		try(Socket s = new Socket("test.org",11))
		{
			InputStream inStream = s.getInputStream();
			Scanner in = new Scanner(inStream);
			
			while(in.hasNextLine())
			{
				String line = in.nextLine();
				System.out.println(line);
			}
		}
	}

}
