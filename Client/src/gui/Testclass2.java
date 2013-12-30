package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Messages.TestMessage;

public class Testclass2 extends AbstractGUIComponent {

	private JLabel label;
	private JButton btn_goback;
	private JButton btn_goto1;
	private JButton btn_goto3;
	
	public Testclass2(final IGUINavigator navigator)
	{
		label = new JLabel();
		label.setText("I Am 2nd Screen");
		
		btn_goback = new JButton();
		btn_goback.setText("Go back to last screen");
		btn_goback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
									
				navigator.goBack();
							
			}
			
			
			
		});
		
		btn_goto1 = new JButton();
		btn_goto1.setText("Move to 1st screen");
		btn_goto1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
									
				navigator.goToGuiTestclass();
							
			}
			
		
			
		});
		
		btn_goto3 = new JButton();
		btn_goto3.setText("Move to 3rd screen");
		btn_goto3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
									
				navigator.goToTestclass3();
							
			}
			
		
			
		});
		
		add(label);
		add(btn_goto1);
		add(btn_goto3);
		add(btn_goback);
		
	}
}
