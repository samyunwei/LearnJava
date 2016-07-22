package slider;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;



public class SliderFrame extends JFrame
{
	private JPanel sliderPanel;
	private JTextField textField;
	private ChangeListener listener;
	
	public SliderFrame()
	{
		sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridBagLayout());
		
		listener = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent event) {
				// TODO Auto-generated method stub
				JSlider source = (JSlider)event.getSource();
				textField.setText("" + source.getValue());
			}
		};
		
		JSlider slider = new JSlider();
		addSlider(slider, "Plain");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Ticks");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Snap to ticks");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintTrack(false);
		addSlider(slider, "No Track");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setInverted(true);
		addSlider(slider, "Inverted");
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider, "Label");
		
		slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		
		
		Dictionary<Integer, Component> labelTable = new java.util.Hashtable<>();
		labelTable.put(0, new JLabel("A"));
		labelTable.put(20, new JLabel("B"));
		labelTable.put(40, new JLabel("C"));
		labelTable.put(60, new JLabel("D"));
		labelTable.put(80, new JLabel("E"));
		labelTable.put(100, new JLabel("F"));
		slider.setLabelTable(labelTable);
		addSlider(slider, "Custom labels");
		
		slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(20);
		
		labelTable = new java.util.Hashtable<>();
		labelTable.put(0, new JLabel(new ImageIcon("LeftNode.png")));
		labelTable.put(20, new JLabel(new ImageIcon("LeftNode.png")));
		labelTable.put(40, new JLabel(new ImageIcon("LeftNode.png")));
		labelTable.put(60, new JLabel(new ImageIcon("LeftNode.png")));
		labelTable.put(80, new JLabel(new ImageIcon("LeftNode.png")));
		labelTable.put(100, new JLabel(new ImageIcon("LeftNode.png")));
		slider.setLabelTable(labelTable);
		addSlider(slider, "Icon labels");
		
		
		textField = new JTextField();
		add(sliderPanel,BorderLayout.CENTER);
		add(textField,BorderLayout.SOUTH);
		pack();
		
	}
	
	public void addSlider(JSlider s,String description)
	{
		s.addChangeListener(listener);
		JPanel panel = new JPanel();
		panel.add(s);
		panel.add(new JLabel(description));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = sliderPanel.getComponentCount();
		gbc.anchor = GridBagConstraints.WEST;
		sliderPanel.add(panel,gbc);
	}
}
