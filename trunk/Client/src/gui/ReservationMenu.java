package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DataObjects.Customer;
import Messages.MessageInsertReservation;
import Messages.MessageInsertReservationReply;

import client.Client;
import java.awt.BorderLayout;

public class ReservationMenu extends AbstractGUIComponent {
	private Customer cst;
	private Map<String,Integer> parkingLots;	
	private JTextField textFieldCarNum;
	private final JComboBox comboBoxParkLot;
	private JTextField textField_estCotDate;
	private JTextField textField_estCotHour;
	private JTextField textField_estCinDate;
	private JTextField textField_estCinHour;
	public ReservationMenu(final IGUINavigator navigator, final Customer cst, Map<String,Integer> mp) {

		this.cst = cst;
		this.parkingLots = mp;
		setSize(new Dimension(501, 402));
		setLayout(null);

		JLabel lblCid = new JLabel("Car number");
		lblCid.setBounds(15, 16, 69, 20);
		add(lblCid);

		JLabel lblParkingLot = new JLabel("Parking lot");
		lblParkingLot.setBounds(15, 57, 94, 20);
		add(lblParkingLot);

		JLabel lblEstimateChackOut = new JLabel("Estimate check out date");
		lblEstimateChackOut.setBounds(15, 189, 184, 20);
		add(lblEstimateChackOut);

		JLabel lblEstimateChackOut_1 = new JLabel("Estimate check out hour");
		lblEstimateChackOut_1.setBounds(15, 225, 184, 20);
		add(lblEstimateChackOut_1);

		textFieldCarNum = new JTextField();
		textFieldCarNum.setBounds(82, 13, 146, 26);
		add(textFieldCarNum);
		textFieldCarNum.setColumns(10);

		comboBoxParkLot = new JComboBox();
		comboBoxParkLot.setBounds(82, 54, 146, 26);
		Set keys = parkingLots.keySet();
		for (Iterator i = keys.iterator(); i.hasNext();)
		{
			comboBoxParkLot.addItem(i.next());
		}
		add(comboBoxParkLot);

		textField_estCotDate = new JTextField();
		textField_estCotDate.setBounds(214, 186, 146, 26);
		add(textField_estCotDate);
		textField_estCotDate.setColumns(10);

		textField_estCotHour = new JTextField();
		textField_estCotHour.setBounds(214, 222, 146, 26);
		add(textField_estCotHour);
		textField_estCotHour.setColumns(10);

		

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arr[] = new String[8];
				arr[0] = Integer.toString(100000 + new Random().nextInt(900000));
				arr[1] = textFieldCarNum.getText();
				arr[2] = cst.getId();
				arr[3] = Integer.toString(parkingLots.get(comboBoxParkLot.getSelectedItem()));
				arr[4] = textField_estCinDate.getText();
				arr[5] = textField_estCinHour.getText();
				arr[6] = textField_estCotDate.getText();
				arr[7] = textField_estCotHour.getText();
				client.send(new MessageInsertReservation(arr));
				MessageInsertReservationReply irr = (MessageInsertReservationReply)client.getMessage();
				irr.doAction();
				navigator.goBack();
			}
		});
		btnSubmit.setBounds(40, 299, 115, 29);
		add(btnSubmit);

		JLabel lblEstimateCheckIn = new JLabel("Estimate check in date");
		lblEstimateCheckIn.setBounds(15, 106, 184, 20);
		add(lblEstimateCheckIn);

		textField_estCinDate = new JTextField();
		textField_estCinDate.setColumns(10);
		textField_estCinDate.setBounds(214, 106, 146, 26);
		add(textField_estCinDate);

		JLabel lblEstimateCheckInhour = new JLabel("Estimate check inhour");
		lblEstimateCheckInhour.setBounds(15, 142, 184, 20);
		add(lblEstimateCheckInhour);

		textField_estCinHour = new JTextField();
		textField_estCinHour.setColumns(10);
		textField_estCinHour.setBounds(214, 142, 146, 26);
		add(textField_estCinHour);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(210, 299, 115, 29);
		add(btnCancel);


	}
}
