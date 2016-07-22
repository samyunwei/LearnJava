package dataExchange;
import javax.swing.*;
import java.awt.*;
public class DataExchangeFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new DataExchangeFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("DataFrameExchangeTest");
				frame.setVisible(true);
			}
		});
	}

}
