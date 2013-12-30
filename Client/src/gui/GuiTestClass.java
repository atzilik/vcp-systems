package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Messages.*;


public class GuiTestClass extends AbstractGUIComponent {

	private JButton button;
	
	public GuiTestClass()
	{
		setSize(640, 480);			
		button = new JButton();
		button.setText("click me!");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				TestMessage msg = new TestMessage();
				client.send(msg);
				
				TestMessage reply = (TestMessage) client.getMessage();	
				reply.doAction();
			}
			
			
			
		});
		
		add(button);
		
	}
	
	public static void main(String args[]) {
		
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(640, 480);
		mainFrame.setContentPane(new GuiTestClass());
		mainFrame.setVisible(true);
		
	}
}
