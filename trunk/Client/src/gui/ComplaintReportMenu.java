package gui;
//**********************************************************************
//Import list
//**********************************************************************

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import DataObjects.FullComplaint;
import DataObjects.Reservation;
import DataObjects.Worker;
import Messages.MessageGetComplaintData;
import Messages.MessageGetCompaintDataReply;
import Messages.MessageGetReservationData;
import Messages.MessageGetReservationDataReply;

import com.toedter.calendar.JDateChooser;

/**
 * 
 * @author alon
 *This class is responsible for report of the complaint.
 */
public class ComplaintReportMenu extends AbstractGUIComponent {

	private Worker wkr;
/**
 * 
 * @param navigator navigate between panels
 * @param wkr
 */
	public ComplaintReportMenu(final IGUINavigator navigator, final Worker wkr, final Worker workerToBack)
	{
		super();	
		setLayout(null);
		this.wkr = wkr;
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(28, 52, 46, 14);
		add(lblFrom);
		
		JLabel lblTo = new JLabel("to:");
		lblTo.setBounds(158, 52, 46, 14);
		add(lblTo);
		
		JLabel lblComplaintReportPlease = new JLabel("Complaint Report, Please Choose date range:");
		lblComplaintReportPlease.setBounds(28, 27, 246, 14);
		add(lblComplaintReportPlease);
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(61, 46, 87, 20);
		add(dateChooser);
		
		final JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(178, 46, 87, 20);
		add(dateChooser_1);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MessageGetComplaintData msg = new MessageGetComplaintData(dateChooser.getDate(),dateChooser_1.getDate(),wkr.getParkingLotID());
				client.send(msg);
				MessageGetCompaintDataReply replay = (MessageGetCompaintDataReply)client.getMessage();
				if (replay.getComplaintsArray() != null)
					PrintTable(replay.getComplaintsArray());
				
			}
		});
		btnGenerate.setBounds(275, 43, 87, 23);
		add(btnGenerate);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				navigator.goToComplaintReportMenu(wkr,workerToBack);
				
			}
		});
		
		btnReset.setBounds(362, 43, 89, 23);
		add(btnReset);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(158, 249, 89, 23);
		btnGoBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(workerToBack == null)
					navigator.goToWorkerMenu(wkr);
				else
					navigator.goToWorkerMenu(workerToBack);
			}
		});
		add(btnGoBack);
	}
	/**
	 * 
	 * @param complaints
	 * array list is all the data of the complaints
	 */
private void PrintTable(ArrayList<FullComplaint> complaints) {
		
		
		int length = complaints.size();
		Object[][] data = new Object[length+1][8];
		int i=0;
		
		data[i][0] = "complaintID";
		data[i][1] = "customerID";
		data[i][2] = "details";
		data[i][3] = "dateRecv";
		data[i][4] = "refund";
		data[i][5] = "dateHandled";
		data[i][6] = "workerID";
		data[i][7] = "answer";
		
		i++;
		
		for(FullComplaint comp: complaints)
		{
			data[i][0] = comp.getComplaintID();
			data[i][1] = comp.getCustomerID();
			data[i][2] = comp.getDetails();
			data[i][3] = comp.getDateRecv();
			if(comp.getDateHandled()!=null)
			{
				data[i][4] = comp.getRefund();
				data[i][5] = comp.getDateHandled();
				data[i][6] = comp.getWorkerID();
				data[i][7] = comp.getAnswer();
			}
			else
			{
				data[i][4] = "";
				data[i][5] = "";
				data[i][6] = "";
				data[i][7] = "";
			}
			
			i++;
		}
		
		String[] columnNames = {"complaintID",
                "customerID",
                "details",
                "dateRecv",
                "refund",
                "dateHandled",
                "workerID",
                "answer"};
		
		JTable complaintTable = new JTable(data , columnNames);
		complaintTable.setBounds(new Rectangle(10, 100, 620, i*23));
	//	complaintTable.getColumnModel().getColumn(1).setPreferredWidth(35);
	//	complaintTable.setRowHeight(23);
		

		complaintTable.setVisible(true);
		
		this.add(complaintTable,null);
		
		this.revalidate();
		this.repaint();
	}
}
