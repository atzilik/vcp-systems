package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainRegistrationMenu extends JFrame {
	private CustomerMenu cm;
	public MainRegistrationMenu(final CustomerMenu cm){
		
		this.cm = cm;
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnOpenFullMember = new JButton("Open full member registration");
		btnOpenFullMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FullMemberRegistration fmr = new FullMemberRegistration();
				setVisible(false);	
			}
		});
		btnOpenFullMember.setBounds(49, 69, 289, 29);
		getContentPane().add(btnOpenFullMember);
		
		JButton btnOpenStandardRegistration = new JButton("Open standard registration");
		btnOpenStandardRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StandardMemberRegistration smr = new StandardMemberRegistration();
				setVisible(false);	
				
			}
		});
		btnOpenStandardRegistration.setBounds(49, 140, 289, 29);
		getContentPane().add(btnOpenStandardRegistration);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cm.setVisible(true);
			}
		});
		btnNewButton.setBounds(148, 212, 89, 23);
		getContentPane().add(btnNewButton);
		setVisible(true);
	}
}
	

		
	

