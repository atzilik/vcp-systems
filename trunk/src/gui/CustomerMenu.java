package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logic.Customer;
import javax.swing.JLabel;

public class CustomerMenu extends JFrame{
	private Customer cst;
	public CustomerMenu(Customer cst){
		this.cst = cst;
		setTitle("Main Menu");
		setSize(new Dimension(500, 300));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);

		JButton btnCheckRes = new JButton("Check reservation");
		btnCheckRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("CheckReservationStatus");
			}
		});
		btnCheckRes.setBounds(20, 183, 147, 42);
		getContentPane().add(btnCheckRes);

		JButton btnCheckIn = new JButton("Check in");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CheckInMenu cim = new CheckInMenu();
			}
		});
		btnCheckIn.setBounds(89, 98, 119, 42);
		getContentPane().add(btnCheckIn);

		JButton btnCheckOut = new JButton("Check out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CheckOutMenu com = new CheckOutMenu();
			}
		});
		btnCheckOut.setBounds(267, 98, 119, 42);
		getContentPane().add(btnCheckOut);

		JButton btnIssueComplaint = new JButton("Issue complaint");
		btnIssueComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("CustomerComplaintMenu");
			}
		});
		btnIssueComplaint.setBounds(188, 183, 135, 42);
		getContentPane().add(btnIssueComplaint);

		JButton btnReservation = new JButton("Reservation");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ReservationMenu rm = new ReservationMenu();
			}
		});
		btnReservation.setBounds(342, 183, 119, 42);
		getContentPane().add(btnReservation);
		
		JLabel lblname = new JLabel(cst.getfName() + " " + cst.getlName());
		lblname.setBounds(171, 31, 215, 14);
		getContentPane().add(lblname);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void openWindow(String str){
		setVisible(false);
		if (str.equals("CheckReservationStatus"))
		{
			CheckReservationStatus crs = new CheckReservationStatus(this);
		}
		else if (str.equals("CustomerComplaintMenu"))
		{
			CustomerComplaintMenu ccm = new CustomerComplaintMenu(this);
		}

	}
}
