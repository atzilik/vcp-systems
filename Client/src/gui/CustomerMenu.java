package gui;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import DataObjects.Customer;
import DataObjects.FullMember;
import Messages.MessageGetParkingLotsID;
import Messages.MessageGetParkingLotsIDReply;
public class CustomerMenu extends AbstractGUIComponent {
	private Customer cst;	
	private IGUINavigator navigator;
	/**
	 * 
	 * @param cst 
	 */
	public CustomerMenu(final IGUINavigator navigator, Customer cst){
		this.cst = cst;	
		this.navigator = navigator;
		setLayout(null);
		if (cst instanceof FullMember)
		{
			openMemberMenu();
		}
		else
		{
			openCustomerMenu();
		}

	}

	public void openCustomerMenu(){
		JButton btnCheckRes = new JButton("Cancel reservation");
		btnCheckRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToCancelReservation(cst);
			}
		});
		btnCheckRes.setBounds(98, 242, 147, 42);
		add(btnCheckRes);

		JButton btnIssueComplaint = new JButton("Issue complaint");
		btnIssueComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToIssueComplaint(cst);
			}
		});
		btnIssueComplaint.setBounds(326, 242, 135, 42);
		add(btnIssueComplaint);

		JButton btnReservation = new JButton("<html>Reserve<br />in advance</html>");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserve();
			}
		});
		btnReservation.setBounds(227, 156, 119, 45);
		add(btnReservation);

		JLabel lblname = new JLabel(cst.getfName() + " " + cst.getlName());
		lblname.setBounds(221, 31, 215, 14);
		add(lblname);

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
				reserve();
			}
		});
		btnReserveNow.setBounds(399, 156, 119, 45);
		add(btnReserveNow);

		JButton btnCheckIn = new JButton("Check in");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToCheckIn(cst);
			}
		});
		btnCheckIn.setBounds(126, 73, 119, 45);
		add(btnCheckIn);

		JButton btnCheckOut_1 = new JButton("Check out");
		btnCheckOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToCheckOut(cst);
			}
		});
		btnCheckOut_1.setBounds(326, 73, 119, 45);
		add(btnCheckOut_1);
	}

	public void openMemberMenu(){
		JLabel lblname = new JLabel(cst.getfName() + " " + cst.getlName());
		lblname.setBounds(221, 31, 215, 14);
		add(lblname);
		JButton btnNewButton = new JButton("Check in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToCheckIn(cst);
			}
		});
		btnNewButton.setBounds(111, 77, 122, 61);
		add(btnNewButton);

		JButton btnCheckOut = new JButton("Check out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToCheckOut(cst);
			}
		});
		btnCheckOut.setBounds(325, 77, 122, 61);
		add(btnCheckOut);

		JButton btnIssueComplaint_1 = new JButton("Issue complaint");
		btnIssueComplaint_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToIssueComplaint(cst);
			}
		});
		btnIssueComplaint_1.setBounds(209, 171, 122, 61);
		add(btnIssueComplaint_1);

		setVisible(true);
	}

	public void reserve(){
		client.send(new MessageGetParkingLotsID());
		MessageGetParkingLotsIDReply gpir = (MessageGetParkingLotsIDReply)client.getMessage();
		navigator.goToReservation(cst, gpir.getMp());
		
	}
	
}
