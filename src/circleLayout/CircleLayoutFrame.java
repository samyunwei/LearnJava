package circleLayout;

import java.awt.Button;

import javax.swing.*;

public class CircleLayoutFrame extends JFrame
{
	public CircleLayoutFrame()
	{
		setLayout(new CircleLayou());
		add(new JButton("Yellow"));
		add(new JButton("Blue"));
		add(new JButton("Red"));
		add(new JButton("Green"));
		add(new JButton("Orange"));
		add(new JButton("Fuchsia"));
		add(new JButton("Indigo"));
		pack();
	}
}
