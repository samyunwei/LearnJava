package server;

import java.io.*;
import java.net.*;
import java.util.*;


public class EchoServer {

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		try(ServerSocket s = new ServerSocket(8189))
		{
			try(Socket incoming = s.accept())
			{
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();
				try(Scanner in = new Scanner(inStream))
				{
					PrintWriter out = new PrintWriter(outStream,true);
					out.println("Hello! Enter Bye to exit");
					boolean done = false;
					while(!done && in.hasNextLine())
					{
						String line = in.nextLine();
						out.println("Echo:" + line);
						if(line.trim().equals("BYE"))
						{
							done = true;
						}
					}
				}
			}
		}
	}

}
