package threaded;

import java.io.*;
import java.net.*;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class ThreadedEchoServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			int i = 1;
			ServerSocket s = new ServerSocket(8189);
			while(true)
			{
				Socket incoming = s.accept();
				System.out.println("Spawning" + i);
				Runnable r = new ThreadEchoHandler(incoming);
				Thread t = new Thread(r);
				t.start();
				i++;
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

}


class ThreadEchoHandler implements Runnable
{
	private Socket incoming;
	
	public ThreadEchoHandler(Socket i) {
		// TODO Auto-generated constructor stub
		
		incoming = i;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			try {
				InputStream inStream = incoming.getInputStream();
				OutputStream outputStream = incoming.getOutputStream();

				Scanner in = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outputStream, true);

				out.println("Hello! Enter BYE to exit");

				boolean done = false;
				while (!done && in.hasNextLine()) {
					String line = in.nextLine();
					out.println("Echo:" + line);
					if (line.trim().equals("BYE")) {
						done = true;
					}
				}
			} finally {
				incoming.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}