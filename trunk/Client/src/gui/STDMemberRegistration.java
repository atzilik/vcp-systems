package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import DataObjects.Customer;

public class STDMemberRegistration extends AbstractGUIComponent {
	private JTextField textField_hour;
	protected Customer cst;
	private JTextField textField_carNum;
	private JTextField textField_date;
	public STDMemberRegistration(final IGUINavigator navigator, Customer cst) {

		setLayout(null);

		JLabel lblCid = new JLabel("Car number");
		lblCid.setBounds(74, 59, 72, 14);
		add(lblCid);

		textField_carNum = new JTextField();
		textField_carNum.setBounds(177, 56, 86, 20);
		add(textField_carNum);
		textField_carNum.setColumns(10);

		JLabel lblStartDate = new JLabel("Start date");
		lblStartDate.setBounds(72, 102, 95, 14);
		add(lblStartDate);

		textField_date = new JTextField();
		textField_date.setBounds(177, 99, 86, 20);
		add(textField_date);
		textField_date.setColumns(10);


		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(231, 233, 86, 34);
		add(btnCancel);
		
		JButton btnCreate = new JButton("Submit");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setBounds(90, 233, 86, 34);
		add(btnCreate);
		
		JLabel lblEstimateChackOut = new JLabel("Estimate check out hour");
		lblEstimateChackOut.setBounds(70, 139, 190, 20);
		add(lblEstimateChackOut);

		textField_hour = new JTextField();
		textField_hour.setBounds(215, 139, 87, 20);
		add(textField_hour);
		textField_hour.setColumns(10);

		JLabel lblParkingLot = new JLabel("Parking lot");
		lblParkingLot.setBounds(70, 172, 90, 20);
		add(lblParkingLot);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(157, 170, 109, 26);
		add(comboBox);
		
		
	}

}