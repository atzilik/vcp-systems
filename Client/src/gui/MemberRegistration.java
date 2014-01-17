package gui;


import javax.swing.JButton;

import DataObjects.Customer;
import Messages.MessageGetParkingLotsID;
import Messages.MessageGetParkingLotsIDReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * choose a membership option
 * @author Gal
 *
 */
public class MemberRegistration extends AbstractGUIComponent {
	/**
	 * Customer instance
	 */
	private Customer cst;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param cst customer instance
	 */
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
				client.send(new MessageGetParkingLotsID());
				MessageGetParkingLotsIDReply gpir = (MessageGetParkingLotsIDReply)client.getMessage();
				navigator.goToSTDMemberRegisteration(cst, gpir.getMp());

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