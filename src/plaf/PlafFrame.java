package plaf;

import java.awt.event.*;
import javax.swing.*;


public class PlafFrame extends JFrame{
	private JPanel buttonPanel;
	public PlafFrame()
	{
		buttonPanel = new JPanel();
		
		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo info:infos)
		{
			makeButton(info.getName(), info.getClassName());
		}
		add(buttonPanel);
		pack();
	}



	void makeButton(String name, final String plafName) {
		JButton button = new JButton(name);
		buttonPanel.add(button);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try 
				{
					UIManager.setLookAndFeel(plafName);
					SwingUtilities.updateComponentTreeUI(PlafFrame.this);
					pack();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}

