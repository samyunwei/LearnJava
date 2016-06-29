package button;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class ButtonFrame extends JFrame{

	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	
	public ButtonFrame()
	{
		setSize(DEFAULT_WIDTH,DEFAULT_WIDTH);
		
		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("Red");
		
		buttonPanel = new JPanel();
		buttonPanel.add(yellowButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);
		
		this.add(buttonPanel);
		
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction blueAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);
		
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
		
	}
	
	private class ColorAction implements ActionListener
	{
		private Color backgroundColor;
		
		public ColorAction(Color c) {
			// TODO Auto-generated constructor stub
			backgroundColor = c;
		}
		
		public void actionPerformed(ActionEvent event)
		{
			buttonPanel.setBackground(backgroundColor);
		}
	}

}
