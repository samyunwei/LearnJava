package bounceThread;
import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import bounce.Ball;

public class BallComponent extends JPanel
{
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;
	
	private List<bounceThread.Ball>balls = new ArrayList<>();
	
	public void add(bounceThread.Ball b) {
		// TODO Auto-generated method stub
		balls.add(b);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(bounceThread.Ball b:balls)
		{
			g2.fill(b.getShape());
		}
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}

}
