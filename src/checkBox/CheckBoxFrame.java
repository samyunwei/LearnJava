package checkBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CheckBoxFrame extends JFrame 
{
	private JLabel text;
	private JCheckBox Bold;
	private JCheckBox Itallc;
	static private final int  FONTSIZE = 50;
	
	public CheckBoxFrame() {
		// TODO Auto-generated constructor stub
		text = new JLabel("test text");
		Bold = new JCheckBox("Bold");
		Itallc = new JCheckBox("Itallc");
		
		ActionListener listener = new CheckBoxListener();
		Bold.addActionListener(listener);
		Itallc.addActionListener(listener);
		
		
		add(text,BorderLayout.CENTER);
		JPanel boxpanel = new JPanel();
		add(boxpanel,BorderLayout.SOUTH);
		boxpanel.add(Bold);
		boxpanel.add(Itallc);
		pack();
	}
	
	private class CheckBoxListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int mode = 0;
			if(Bold.isSelected())
			{
				mode += Font.BOLD;
			}
			if(Itallc.isSelected())
			{
				mode += Font.ITALIC;
			}
			text.setFont(new Font("Serif",mode,FONTSIZE));
		}
	}
	
	
	
	
	
	
	
}





