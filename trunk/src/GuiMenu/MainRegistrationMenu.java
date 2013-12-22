package GuiMenu;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainRegistrationMenu extends JFrame {
	public MainRegistrationMenu(){
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		
		JButton btnOpenFullMember = new JButton("Open full member registration");
		btnOpenFullMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOpenFullMember.setBounds(49, 69, 289, 29);
		getContentPane().add(btnOpenFullMember);
		
		JButton btnOpenStandardRegistration = new JButton("Open standard registration");
		btnOpenStandardRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOpenStandardRegistration.setBounds(49, 140, 289, 29);
		getContentPane().add(btnOpenStandardRegistration);
	}
}
	

		
	

