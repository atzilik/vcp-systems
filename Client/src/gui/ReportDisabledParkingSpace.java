package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import DataObjects.ParkingSpace;
import DataObjects.Worker;
import Messages.MessageReportDisabledPSpace;
import Messages.MessageReportDisabledPSpaceReply;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ReportDisabledParkingSpace extends AbstractGUIComponent {
	private int i=0;
	public ReportDisabledParkingSpace(final IGUINavigator navigator, final Worker worker) {
		super();		

		setLayout(null);

		JLabel lblPlace = new JLabel("Floor Num:");
		lblPlace.setBounds(87, 49, 88, 14);
		add(lblPlace);

		JLabel lblReason = new JLabel("Row Num:");
		lblReason.setBounds(86, 85, 78, 14);
		add(lblReason);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(298, 240, 89, 23);
		add(btnCancel);

		JLabel lblNewLabel = new JLabel("ParkingLot:" +worker.getParkingLotID());
		lblNewLabel.setBounds(69, 11, 95, 14);
		add(lblNewLabel);

		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(161, 46, 86, 20);
		add(comboBox);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(161, 82, 86, 20);
		add(comboBox_1);

		JLabel lblDepth = new JLabel("Depth:");
		lblDepth.setBounds(86, 118, 65, 14);
		add(lblDepth);

		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(161, 113, 86, 20);
		add(comboBox_2);
		System.out.println(worker.getParkingLotID());
		System.out.println(parkinglots[0].getDepthSize());
		System.out.println(parkinglots[worker.getParkingLotID()].getDepthSize());
		while (i<parkinglots[worker.getParkingLotID()].getDepthSize()){
			comboBox_2.addItem(i);
			i++;
		}


		JButton btnEnablePs = new JButton("Enable parking space");
		btnEnablePs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String arr[] = new String[4];
				arr[0]=Integer.toString(worker.getParkingLotID());
				arr[1]=Integer.toString(comboBox.getSelectedIndex());
				arr[2]=Integer.toString(comboBox_1.getSelectedIndex());
				arr[3]=Integer.toString(comboBox_2.getSelectedIndex());
				// if user wants to enable parking space which is not disabled
				if (parkinglots[worker.getParkingLotID()].getParkingspace()[comboBox.getSelectedIndex()][comboBox_1.getSelectedIndex()][comboBox_2.getSelectedIndex()].isDisabled()==false){
					JOptionPane.showMessageDialog(null, "Unable to enable this parkingspace. This parking space is not disabled! ");
					return;
				}
				//if user wants to enable parking space which is disabled
				else if (parkinglots[worker.getParkingLotID()].getParkingspace()[comboBox.getSelectedIndex()][comboBox_1.getSelectedIndex()][comboBox_2.getSelectedIndex()].isDisabled()){
					parkinglots[worker.getParkingLotID()].getParkingspace()[comboBox.getSelectedIndex()][comboBox_1.getSelectedIndex()][comboBox_2.getSelectedIndex()].setDisabled(false);
					client.send(new MessageReportDisabledPSpace(arr,false));
					MessageReportDisabledPSpaceReply mrdps = (MessageReportDisabledPSpaceReply)client.getMessage();
					mrdps.doAction();
				}
				return;	
			}
		});

		btnEnablePs.setBounds(257, 140, 168, 66);
		add(btnEnablePs);
		JButton btnReportAsDisabled = new JButton("Report as disabled");
		btnReportAsDisabled.setBounds(257, 56, 168, 73);
		add(btnReportAsDisabled);
		btnReportAsDisabled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String arr[] = new String[4];
				arr[0]=Integer.toString(worker.getParkingLotID());
				arr[1]=Integer.toString(comboBox.getSelectedIndex());
				arr[2]=Integer.toString(comboBox_1.getSelectedIndex());
				arr[3]=Integer.toString(comboBox_2.getSelectedIndex());
				//check if user can disable the parking space
				if (parkinglots[worker.getParkingLotID()].getParkingspace()[comboBox.getSelectedIndex()][comboBox_1.getSelectedIndex()][comboBox_2.getSelectedIndex()].isDisabled() || parkinglots[worker.getParkingLotID()].getParkingspace()[comboBox.getSelectedIndex()][comboBox_1.getSelectedIndex()][comboBox_2.getSelectedIndex()].isOccupied() || parkinglots[worker.getParkingLotID()].getParkingspace()[comboBox.getSelectedIndex()][comboBox_1.getSelectedIndex()][comboBox_2.getSelectedIndex()].isReserved()){
					JOptionPane.showMessageDialog(null, "Unable to disable this parkingspace. This parking space may be reserved/occupied or already disabled ");
					return;
				}
				else
				{
					parkinglots[worker.getParkingLotID()].getParkingspace()[comboBox.getSelectedIndex()][comboBox_1.getSelectedIndex()][comboBox_2.getSelectedIndex()].setDisabled(true);
					client.send(new MessageReportDisabledPSpace(arr,true));
					MessageReportDisabledPSpaceReply mrdps = (MessageReportDisabledPSpaceReply)client.getMessage();
					mrdps.doAction();
				}
			}

		});


	}
}
