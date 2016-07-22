package dataExchange;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataExchangeFrame extends JFrame
{
	public static final int TEXT_ROWS = 20;
	public static final int TEXT_COLUMNS = 40;
	private PasswordChooser dialog = null;
	private JTextArea textArea;
	
	public DataExchangeFrame()
	{
		JMenuBar mbar = new JMenuBar();
		setJMenuBar(mbar);
		JMenu fileMenu = new JMenu("File");
		mbar.add(fileMenu);
		
		JMenuItem connectItem = new JMenuItem("Connect");
		connectItem.addActionListener(new ConnectAction());
		fileMenu.add(connectItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		
		textArea = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
		add(new JScrollPane(textArea),BorderLayout.CENTER);
		pack();
		
		
	}
	
	private class ConnectAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if(dialog == null)
			{
				dialog = new PasswordChooser();
			}
			dialog.setUser(new User("yourname",null));
			if(dialog.showDialog(DataExchangeFrame.this, "Connect"))
			{
				User u = dialog.getUser();
				textArea.append("User name = " + u.getName() + ", password = "+(new String(u.getPassword()))+ "\n");
				
			}
		}
	}
}
