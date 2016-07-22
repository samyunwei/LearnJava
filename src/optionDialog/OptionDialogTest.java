package optionDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class OptionDialogTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new OptionDialogFrame();
				frame.setTitle("OptionDialogFrameTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
