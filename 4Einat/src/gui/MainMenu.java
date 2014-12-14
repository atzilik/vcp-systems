package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
	
	private INavigator navigator;
	
	public MainMenu(final INavigator navigator) {
		
		this.navigator = navigator;
		setLayout(null);
		
		JLabel title = new JLabel("ברוכים הבאים לתיקיית מעבר");
		title.setFont(new Font("Tahoma", Font.PLAIN, 22));
		title.setBounds(360, 44, 296, 38);
		add(title);
		
		JButton btnToForm1 = new JButton("1: שאלון היכרות");
		btnToForm1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				navigator.toForm1();				
			}
			
			
		});
		btnToForm1.setBounds(762, 139, 196, 30);
		add(btnToForm1);
		
		JButton btnToForm2 = new JButton("2: שאלון מטרות");
		btnToForm2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				navigator.toForm2();				
			}
			
			
		});
		btnToForm2.setBounds(762, 207, 196, 30);
		add(btnToForm2);
		
	}

}
