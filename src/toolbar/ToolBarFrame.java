package toolbar;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class ToolBarFrame extends JFrame
{
	private JToolBar bar;
	
	class TestAction extends AbstractAction {
		public TestAction(String name) {
			super(name);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			System.out.println(getValue(Action.NAME) + "selected.");
		}
	}
	
	public ToolBarFrame()
	{
		bar = new JToolBar("Color");
		add(bar,BorderLayout.NORTH);
		
		Action BlueAction = new TestAction("Blue");
		BlueAction.putValue(Action.SMALL_ICON, new ImageIcon("test.gif"));
		
		Action YellowAction = new TestAction("Yellow");
		YellowAction.putValue(Action.SMALL_ICON, new ImageIcon("test.gif"));
		
		Action RedAction = new TestAction("Red");
		RedAction.putValue(Action.SMALL_ICON, new ImageIcon("test.gif"));
		
		bar.add(BlueAction);
		bar.addSeparator();
		bar.add(YellowAction);
		bar.addSeparator();
		bar.add(RedAction);
		
	}
}
