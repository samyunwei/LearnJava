package inetAddress;

import java.io.*;
import java.net.*;

public class InetAddressTest {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		if(args.length > 0)
		{
			String host = args[0];
			InetAddress[] address = InetAddress.getAllByName(host);
			for(InetAddress a : address)
			{
				System.out.println(a);
			}
		}else
		{
			InetAddress localHostAddress = InetAddress.getLocalHost();
			System.out.println(localHostAddress);
		}
	}

}
