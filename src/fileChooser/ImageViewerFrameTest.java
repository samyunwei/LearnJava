package fileChooser;
import java.awt.*;
import javax.swing.*;

public class ImageViewerFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new ImageViewerFrame();
				frame.setTitle("ImageVierFrameTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
