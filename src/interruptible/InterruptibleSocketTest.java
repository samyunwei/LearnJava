package interruptible;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.channels.*;
import javax.swing.*;



public class InterruptibleSocketTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new InterruptibleSocketFrame();
				frame.setTitle("InterruptibleSocketTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
class InterruptibleSocketFrame extends JFrame
{
	public static final int TEXT_ROWS = 20;
	public static final int TEXT_COLUMNS = 60;
	
	private Scanner in;
	private JButton interruptibleButton;
	private JButton blockingButton;
	private JButton cancelButton;
	private JTextArea messages;
	private TestServer server;
	private Thread connectThread;
	
	
	public InterruptibleSocketFrame()
	{
		JPanel northPanel = new JPanel();
		add(northPanel,BorderLayout.NORTH);
		
		messages = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
		add(new JScrollPane(messages));
		
		interruptibleButton = new JButton("Interruptibe");
		blockingButton = new JButton("Blocking");
		northPanel.add(interruptibleButton);
		northPanel.add(blockingButton);
		
		interruptibleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				interruptibleButton.setEnabled(false);
				blockingButton.setEnabled(false);
				cancelButton.setEnabled(true);
				connectThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try
						{
							connectInterruptibly();
						}catch(IOException e)
						{
							messages.append("\nInterruptibleSocketTest.connectInterruptible:" + e);
						}
					}
				});
				connectThread.start();
			}
		});
		
		blockingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				interruptibleButton.setEnabled(false);
				blockingButton.setEnabled(false);
				cancelButton.setEnabled(true);
				connectThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try
						{
							connectBlocking();
						}catch(IOException e)
						{
							messages.append("\nInterruptibleSocketTest.connectBlocking:" + e);
						}
					}
				});
				connectThread.start();
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(false);
		northPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				connectThread.interrupt();
				cancelButton.setEnabled(false);
			}
		});
		server = new TestServer();
		new Thread(server).start();
		pack();
	}
	
	
	public void connectInterruptibly() throws IOException
	{
		messages.append("Interruptible:\n");
		try(SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost",8189)))
		{
			in = new Scanner(channel);
			while(!Thread.currentThread().isInterrupted())
			{
				messages.append("Reading ");
				if(in.hasNextLine())
				{
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		}
		finally
		{
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					messages.append("Channel closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}
			});
		}
	}
	
	public void connectBlocking() throws IOException
	{
		messages.append("Blocking\n");
		try(Socket sock = new Socket("localhost",8189))
		{
			in = new Scanner(sock.getInputStream());
			while(!Thread.currentThread().isInterrupted())
			{
				messages.append("Reading ");
				if(in.hasNextLine())
				{
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		}
	}
	
	
	
	class TestServer implements Runnable {

		@Override
		public void run() {
			try {
				// TODO Auto-generated method stub
				ServerSocket s = new ServerSocket(8189);
				while (true) {
					Socket incoming = s.accept();
					Runnable r = new TestServerHandler(incoming);
					Thread t = new Thread(r);
					t.start();
				}
			} catch (IOException e) {
				messages.append("\nTestServer.run:" + e);
			}
		}

	}

	class TestServerHandler implements Runnable
	{
		private Socket incoming;
		private int counter;
		
		public TestServerHandler(Socket i) {
			// TODO Auto-generated constructor stub
			incoming = i;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try
			{
				try
				{
					OutputStream outStream = incoming.getOutputStream();
					PrintWriter out = new PrintWriter(outStream,true);
					while(counter < 100)
					{
						counter++;
						if(counter <= 10)
						{
							out.println(counter);
						}
						Thread.sleep(100);
					}
				}finally
				{
					incoming.close();
					messages.append("Closing server\n");
				}
			}
			catch(Exception e)
			{
				messages.append("\nTestServerHandler.run:"+e);
			}
		}
		
	}
}

