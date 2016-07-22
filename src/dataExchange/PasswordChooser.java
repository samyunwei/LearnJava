package dataExchange;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sun.jmx.snmp.UserAcl;

public class PasswordChooser extends JPanel
{
	private JTextField username;
	private JPasswordField password;
	private JButton okButton;
	private boolean ok;
	private JDialog dialog;
	
	public PasswordChooser()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		panel.add(new JLabel("User Name"));
		panel.add(username = new JTextField(""));
		panel.add(new JLabel("password"));
		panel.add(password = new JPasswordField(""));
		add(panel,BorderLayout.CENTER);
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				ok = true;
				dialog.setVisible(false);
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel,BorderLayout.SOUTH);
		
	}
	
	public void setUser(User u)
	{
		username.setText(u.getName());
	}
	
	public User getUser()
	{
		return new User(username.getText(), new String(password.getPassword()));
	}
	
	public boolean showDialog(Component parent,String title)
	{
		ok = false;
		Frame owner = null;
		if(parent instanceof Frame) 
		{
			owner = (Frame)parent;
		}else
		{
			owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
		}
		
		if(dialog == null || dialog.getOwner() != owner)
		{
			dialog = new JDialog(owner,true);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
			
		}
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
	
	
	
}

