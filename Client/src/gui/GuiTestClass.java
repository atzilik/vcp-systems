package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Messages.*;


public class GuiTestClass extends AbstractGUIComponent {

	private JLabel label;
	private JButton btn_goto2;
	private JButton btn_goto3;
	private JButton btn_goback;
	
	public GuiTestClass(final IGUINavigator navigator)
	{				
		
		label = new JLabel();
		label.setText("I Am 1st Screen");		
		
		btn_goto2 = new JButton();
		btn_goto2.setText("Move to screen 2");
		btn_goto2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					navigator.goToTestclass2();
			}
			
			
			
		});
		
		btn_goto3 = new JButton();
		btn_goto3.setText("Move to screen 3");
		btn_goto3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					navigator.goToTestclass3();
			}
			
			
			
		});
		
		add(label);
		add(btn_goto2);
		add(btn_goto3);
		
	}
	
	public static void main(String args[]) {
		
			Frame frame = new Frame();
			frame.setContentPane(new GuiTestClass(frame));			
		
	}
}
