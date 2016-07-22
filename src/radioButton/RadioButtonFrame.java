package radioButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class RadioButtonFrame extends JFrame {
	private JPanel buttonpanel;
	private ButtonGroup group;
	private JLabel label;
	private static final int DEFAULT_SIZE = 36;
	
	public RadioButtonFrame()
	{
		label = new JLabel("The quick brown fox jumps over the lazy dog");
		label.setFont(new Font("Serif",Font.PLAIN,DEFAULT_SIZE));
		add(label,BorderLayout.CENTER);
		buttonpanel = new JPanel();
		group = new ButtonGroup();
		
		addRadioButton("Small", 8);
		addRadioButton("Medium", 12);
		addRadioButton("Large", 18);
		addRadioButton("Extra large", 36);
		
		add(buttonpanel,BorderLayout.SOUTH);
		pack();
	}
	
	
	public void addRadioButton(String name,final int size)
	{
		boolean selected = size == DEFAULT_SIZE;
		JRadioButton button = new JRadioButton(name,selected);
		group.add(button);
		buttonpanel.add(button);
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				label.setFont(new Font("serif",Font.PLAIN,size));
			}
		};
		
		button.addActionListener(listener);
	}
}
