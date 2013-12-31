package gui;


import javax.swing.JButton;

import DataObjects.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberRegistration extends AbstractGUIComponent {
	private Customer cst;
	public MemberRegistration(final IGUINavigator navigator, final Customer cst){
		this.cst = cst;
		setLayout(null);
		JButton btnOpenFullMember = new JButton("Open full member registration");
		btnOpenFullMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToFullMemberRegisteration(cst);     
			}
		});
		btnOpenFullMember.setBounds(49, 69, 289, 29);
		add(btnOpenFullMember);

		JButton btnOpenStandardRegistration = new JButton("Open standard registration");
		btnOpenStandardRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToSTDMemberRegisteration(cst);

			}
		});
		btnOpenStandardRegistration.setBounds(49, 140, 289, 29);
		add(btnOpenStandardRegistration);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToCustomerMenu(cst);
			}
		});
		btnNewButton.setBounds(148, 212, 89, 23);
		add(btnNewButton);
	}

}