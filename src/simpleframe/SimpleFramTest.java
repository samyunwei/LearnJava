package simpleframe;

import java.awt.*;
import javax.swing.*;


public class SimpleFramTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SimpleFram frame = new SimpleFram();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
	}

}

class SimpleFram extends JFrame
{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public SimpleFram()
	{
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}
