package sizedFrame;

import java.awt.*;
import javax.swing.*;

public class SizedFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new SizedFrame();
				frame.setTitle("SizedFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
			}
		});
	}

}



class SizedFrame extends JFrame
{
	public SizedFrame()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth/2,screenHeight/2);
		setLocationByPlatform(true);
		
		Image img = new ImageIcon("icon.gif").getImage();
		setIconImage(img);
		
		
	}
}