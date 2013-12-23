package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReservationMenu extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public ReservationMenu() {
		setSize(new Dimension(500, 400));
		getContentPane().setLayout(null);
		
		JLabel lblCid = new JLabel("Cid");
		lblCid.setBounds(15, 16, 69, 20);
		getContentPane().add(lblCid);
		
		JLabel lblParkingLot = new JLabel("Parking lot");
		lblParkingLot.setBounds(15, 57, 94, 20);
		getContentPane().add(lblParkingLot);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(15, 93, 69, 20);
		getContentPane().add(lblDate);
		
		JLabel lblHour = new JLabel("Hour");
		lblHour.setBounds(15, 129, 69, 20);
		getContentPane().add(lblHour);
		
		JLabel lblEstimateChackOut = new JLabel("Estimate chack out date");
		lblEstimateChackOut.setBounds(15, 165, 184, 20);
		getContentPane().add(lblEstimateChackOut);
		
		JLabel lblEstimateChackOut_1 = new JLabel("Estimate chack out hour");
		lblEstimateChackOut_1.setBounds(15, 201, 184, 20);
		getContentPane().add(lblEstimateChackOut_1);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(15, 237, 69, 20);
		getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setBounds(82, 13, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(82, 123, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(124, 54, 134, 26);
		getContentPane().add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(82, 90, 146, 26);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(214, 162, 146, 26);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(214, 198, 146, 26);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(99, 234, 146, 26);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnAddCar = new JButton("Add car");
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddCar.setBounds(69, 288, 115, 29);
		getContentPane().add(btnAddCar);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreate.setBounds(241, 288, 115, 29);
		getContentPane().add(btnCreate);
	}

}
