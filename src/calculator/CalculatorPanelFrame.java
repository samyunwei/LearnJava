package calculator;

import javax.swing.JFrame;

public class CalculatorPanelFrame extends JFrame
{
	public	CalculatorPanelFrame()
	{
		add(new CalculatorPanel());
		pack();
	}
}
