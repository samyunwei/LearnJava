package logging;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import sun.launcher.resources.launcher;

public class LoggingImageViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(System.getProperty("java.util.logging.config.class") == null && System.getProperty("java.util.logging.config.file") == null)
		{
			try
			{
				Logger.getLogger("com.sam.LearnJava").setLevel(Level.ALL);
				final int LOG_ROTATION_COUNT = 10;
				Handler handler = new FileHandler("%h/LoggingImageViewer.log",0,LOG_ROTATION_COUNT);
				Logger.getLogger("com.sam.LearnJava").addHandler(handler);
			}
			catch(IOException e)
			{
				Logger.getLogger("com.sam.LearnJava").log(Level.SEVERE,"Can't create log file handler",e);
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Handler windowHandler = new WindowsHandler();
				windowHandler.setLevel(Level.ALL);
				Logger.getLogger("com.sam.LearnJava").addHandler(windowHandler);
				
				JFrame frame = new ImageViewerFrame();
				frame.setTitle("LoggingImageViewer");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				Logger.getLogger("com.sam.LearnJava").fine("Showing Frame");
				frame.setVisible(true);
			}
		});
	}

}



class ImageViewerFrame extends JFrame
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	
	private JLabel label;
	private static Logger logger = Logger.getLogger("com.sam.LearnJava");
	public ImageViewerFrame()
	{
		logger.entering("ImageViewerFrame","<init>");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new FileOpenListener());
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				logger.fine("Exiting");
				System.exit(0);
			}
		});
		
		label = new JLabel();
		add(label);
		logger.exiting("ImageViewerFrame", "<Init>");
		
		
	}
	
	private class FileOpenListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed",event);
			
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
			
			chooser.setFileFilter(new FileFilter() {
				
				@Override
				public boolean accept(File f)
				{
					return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
				}
				
				public String getDescription()
				{
					return "GIF Images";
				}
			});
			
			int r = chooser.showOpenDialog(ImageViewerFrame.this);
			
			if(r == JFileChooser.APPROVE_OPTION)
			{
				String name = chooser.getSelectedFile().getPath();
				logger.log(Level.FINE,"Reading file{0}",name);
				label.setIcon(new ImageIcon(name));
			}else
			{
				logger.fine("File Open dialog canceled.");
			}
			logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
		}
	}
}

class WindowsHandler extends StreamHandler
{
	private JFrame frame;
	public WindowsHandler()
	{
		frame = new JFrame();
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		frame.setSize(200,200);
		frame.add(new JScrollPane(output));
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		
		setOutputStream(new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void write(byte[] b,int off,int len) throws IOException {
				// TODO Auto-generated method stub
				output.append(new String(b,off,len));
			}
		});
	}
	
	public void publish(LogRecord record)
	{
		if(!frame.isVisible())
		{
			return;
		}
		super.publish(record);
		flush();
	}
}