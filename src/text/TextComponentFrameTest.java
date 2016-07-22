package text;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TextComponentFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				JFrame frame = new TextComponentFrame();
				frame.setTitle("TextComponentFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
