package gui;


import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;


import DataObjects.CeoWorker;
import DataObjects.CustomerService;
import DataObjects.DataObjectMessageToUser;
import DataObjects.ParkingLot;
import DataObjects.ParkingLotManager;
import DataObjects.Worker;
import Messages.MessageGetMessage;
import Messages.MessageGetMessageReply;
import Messages.MessageGetParkingLotsID;
import Messages.MessageGetParkingLotsIDReply;

/**
 * 
 * @param wkr variable which will help indicate the relevant worker using the program
 * @param navigator for navigating between the panels
 */
public class WorkerMenu extends AbstractGUIComponent {
	private Worker wkr;
	private IGUINavigator navigator;

	/**
	 * 
	 * @param navigator navigate between panels
	 * @param wkr worker instance
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
		btnCancel.setBounds(250, 265, 89, 23);
		add(btnCancel);

		// check if user have message and print
		client.send(new MessageGetMessage(wkr.getId()));
		MessageGetMessageReply msgReplay = (MessageGetMessageReply)client.getMessage();

		for(DataObjectMessageToUser msg: msgReplay.getMsgArr())
			JOptionPane.showMessageDialog(null, msg.getMsg());


	}

	/**
	 * open the regular worker menu
	 */
	public void openWorkerMenu(){

		JButton btn_ReserveLocalParkingSpace = new JButton("<html>Reserve Local<br />Parking Space</html>");
		btn_ReserveLocalParkingSpace.setVerticalAlignment(SwingConstants.TOP);
		btn_ReserveLocalParkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToReserveParkingSpace(null,wkr);
			}
		});
		btn_ReserveLocalParkingSpace.setBounds(99, 51, 145, 48);
		add(btn_ReserveLocalParkingSpace);

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
				navigator.goToEnableDisableFacility(wkr);		}
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
				navigator.goToWorkerSetupMenu(wkr.getParkingLotID(),wkr);
			}
		});
		btnSetupParkingLot.setVerticalAlignment(SwingConstants.TOP);
		btnSetupParkingLot.setBounds(166, 179, 123, 61);
		add(btnSetupParkingLot);
	}
/**
 * open the parking lot manager menu
 */
	public void openParkingLotManagerMenu(){

		JButton btnChangeRates = new JButton("<html>Open Change<br />Rates Menu</html> ");
		btnChangeRates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToChangeRatesReq(wkr);
			}
		});
		btnChangeRates.setBounds(38, 94, 106, 45);
		add(btnChangeRates);

		JButton btnreservationreport = new JButton("<html>Reservation<br />report</html> ");
		btnreservationreport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				navigator.goToReservationDataMenuParkingLot(wkr, null);
			}
		});
		btnreservationreport.setBounds(157, 94, 106, 45);
		add(btnreservationreport);

		JButton btncomplaintsreport = new JButton("<html>Complaints<br />report</html> ");
		btncomplaintsreport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				navigator.goToComplaintReportMenu(wkr,null);

			}
		});
		btncomplaintsreport.setBounds(38, 161, 106, 45);
		add(btncomplaintsreport);

		JButton btndisabledreport = new JButton("<html>Disabled<br />report</html> ");
		btndisabledreport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				navigator.goToDisabledReport(wkr,null);
			}
		});
		btndisabledreport.setBounds(157, 161, 106, 45);
		add(btndisabledreport);

	}
	/**
	 * open the customer service menu
	 */
	public void openCSMenu(){
		JButton btnReserveParkingSpace = new JButton("<html>Reserve<br />parking space</html>");
		btnReserveParkingSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.send(new MessageGetParkingLotsID());
				MessageGetParkingLotsIDReply gplir= (MessageGetParkingLotsIDReply)client.getMessage();
				navigator.goToReserveParkingSpace(gplir.getMp(),wkr);
			}
		});
		btnReserveParkingSpace.setBounds(46, 90, 117, 52);
		add(btnReserveParkingSpace);

		JButton btncomplaintmenu = new JButton("<html>Complaint<br />menu</html>");
		btncomplaintmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToComplaintMenu(wkr);
			}
		});
		btncomplaintmenu.setBounds(112, 161, 117, 52);
		add(btncomplaintmenu);

	}
	/**
	 * open the CEO menu
	 */
	public void openCEOMenu(){
		JButton btnActivityReport = new JButton("<html>Activity<br />report</html>");
		btnActivityReport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				navigator.goToActivityReportMenu(wkr);

			}
		});
		btnActivityReport.setBounds(137, 186, 84, 52);
		add(btnActivityReport);

		JButton btnSnapShot = new JButton("<html>Snapshot<br />report</html>");
		btnSnapShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.send(new MessageGetParkingLotsID());
				MessageGetParkingLotsIDReply gplir= (MessageGetParkingLotsIDReply)client.getMessage();
				navigator.goToSnapShotReport(gplir.getMp());

			}
		});
		btnSnapShot.setBounds(10, 102, 155, 52);
		add(btnSnapShot);

		JButton btnexceptionsreport = new JButton("<html>Exceptions<br />report</html>");
		btnexceptionsreport.setBounds(176, 97, 130, 62);
		add(btnexceptionsreport);

		JButton btnperformancereport = new JButton("<html>Performance<br />report</html>");
		btnperformancereport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				navigator.goToPerformenceReportMenu(wkr);
				
			}
		});
		btnperformancereport.setBounds(316, 107, 124, 52);
		add(btnperformancereport);

		JButton btnreservationdata = new JButton("<html>Reservation<br />data</html>");
		btnreservationdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToreservationsDataMenu(wkr);
			}
		});
		btnreservationdata.setBounds(349, 186, 91, 52);
		add(btnreservationdata);

		JButton btnchangeRatesrequests = new JButton("<html>Change Rates<br />requests</html>");
		btnchangeRatesrequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToChangeRates(wkr);
			}
		});
		btnchangeRatesrequests.setBounds(26, 186, 101, 52);
		add(btnchangeRatesrequests);


		JButton btnworkersdata = new JButton("<html>Workers<br />data</html>");
		btnworkersdata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				navigator.goToWorkerDataMenu(wkr);
			}
		});
		btnworkersdata.setBounds(231, 186, 108, 52);
		add(btnworkersdata);

		JButton btnReports = new JButton("View Parking lots reports");
		btnReports.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.send(new MessageGetParkingLotsID());
				MessageGetParkingLotsIDReply gplir= (MessageGetParkingLotsIDReply)client.getMessage();
				navigator.goToCEOChoosePLReport(gplir.getMp(),wkr);
			}
		});
		btnReports.setBounds(21, 250, 180, 52);
		add(btnReports);

	}
}
