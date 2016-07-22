package comboBox;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ComBoxFrame extends JFrame
{
	private JComboBox<String> faceCombo;
	private JLabel label;
	private static final int DEFAULT_SIZE = 24;
	
	public ComBoxFrame()
	{
		label = new JLabel("The quick brown fox jumps over the lazy dogs");
		label.setFont(new Font("Serif",Font.PLAIN,DEFAULT_SIZE));
		add(label,BorderLayout.CENTER);
		
		
		faceCombo = new JComboBox<>();
		faceCombo.addItem("Serif");
		faceCombo.addItem("SansSerif");
		faceCombo.addItem("Monospaced");
		faceCombo.addItem("Dialog");
		faceCombo.addItem("DialogInput");
		
		
		faceCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				label.setFont(new Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()),Font.PLAIN,DEFAULT_SIZE));
				
			}
		});
		
		JPanel comboPanel = new JPanel();
		comboPanel.add(faceCombo);
		add(comboPanel,BorderLayout.SOUTH);
		pack();
	}
}
