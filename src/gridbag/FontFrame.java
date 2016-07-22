package gridbag;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.sun.corba.se.pept.transport.EventHandler;

public class FontFrame extends JFrame
{
	public static final int TEXT_ROWS = 10;
	public static final int TEXT_COLUMNS = 20;
	
	private JComboBox<String> face;
	private JComboBox<Integer> size;
	private JCheckBox bold;
	private JCheckBox italic;
	private JTextArea sample;
	
	public FontFrame()
	{
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		ActionListener lister = java.beans.EventHandler.create(ActionListener.class, this, "updateSample");
		
		
		JLabel faceLabel = new JLabel("Face: ");
		face = new JComboBox<>(new String[]{"Serif","SansSerif","Monospaced","Dialog","DialogInput"});
		
		face.addActionListener(lister);
		
		JLabel sizeLabel  = new JLabel("Size: ");
		size = new JComboBox<>(new Integer[]{8,10,12,15,18,24,36,48});
		size.addActionListener(lister);
		
		bold = new JCheckBox("Bold");
		bold.addActionListener(lister);
		
		italic = new JCheckBox("Italic");
		italic.addActionListener(lister);
		
		sample = new JTextArea(TEXT_ROWS,TEXT_COLUMNS);
		sample.setText("testText");
		sample.setEditable(false);
		sample.setLineWrap(true);
		sample.setBorder(BorderFactory.createEtchedBorder());
		
		add(faceLabel,new GBC(0,0).setAnchor(GBC.EAST));
		add(face,new GBC(1,0).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
		add(sizeLabel,new GBC(0,1).setAnchor(GBC.EAST));
		add(size,new GBC(1,1).setFill(GBC.HORIZONTAL).setWeight(100, 0).setInsets(1));
		add(bold,new GBC(0,2,2,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(italic,new GBC(0,3,2,1).setAnchor(GBC.CENTER).setWeight(100, 100));
		add(sample,new GBC(2,0,1,4).setFill(GBC.BOTH).setWeight(100, 100));
		pack();
		updateSample();
		
	}
	
	
	public void updateSample()
	{
		String fontFace = (String) face.getSelectedItem();
		int fonstyle = (bold.isSelected()? Font.BOLD : 0) + (italic.isSelected()? Font.ITALIC : 0);
		int fontsize = size.getItemAt(size.getSelectedIndex());
		Font font = new Font(fontFace,fonstyle,fontsize);
		sample.setFont(font);
		sample.repaint();
	}
}
