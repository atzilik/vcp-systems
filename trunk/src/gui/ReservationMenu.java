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
	private CustomerMenu cm;
	private JTextField textFieldCarNum;
	private JTextField textField_estCotDate;
	private JTextField textField_estCotHour;
	private JTextField textField_estCinDate;
	public ReservationMenu(final CustomerMenu cm) {
		this.cm = cm;
		setSize(new Dimension(501, 402));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblCid = new JLabel("Car number");
		lblCid.setBounds(15, 16, 69, 20);
		getContentPane().add(lblCid);
		
		JLabel lblParkingLot = new JLabel("Parking lot");
		lblParkingLot.setBounds(15, 57, 94, 20);
		getContentPane().add(lblParkingLot);
		
		JLabel lblEstimateChackOut = new JLabel("Estimate check out date");
		lblEstimateChackOut.setBounds(15, 189, 184, 20);
		getContentPane().add(lblEstimateChackOut);
		
		JLabel lblEstimateChackOut_1 = new JLabel("Estimate check out hour");
		lblEstimateChackOut_1.setBounds(15, 225, 184, 20);
		getContentPane().add(lblEstimateChackOut_1);
		
		textFieldCarNum = new JTextField();
		textFieldCarNum.setBounds(82, 13, 146, 26);
		getContentPane().add(textFieldCarNum);
		textFieldCarNum.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(82, 54, 146, 26);
		getContentPane().add(comboBox);
		
		textField_estCotDate = new JTextField();
		textField_estCotDate.setBounds(214, 186, 146, 26);
		getContentPane().add(textField_estCotDate);
		textField_estCotDate.setColumns(10);
		
		textField_estCotHour = new JTextField();
		textField_estCotHour.setBounds(214, 222, 146, 26);
		getContentPane().add(textField_estCotHour);
		textField_estCotHour.setColumns(10);
		
		JButton btnAddCar = new JButton("Add car");
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddCar.setBounds(193, 299, 115, 29);
		getContentPane().add(btnAddCar);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(40, 299, 115, 29);
		getContentPane().add(btnSubmit);
		
		JLabel lblEstimateCheckIn = new JLabel("Estimate check in date");
		lblEstimateCheckIn.setBounds(15, 106, 184, 20);
		getContentPane().add(lblEstimateCheckIn);
		
		textField_estCinDate = new JTextField();
		textField_estCinDate.setColumns(10);
		textField_estCinDate.setBounds(214, 106, 146, 26);
		getContentPane().add(textField_estCinDate);
		
		JLabel lblEstimateCheckInhour = new JLabel("Estimate check inhour");
		lblEstimateCheckInhour.setBounds(15, 142, 184, 20);
		getContentPane().add(lblEstimateCheckInhour);
		
		JTextField textField_estCinHour = new JTextField();
		textField_estCinHour.setColumns(10);
		textField_estCinHour.setBounds(214, 142, 146, 26);
		getContentPane().add(textField_estCinHour);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cm.setVisible(true);
			}
		});
		btnCancel.setBounds(334, 299, 115, 29);
		getContentPane().add(btnCancel);
		
		
		setVisible(true);
	}
}
