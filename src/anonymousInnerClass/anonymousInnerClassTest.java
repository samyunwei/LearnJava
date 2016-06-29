package anonymousInnerClass;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
public class anonymousInnerClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TalkingClock clock = new TalkingClock();
		clock.start(1000, true);
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}

}


class TalkingClock
{
	public void start(int interval,final boolean beep)
	{
		ActionListener listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Date now = new Date();
				System.out.println("At the tone,the time is" + now);
				if(beep)
				{
					Toolkit.getDefaultToolkit().beep();
				}
			}
		};
		Timer t = new Timer(interval, listener);
		t.start();
	}
}
