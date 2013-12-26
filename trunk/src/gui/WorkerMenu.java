package gui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import logic.Worker;

public class WorkerMenu extends Frame {
	private Worker wkr;
	public WorkerMenu(Worker wkr){
		super();
		this.wkr=wkr;
		setTitle("Worker Menu");
		setSize(477, 320);
		JButton btn_ReserverLocalParkingSpace = new JButton("<html>Reserve Local<br />Parking Space</html>");
		btn_ReserverLocalParkingSpace.setVerticalAlignment(SwingConstants.TOP);
		btn_ReserverLocalParkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btn_ReserverLocalParkingSpace.setBounds(99, 51, 145, 48);
		getContentPane().add(btn_ReserverLocalParkingSpace);

		JButton btn_ReportDisabledParkingSpace = new JButton("<html>Report Disabled<br />Parking Space</html>");
		btn_ReportDisabledParkingSpace.setVerticalAlignment(SwingConstants.TOP);
		btn_ReportDisabledParkingSpace.setBounds(99, 110, 145, 58);
		getContentPane().add(btn_ReportDisabledParkingSpace);

		JButton btn_ReportDisabledFacility = new JButton("<html>Report Disabled<br />Facility</html>");
		btn_ReportDisabledFacility.setVerticalAlignment(SwingConstants.TOP);
		btn_ReportDisabledFacility.setBounds(261, 51, 138, 48);
		getContentPane().add(btn_ReportDisabledFacility);

		JButton btn_InsertAlternativeParkingLot = new JButton("<html>Insert Alternative<br />Parking Lot</html>");
		btn_InsertAlternativeParkingLot.setVerticalAlignment(SwingConstants.TOP);
		btn_InsertAlternativeParkingLot.setBounds(261, 110, 138, 58);
		getContentPane().add(btn_InsertAlternativeParkingLot);

		JButton btnSetupParkingLot = new JButton("<html>Setup<br />Parking Lot</html>");
		btnSetupParkingLot.setVerticalAlignment(SwingConstants.TOP);
		btnSetupParkingLot.setBounds(166, 179, 123, 61);
		getContentPane().add(btnSetupParkingLot);

		JLabel lblNewLabel = new JLabel("Hello" + " " +wkr.getfName() + " " + wkr.getlName());
		lblNewLabel.setBounds(121, 11, 123, 14);
		getContentPane().add(lblNewLabel);
	}
}
