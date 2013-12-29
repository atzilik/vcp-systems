package gui;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import logic.Worker;
import logic.CeoWorker;
import logic.CustomerService;
import logic.ParkingLotManager;
public class WorkerMenu extends Frame {
	private Worker wkr;
	/**
	 * 
	 * @param wkr
	 */
	public WorkerMenu(Worker wkr){
		super();
		this.wkr  = wkr;
		setTitle("Staff Menu");
		setSize(477, 320);
		if (wkr instanceof ParkingLotManager)
		{
			openParkingLotManagerMenu();
		}
		else if (wkr instanceof CeoWorker)
		{
			openCEOMenu();
		}
		else if (wkr instanceof CustomerService)
		{
			openCSMenu();
		}
		else
		{
			openWorkerMenu();
		}
	}
	public void openWorkerMenu(){
		JButton btn_ReserverLocalParkingSpace = new JButton("<html>Reserve Local<br />Parking Space</html>");
		btn_ReserverLocalParkingSpace.setVerticalAlignment(SwingConstants.TOP);
		/*btn_ReserverLocalParkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ReserveLocalParkingSpace");
			}
		});*/
		btn_ReserverLocalParkingSpace.setBounds(99, 51, 145, 48);
		getContentPane().add(btn_ReserverLocalParkingSpace);

		JButton btn_ReportDisabledParkingSpace = new JButton("<html>Report Disabled<br />Parking Space</html>");
		btn_ReportDisabledParkingSpace.setVerticalAlignment(SwingConstants.TOP);
		btn_ReportDisabledParkingSpace.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			openWindow("btn_ReportDisabledParkingSpace");
		}
	});
		btn_ReportDisabledParkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ReportDisabledParkingSpace");
			}
		});
		btn_ReportDisabledParkingSpace.setBounds(99, 110, 145, 58);
		getContentPane().add(btn_ReportDisabledParkingSpace);

		JButton btn_ReportDisabledFacility = new JButton("<html>Report Disabled<br />Facility</html>");
		btn_ReportDisabledFacility.setVerticalAlignment(SwingConstants.TOP);
		btn_ReportDisabledFacility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ReportDisabledFacility");
			}
		});
		btn_ReportDisabledFacility.setBounds(261, 51, 138, 48);
		getContentPane().add(btn_ReportDisabledFacility);

		JButton btn_InsertAlternativeParkingLot = new JButton("<html>Insert Alternative<br />Parking Lot</html>");
		btn_InsertAlternativeParkingLot.setVerticalAlignment(SwingConstants.TOP);
		btn_InsertAlternativeParkingLot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("InsertAlternativeParkingLot");
			}
		});
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

	public void openParkingLotManagerMenu(){

		JButton btnChangeRates = new JButton("<html>Open Change<br />Rates Menu</html> ");
		btnChangeRates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ChangeRates");
			}
		});
		btnChangeRates.setBounds(38, 94, 106, 45);
		getContentPane().add(btnChangeRates);

		JButton btnreservationreport = new JButton("<html>Reservation<br />report</html> ");
		btnreservationreport.setBounds(157, 94, 106, 45);
		getContentPane().add(btnreservationreport);

		JButton btncomplaintsreport = new JButton("<html>Complaints<br />report</html> ");
		btncomplaintsreport.setBounds(38, 161, 106, 45);
		getContentPane().add(btncomplaintsreport);

		JButton btndisabledreport = new JButton("<html>Disabled<br />report</html> ");
		btndisabledreport.setBounds(157, 161, 106, 45);
		getContentPane().add(btndisabledreport);
	}
	public void openCSMenu(){
		JButton btnReserveParkingSpace = new JButton("<html>Reserve<br />parking space</html>");
		btnReserveParkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ReserveParkingSpace");
			}
		});
		btnReserveParkingSpace.setBounds(46, 90, 117, 52);
		getContentPane().add(btnReserveParkingSpace);

		JButton btncomplaintmenu = new JButton("<html>Complaint<br />menu</html>");
		btncomplaintmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ComplaintMenu");
			}
		});
		btncomplaintmenu.setBounds(112, 161, 117, 52);
		getContentPane().add(btncomplaintmenu);
		JButton btnreserveLocalparkingSpace = new JButton("<html>Reserve local<br />parking space</html>");
		/*btnreserveLocalparkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ReserveLocalparkingSpace");
			}
		});*/
		btnreserveLocalparkingSpace.setBounds(173, 90, 117, 52);
		getContentPane().add(btnreserveLocalparkingSpace);
	}
	public void openCEOMenu(){
		JButton btnNewButton = new JButton("<html>Activity<br />report</html>");
		btnNewButton.setBounds(23, 71, 102, 49);
		getContentPane().add(btnNewButton);

		JButton button = new JButton("<html>Snapshot<br />report</html>");
		button.setBounds(23, 11, 102, 49);
		getContentPane().add(button);

		JButton btnexceptionsreport = new JButton("<html>Exceptions<br />report</html>");
		btnexceptionsreport.setBounds(23, 141, 102, 49);
		getContentPane().add(btnexceptionsreport);

		JButton btnperformancereport = new JButton("<html>Performance<br />report</html>");
		btnperformancereport.setBounds(23, 201, 102, 49);
		getContentPane().add(btnperformancereport);

		JButton btnreservationdata = new JButton("<html>Reservation<br />data</html>");
		btnreservationdata.setBounds(205, 201, 102, 49);
		getContentPane().add(btnreservationdata);

		JButton btnchangeRatesrequests = new JButton("<html>Change Rates<br />requests</html>");
		btnchangeRatesrequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openWindow("ChangeRatesReq");
			}
		});
		btnchangeRatesrequests.setBounds(220, 11, 102, 49);
		getContentPane().add(btnchangeRatesrequests);


		JButton btnworkersdata = new JButton("<html>Workers<br />data</html>");
		btnworkersdata.setBounds(205, 147, 102, 49);
		getContentPane().add(btnworkersdata);
	}


	public void openWindow(String str){
		setVisible(false);

		switch(str)
		{

		case "ChangeRates":{
			ChangeRateMenu crm = new ChangeRateMenu();
			setVisible(false);
			crm.setVisible(true);
			break;
		}
		case "ChangeRatesReq":{
			RateRequests rateReq = new RateRequests();
			setVisible(false);
			rateReq.setVisible(true);
			break;
		}
		case "ReserveParkingSpace":{
			ReserveParkingSpace rps = new ReserveParkingSpace();
			setVisible(false);
			rps.setVisible(true);
			break;
		}
		case "ComplaintMenu":{
			ComplaintMenu cm = new ComplaintMenu();
			setVisible(false);
			cm.setVisible(true);
			break;
		}
		/*case "ReserveLocalparkingSpace":{
			ComplaintMenu cm = new ComplaintMenu();
			setVisible(false);
			cm.setVisible(true);
			break;
		}*/
		case("btn_ReportDisabledParkingSpace"):{
			ReportDisabledParkingSpace rdps = new ReportDisabledParkingSpace();
			setVisible(false);
			rdps.setVisible(true);
			break;
		}
		case("ReportDisabledFacility"):{
			ReportDisabledFacility rdf = new ReportDisabledFacility();
			setVisible(false);
			rdf.setVisible(true);
			break;	
		}
		case ("InsertAlternativeParkingLot"):{
			InsertAlternativeParkingLot iapl = new InsertAlternativeParkingLot();
			setVisible(false);
			iapl.setVisible(true);
			break;
		}
	}
}
}
