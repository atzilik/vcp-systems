package gui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainRegistrationMenu extends AbstractGUIComponent {
	private CustomerMenu cm;
	public MainRegistrationMenu(final CustomerMenu cm){
		super();
		this.cm = cm;

		JButton btnOpenFullMember = new JButton("Open full member registration");
		btnOpenFullMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Full");      
			}
		});
		btnOpenFullMember.setBounds(49, 69, 289, 29);
		add(btnOpenFullMember);

		JButton btnOpenStandardRegistration = new JButton("Open standard registration");
		btnOpenStandardRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Standard");

			}
		});
		btnOpenStandardRegistration.setBounds(49, 140, 289, 29);
		add(btnOpenStandardRegistration);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cm.setVisible(true);
			}
		});
		btnNewButton.setBounds(148, 212, 89, 23);
		add(btnNewButton);
	}
	public void openWindow(String str){
		if (str.equals("Full"))
		{
			FullMemberRegistration fmr = new FullMemberRegistration(this);
			setVisible(false);
			fmr.setVisible(true);
		}
		else
		{
			StandardMemberRegistration smr = new StandardMemberRegistration(this);
			setVisible(false); 
			smr.setVisible(true);
		}

	}
}