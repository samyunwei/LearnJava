package comboBox;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;

public class ComBoxFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ComBoxFrame();
				frame.setTitle("ComBoxFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
	}

}
