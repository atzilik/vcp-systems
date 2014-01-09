package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DataObjects.Customer;
import Messages.MessageInsertReservation;
import Messages.MessageInsertReservationReply;

public class ReservationMenu extends AbstractGUIComponent {
	private Customer cst;
	private Map<String,Integer> parkingLots;	
	private final JComboBox comboBoxParkLot;
	private JTextField textField_estCotDate;
	private JTextField textField_estCotHour;
	private JTextField textField_CinDate;
	private JTextField textField_CinHour;
	public ReservationMenu(final IGUINavigator navigator, final Customer cst, Map<String,Integer> mp, final int type) {

		this.cst = cst;
		this.parkingLots = mp;
		setLayout(null);
		JLabel lblParkingLot = new JLabel("Parking lot");
		lblParkingLot.setBounds(15, 57, 94, 20);
		add(lblParkingLot);


		JLabel lblEstimateChackOut_1 = new JLabel("Estimate check out hour");
		lblEstimateChackOut_1.setBounds(15, 225, 184, 20);
		add(lblEstimateChackOut_1);

		comboBoxParkLot = new JComboBox();
		comboBoxParkLot.setBounds(82, 54, 146, 26);
		Set keys = parkingLots.keySet();
		for (Iterator i = keys.iterator(); i.hasNext();)
		{
			comboBoxParkLot.addItem(i.next());
		}
		add(comboBoxParkLot);


		textField_estCotHour = new JTextField();
		textField_estCotHour.setBounds(214, 222, 146, 26);
		add(textField_estCotHour);
		textField_estCotHour.setColumns(10);

		

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO check if parking lot is full and active before insert
				if (parkinglots[parkingLots.get(comboBoxParkLot.getSelectedItem())].isActive() && parkinglots[parkingLots.get(comboBoxParkLot.getSelectedItem())].isFull() == false)
				{
					String arr[] = new String[9];
					arr[0] = Integer.toString(100000 + new Random().nextInt(900000));
					arr[1] = cst.getCarId();
					arr[2] = cst.getId();
					arr[3] = Integer.toString(parkingLots.get(comboBoxParkLot.getSelectedItem()));
					if (type == 1)
					{
		
						arr[4] = new java.sql.Date(new java.util.Date().getTime()).toString();
						arr[5] = new java.sql.Time(new java.util.Date().getTime()).toString();
						arr[6] = arr[4];
						arr[8] = "0";
					}
					else
					{
						arr[4] = textField_CinDate.getText();
						arr[5] = textField_CinHour.getText();
						arr[6] = textField_estCotDate.getText();
						arr[8] = "1";
					}
					arr[7] = textField_estCotHour.getText();
							
					client.send(new MessageInsertReservation(arr));
					MessageInsertReservationReply irr = (MessageInsertReservationReply)client.getMessage();
					irr.doAction();
					navigator.goBack();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Parking lot is either full or not active.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnSubmit.setBounds(40, 299, 115, 29);
		add(btnSubmit);

		//reservation in advance
		if (type == 2)
		{
			JLabel lblEstimateChackOut = new JLabel("Estimate check out date");
			lblEstimateChackOut.setBounds(15, 189, 184, 20);
			add(lblEstimateChackOut);
			
			textField_estCotDate = new JTextField();
			textField_estCotDate.setBounds(214, 186, 146, 26);
			add(textField_estCotDate);
			textField_estCotDate.setColumns(10);
			
			JLabel lblCheckInDate = new JLabel("Check in date");
			lblCheckInDate.setBounds(15, 106, 184, 20);
			add(lblCheckInDate);

			textField_CinDate = new JTextField();
			textField_CinDate.setColumns(10);
			textField_CinDate.setBounds(214, 106, 146, 26);
			add(textField_CinDate);

			JLabel lblCheckInhour = new JLabel("Check inhour");
			lblCheckInhour.setBounds(15, 142, 184, 20);
			add(lblCheckInhour);

			textField_CinHour = new JTextField();
			textField_CinHour.setColumns(10);
			textField_CinHour.setBounds(214, 142, 146, 26);
			add(textField_CinHour);
		}
		
		
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
