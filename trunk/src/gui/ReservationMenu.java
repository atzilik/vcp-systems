package gui;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ReservationMenu extends Frame{
	private CustomerMenu cm;
	private Map<String,Integer> parkingLots;
	private JTextField textFieldCarNum;
	private final JComboBox comboBoxParkLot;
	private JTextField textField_estCotDate;
	private JTextField textField_estCotHour;
	private JTextField textField_estCinDate;
	private JTextField textField_estCinHour;
	public ReservationMenu(final CustomerMenu cm, final Map<String,Integer> parkingLots) {
		super();
		this.cm = cm;
		this.parkingLots = parkingLots;
		setSize(new Dimension(501, 402));
		
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
		
		comboBoxParkLot = new JComboBox();
		comboBoxParkLot.setBounds(82, 54, 146, 26);
		Set keys = parkingLots.keySet();
		for (Iterator i = keys.iterator(); i.hasNext();)
		{
			comboBoxParkLot.addItem(i.next());
		}
		getContentPane().add(comboBoxParkLot);
		
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
				
				String arr[] = new String[10];
				arr[0] = "Insert reservation";
				Random rnd = new Random();
				arr[1] = Integer.toString(100000 + rnd.nextInt(900000));
				arr[2] = textFieldCarNum.getText();
				arr[3] = cm.getCst().getId();
				arr[4] = Integer.toString(parkingLots.get(comboBoxParkLot.getSelectedItem()));
				arr[5] = textField_estCinDate.getText();
				arr[6] = textField_estCinHour.getText();
				arr[7] = textField_estCotDate.getText();
				arr[8] = textField_estCotHour.getText();
			Login.getCc().accept(arr);
				
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
		
		textField_estCinHour = new JTextField();
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
		
	}
}
