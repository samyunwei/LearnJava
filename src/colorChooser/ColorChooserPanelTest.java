package colorChooser;
import java.awt.*;
import javax.swing.*;

public class ColorChooserPanelTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setTitle("ColorChooserFrameTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(new ColorChooserPanel());
				frame.setVisible(true);
			}
		});
	}

}
