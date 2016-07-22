package swingWorker;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

import javax.swing.*;

import com.sun.org.apache.xerces.internal.util.Status;

public class SwingWorkerTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new SwingWorkerFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	

}

class SwingWorkerFrame extends JFrame
{
	private JFileChooser chooser;
	private JTextArea textArea;
	private JLabel statusLine;
	private JMenuItem openItem;
	private JMenuItem cancelItem;
	private SwingWorker<StringBuilder, ProgressData> textReader;
	public static final int TEXT_ROWS = 20;
	public static final int TEXT_COLUMNS = 60;
	
	
	public SwingWorkerFrame()
	{
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		
		textArea = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
		add(new JScrollPane(textArea));
		
		statusLine  = new JLabel(" ");
		add(statusLine,BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		openItem = new JMenuItem("Open");
		menu.add(openItem);
		
		
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int result = chooser.showOpenDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					textArea.setText("");
					openItem.setEnabled(false);
					textReader = new TextReader(chooser.getSelectedFile());
					textReader.execute();
					cancelItem.setEnabled(true);
				}
			}
		});
		
		
		cancelItem = new JMenuItem("Cancel");
		menu.add(cancelItem);
		cancelItem.setEnabled(false);
		cancelItem.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textReader.cancel(true);
			}
		});
		pack();
	}
	private class ProgressData
	{
		public int number;
		public String line;
	}
	
	private class TextReader extends SwingWorker<StringBuilder, ProgressData>
	{
		private File file;
		private StringBuilder text = new StringBuilder();
		
		public TextReader(File file)
		{
			this.file = file;
		}

		@Override
		protected StringBuilder doInBackground() throws Exception {
			// TODO Auto-generated method stub
			int lineNumber = 0;
			try(Scanner in = new Scanner(new FileInputStream(file)))
			{
				while(in.hasNextLine())
				{
					String line = in.nextLine();
					lineNumber++;
					text.append(line);
					text.append("\n");
					ProgressData data = new ProgressData();
					data.number = lineNumber;
					data.line = line;
					publish(data);
					Thread.sleep(1);
				}
			}
			return text;
		}
		
		@Override
		public void process(List<ProgressData>data)
		{
			if(isCancelled())
			{
				return;
			}
			StringBuilder b = new StringBuilder();
			statusLine.setText("" + data.get(data.size() -1).number);
			for(ProgressData d:data)
			{
				b.append(d.line);
				b.append("\n");
			}
			textArea.append(b.toString());
		}
		
		
		@Override
		public void done()
		{
			try
			{
				StringBuilder result = get();
				textArea.setText(result.toString());
				statusLine.setText("Done");
			}
			catch(InterruptedException ex)
			{
				
			}
			catch (CancellationException ex) {
				// TODO: handle exception
				textArea.setText("");
				statusLine.setText("Cancelled");
			}
			catch (ExecutionException ex) {
				// TODO: handle exception
				statusLine.setText("" + ex.getCause());
			}
			
			cancelItem.setEnabled(false);
			openItem.setEnabled(true);
		}
	}
}


