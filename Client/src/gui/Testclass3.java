package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Testclass3 extends AbstractGUIComponent {

	private JLabel label;
	private JButton btn_goback;
	private JButton btn_goto1;
	private JButton btn_goto2;
	
	public Testclass3(final IGUINavigator navigator)
	{
		label = new JLabel();
		label.setText("I Am 3rd Screen");
		
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
		
		btn_goto2 = new JButton();
		btn_goto2.setText("Move to 2nd screen");
		btn_goto2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
									
				navigator.goToTestclass2();
							
			}
			
		
			
		});
		
		add(label);
		add(btn_goto1);
		add(btn_goto2);
		add(btn_goback);
		
	}
}