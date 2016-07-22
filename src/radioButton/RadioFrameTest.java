package radioButton;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class RadioFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new RadioButtonFrame();
				frame.setTitle("RadioFrame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
