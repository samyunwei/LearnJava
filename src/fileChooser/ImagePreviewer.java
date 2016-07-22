package fileChooser;

import java.awt.*;
import java.beans.*;
import java.io.*;
import javax.swing.*;

public class ImagePreviewer extends JLabel
{
	public ImagePreviewer(JFileChooser chooser)
	{
		setPreferredSize(new Dimension(100,100));
		setBorder(BorderFactory.createEtchedBorder());
		chooser.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				// TODO Auto-generated method stub
				if(event.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
				{
					File f = (File) event.getNewValue();
					if(f == null)
					{
						setIcon(null);
						return;
					}
					ImageIcon icon = new ImageIcon(f.getPath());
					
					if(icon.getIconWidth() > getWidth())
					{
						icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(),-1, Image.SCALE_DEFAULT));
					}
					
					setIcon(icon);
				}
			}
		});
	}
	
}
