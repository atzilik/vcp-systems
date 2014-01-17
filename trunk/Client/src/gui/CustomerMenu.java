package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import DataObjects.Customer;
import DataObjects.FullMember;
import DataObjects.DataObjectMessageToUser;
import DataObjects.STDCustomer;
import DataObjects.STDMember;
import Messages.MessageGetMessage;
import Messages.MessageGetMessageReply;
import Messages.MessageGetParkingLotsID;
import Messages.MessageGetParkingLotsIDReply;
/**
 * customer main menu with customer function according to customer type
 * @author Gal
 *
 */
public class CustomerMenu extends AbstractGUIComponent {
	/**
	 * customer instance
	 */
	private Customer cst;	
	/**
	 * navigator to switch panels
	 */
	private IGUINavigator navigator;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param cst customer instance
	 */
	public CustomerMenu(final IGUINavigator navigator, final Customer cst){
		this.cst = cst;	
		this.navigator = navigator;
		setLayout(null);

		JLabel lblname = new JLabel(cst.getfName() + " " + cst.getlName());
		lblname.setBounds(230, 35, 215, 14);
		add(lblname);
		
		
		
		if (cst instanceof STDCustomer)
		{
			JButton btnCheckRes = new JButton("Cancel reservation");
			btnCheckRes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					navigator.goToCancelReservation(cst);
				}
			});
			btnCheckRes.setBounds(98, 242, 147, 42);
			add(btnCheckRes);

			JButton btnReservation = new JButton("<html>Reserve<br />in advance</html>");
			btnReservation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reserve(2);
				}
			});
			btnReservation.setBounds(326, 242, 135, 42);
			add(btnReservation);

			JButton btnMemberRegister = new JButton("Member register");
			btnMemberRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					navigator.goToMemberRegister(cst);
				}
			});
			btnMemberRegister.setBounds(50, 156, 135, 42);
			add(btnMemberRegister);

			JButton btnReserveNow = new JButton("Reserve now");
			btnReserveNow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reserve(1);
				}
			});
			btnReserveNow.setBounds(399, 156, 119, 45);
			add(btnReserveNow);
			
			JButton btnCheckReservation = new JButton("Check Reservation");
			btnCheckReservation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					navigator.goToCheckReservation(cst);	
				}
			});
			btnCheckReservation.setBounds(197, 298, 175, 42);
			add(btnCheckReservation);
		}
		
		if (cst instanceof STDMember)
		{
			JButton btnMemberRegister = new JButton("Full Member register");
			btnMemberRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					navigator.goToFullMemberRegisteration(cst);
				}
			});
			btnMemberRegister.setBounds(50, 156, 155, 42);
			add(btnMemberRegister);
		}

		JButton btnIssueComplaint = new JButton("Issue complaint");
		btnIssueComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageGetParkingLotsID());
				MessageGetParkingLotsIDReply gpir = (MessageGetParkingLotsIDReply)client.getMessage();
				navigator.goToIssueComplaint(cst, gpir.getMp());
			}
		});
		btnIssueComplaint.setBounds(227, 156, 147, 45);
		add(btnIssueComplaint);



		JButton btnCheckIn = new JButton("Check in");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageGetParkingLotsID());
				MessageGetParkingLotsIDReply gpir = (MessageGetParkingLotsIDReply)client.getMessage();
				navigator.goToCheckIn(cst, gpir.getMp());
			}
		});
		btnCheckIn.setBounds(126, 73, 119, 45);
		add(btnCheckIn);

		JButton btnCheckOut_1 = new JButton("Check out");
		btnCheckOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageGetParkingLotsID());
				MessageGetParkingLotsIDReply gpir = (MessageGetParkingLotsIDReply)client.getMessage();
				navigator.goToCheckOut(cst, gpir.getMp());
			}
		});
		btnCheckOut_1.setBounds(326, 73, 119, 45);
		add(btnCheckOut_1);
		
		String cstType = null;
		if (cst instanceof STDCustomer)
			cstType = "STDCustomer";
		else if (cst instanceof STDMember)
			cstType = "STDMember";
		else 
			cstType = "FullMember";
		
		JLabel lblNewLabel = new JLabel(cstType);
		lblNewLabel.setBounds(10, 11, 134, 14);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToCustomerLogin();
			}
		});
		btnNewButton.setBounds(461, 339, 119, 45);
		add(btnNewButton);
		
		// check if user have message and print
		if (cst instanceof STDCustomer)
		{
			client.send(new MessageGetMessage(cst.getId()));
		}
		else if (cst instanceof STDMember)
		{
			client.send(new MessageGetMessage(((STDMember) cst).getMemberId()));
		}
		else
		{
			client.send(new MessageGetMessage(((FullMember) cst).getMemberId()));
		}
		MessageGetMessageReply msgReplay = (MessageGetMessageReply)client.getMessage();
		
		for(DataObjectMessageToUser msg: msgReplay.getMsgArr())
			JOptionPane.showMessageDialog(null, msg.getMsg());
			 
	}

	/**
	 * shared method for both immediate reservation and in advance
	 * @param type immediate reservation or in advance
	 */
	public void reserve(int type){
		client.send(new MessageGetParkingLotsID());
		MessageGetParkingLotsIDReply gpir = (MessageGetParkingLotsIDReply)client.getMessage();
		navigator.goToReservation(cst, gpir.getMp(),type);

	}
}
