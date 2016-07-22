package slider;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class sliderFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new SliderFrame();
				frame.setTitle("Slider Frame");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
