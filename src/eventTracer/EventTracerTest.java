package eventTracer;
import javax.swing.*;
import java.awt.*;

public class EventTracerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				EventTracer Tracer = new EventTracer();
				JFrame frame = new JFrame();
				Tracer.add(frame);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("EventTracerTest");
				frame.setVisible(true);
				
			}
		});
	}

}
