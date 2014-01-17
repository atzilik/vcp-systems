package gui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DataObjects.Customer;
import Messages.MessageCancelReservation;
import Messages.MessageCancelReservationReply;

/**
 * used for canceling a customer reservation
 * @author Gal
 *
 */
public class CancelReservation extends AbstractGUIComponent {
	/**
	 *  cst Customer instance with the current customer details
	 */
	private Customer cst;
	private JTextField textField_resNum;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param cst customer instance
	 */
	public CancelReservation(final IGUINavigator navigator, final Customer cst) {
		this.cst = cst;
		setLayout(null);
		setLayout(null);

		JLabel lblCid = new JLabel("Reservation number:");
		lblCid.setBounds(61, 63, 140, 14);
		add(lblCid);

		textField_resNum = new JTextField();
		textField_resNum.setBounds(211, 60, 86, 20);
		textField_resNum.setColumns(10);
		add(textField_resNum);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(109, 182, 86, 37);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageCancelReservation(textField_resNum.getText(), cst.getId(),cst.getCarId()));
				MessageCancelReservationReply mcrr = (MessageCancelReservationReply)client.getMessage();
				mcrr.doAction();
				if (mcrr.isCompleted())
					navigator.goBack();
			}
		});
		add(btnSubmit);


		JButton button = new JButton("Cancel");
		button.setBounds(242, 182, 86, 37);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		add(button);
	}
}
