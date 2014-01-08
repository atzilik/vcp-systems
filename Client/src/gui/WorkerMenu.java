package gui;


import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;


import DataObjects.CeoWorker;
import DataObjects.CustomerService;
import DataObjects.ParkingLot;
import DataObjects.ParkingLotManager;
import DataObjects.Worker;
import Messages.MessageGetParkingLotsID;
import Messages.MessageGetParkingLotsIDReply;


public class WorkerMenu extends AbstractGUIComponent {
	private Worker wkr;
	private IGUINavigator navigator;
	/**
	 * 
	 * @param wkr
	 */
	public WorkerMenu(final IGUINavigator navigator, Worker wkr){
		
		setLayout(null);
		this.wkr  = wkr;
		this.navigator=navigator;
		setSize(477, 320);
		JLabel lblNewLabel = new JLabel("Hello" + " " +wkr.getfName() + " " + wkr.getlName());
		lblNewLabel.setBounds(121, 11, 123, 14);
		add(lblNewLabel);
		
		String wkrType = null;
		
		if (wkr instanceof ParkingLotManager)
		{
			wkrType = "ParkingLot Manager";
			openParkingLotManagerMenu();
		}
		else if (wkr instanceof CeoWorker)
		{
			wkrType = "CEO";
			openCEOMenu();
		}
		else if (wkr instanceof CustomerService)
		{
			wkrType = "CustomerService";
			openCSMenu();
		}
		else
		{
			wkrType = "Worker";
			openWorkerMenu();
		}
		JLabel lblNewLabel1 = new JLabel(wkrType);
		lblNewLabel1.setBounds(10, 11, 134, 14);
		add(lblNewLabel1);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToWorkerLogin();
			}
		});
		btnCancel.setBounds(173, 265, 89, 23);
		add(btnCancel);
		
	}

	
	public void openWorkerMenu(){
		
	JButton btn_ReserverLocalParkingSpace = new JButton("<html>Reserve Local<br />Parking Space</html>");
		btn_ReserverLocalParkingSpace.setVerticalAlignment(SwingConstants.TOP);
		btn_ReserverLocalParkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToReserveParkingSpace();
			}
		});
		btn_ReserverLocalParkingSpace.setBounds(99, 51, 145, 48);
		add(btn_ReserverLocalParkingSpace);

		JButton btn_ReportDisabledParkingSpace = new JButton("<html>Report Disabled<br />Parking Space</html>");
		btn_ReportDisabledParkingSpace.setVerticalAlignment(SwingConstants.TOP);
		btn_ReportDisabledParkingSpace.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		navigator.goToReportDisabledParkingSpace(wkr);
		}
	});
		btn_ReportDisabledParkingSpace.setBounds(99, 110, 145, 58);
		add(btn_ReportDisabledParkingSpace);

		JButton btn_ReportDisabledFacility = new JButton("<html>Report Disabled<br />Facility</html>");
		btn_ReportDisabledFacility.setVerticalAlignment(SwingConstants.TOP);
		btn_ReportDisabledFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_ReportDisabledFacility.setBounds(261, 51, 138, 48);
		add(btn_ReportDisabledFacility);

		JButton btn_InsertAlternativeParkingLot = new JButton("<html>Insert Alternative<br />Parking Lot</html>");
		btn_InsertAlternativeParkingLot.setVerticalAlignment(SwingConstants.TOP);
		btn_InsertAlternativeParkingLot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.send(new MessageGetParkingLotsID());
				MessageGetParkingLotsIDReply gplir= (MessageGetParkingLotsIDReply)client.getMessage();
				navigator.goToInsertAlternativeParkingLot(gplir.getMp(),wkr);
			}
		});
		btn_InsertAlternativeParkingLot.setBounds(261, 110, 138, 58);
		add(btn_InsertAlternativeParkingLot);

		
		JButton btnSetupParkingLot = new JButton("<html>Setup<br />Parking Lot</html>");
		btnSetupParkingLot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToWorkerSetupMenu(wkr.getParkingLotID());
			}
		});
		btnSetupParkingLot.setVerticalAlignment(SwingConstants.TOP);
		btnSetupParkingLot.setBounds(166, 179, 123, 61);
		add(btnSetupParkingLot);
	}
	
	public void openParkingLotManagerMenu(){

		JButton btnChangeRates = new JButton("<html>Open Change<br />Rates Menu</html> ");
		btnChangeRates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToChangeRates();
			}
		});
		btnChangeRates.setBounds(38, 94, 106, 45);
		add(btnChangeRates);

		JButton btnreservationreport = new JButton("<html>Reservation<br />report</html> ");
		btnreservationreport.setBounds(157, 94, 106, 45);
		add(btnreservationreport);

		JButton btncomplaintsreport = new JButton("<html>Complaints<br />report</html> ");
		btncomplaintsreport.setBounds(38, 161, 106, 45);
		add(btncomplaintsreport);

		JButton btndisabledreport = new JButton("<html>Disabled<br />report</html> ");
		btndisabledreport.setBounds(157, 161, 106, 45);
		add(btndisabledreport);
		
	}
	public void openCSMenu(){
		JButton btnReserveParkingSpace = new JButton("<html>Reserve<br />parking space</html>");
		btnReserveParkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToReserveParkingSpace();
			}
		});
		btnReserveParkingSpace.setBounds(46, 90, 117, 52);
		add(btnReserveParkingSpace);

		JButton btncomplaintmenu = new JButton("<html>Complaint<br />menu</html>");
		btncomplaintmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToComplaintMenu();
			}
		});
		btncomplaintmenu.setBounds(112, 161, 117, 52);
		add(btncomplaintmenu);
		JButton btnreserveLocalparkingSpace = new JButton("<html>Reserve local<br />parking space</html>");
		/*btnreserveLocalparkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ReserveLocalparkingSpace");
			}
		});*/
		btnreserveLocalparkingSpace.setBounds(173, 90, 117, 52);
		add(btnreserveLocalparkingSpace);
	}
	public void openCEOMenu(){
		JButton btnNewButton = new JButton("<html>Activity<br />report</html>");
		btnNewButton.setBounds(23, 71, 102, 49);
		add(btnNewButton);

		JButton button = new JButton("<html>Snapshot<br />report</html>");
		button.setBounds(23, 11, 102, 49);
		add(button);

		JButton btnexceptionsreport = new JButton("<html>Exceptions<br />report</html>");
		btnexceptionsreport.setBounds(23, 141, 102, 49);
		add(btnexceptionsreport);

		JButton btnperformancereport = new JButton("<html>Performance<br />report</html>");
		btnperformancereport.setBounds(23, 201, 102, 49);
		add(btnperformancereport);

		JButton btnreservationdata = new JButton("<html>Reservation<br />data</html>");
		btnreservationdata.setBounds(205, 201, 102, 49);
		add(btnreservationdata);

		JButton btnchangeRatesrequests = new JButton("<html>Change Rates<br />requests</html>");
		btnchangeRatesrequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToChangeRatesReq();
			}
		});
		btnchangeRatesrequests.setBounds(220, 11, 102, 49);
		add(btnchangeRatesrequests);


		JButton btnworkersdata = new JButton("<html>Workers<br />data</html>");
		btnworkersdata.setBounds(205, 147, 102, 49);
		add(btnworkersdata);
	}
}
