package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

import DataObjects.Worker;
import Messages.MessageGetDetailsForActivityReport;
import Messages.MessageGetDetailsForActivityReportReply;
/**
 * Produces a periodic report showing the number of orders, and the disabled parking spaces
 * @author Omri
 * 
 */
public class ActivityReportMenu extends AbstractGUIComponent {
	/**
	 *wkr instance
	 */
	private Worker wkr;

	private int usedResTotal;
	private int usedResMed;
	private int usedResAvg;
	private int usedResFreq[];
	
	private int cancelResTotal;
	private int cancelResMed;
	private int cancelResAvg;
	private int cancelResFreq[];
	
	private int disableTotal;
	private int disableMed;
	private int disableAvg;
	private int disableFreq[];
	
	/**
	 * create the activity report menu
	 * @param navigator
	 * @param worker
	 */
	public ActivityReportMenu(final IGUINavigator navigator,  Worker worker) {
		super();	
		wkr = worker;
		setLayout(null);
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(28, 52, 46, 14);
		add(lblFrom);
		
		JLabel lblComplaintReportPlease = new JLabel("Please choose date and type of report:");
		lblComplaintReportPlease.setBounds(28, 27, 500, 14);
		add(lblComplaintReportPlease);
		
		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(61, 46, 87, 20);
		add(dateChooser);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(158, 249, 89, 23);
		btnGoBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				navigator.goToWorkerMenu(wkr);
			}
		});
		add(btnGoBack);
		
		JButton btnWeekly = new JButton("Weekly");
		btnWeekly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MessageGetDetailsForActivityReport msgUsedRes = new MessageGetDetailsForActivityReport(7, dateChooser.getDate(), 3);
				client.send(msgUsedRes);
				MessageGetDetailsForActivityReportReply replyUsedRes = (MessageGetDetailsForActivityReportReply)client.getMessage();
				if(replyUsedRes.isOk())
				{
					usedResTotal = replyUsedRes.getTotal();
					usedResMed = replyUsedRes.getMed();
					usedResAvg = replyUsedRes.getAvg();
					usedResFreq = replyUsedRes.getFreq();
					
					MessageGetDetailsForActivityReport msgCancelRes = new MessageGetDetailsForActivityReport(7, dateChooser.getDate(), 4);
					client.send(msgCancelRes);
					MessageGetDetailsForActivityReportReply replymsgCancel = (MessageGetDetailsForActivityReportReply)client.getMessage();
					cancelResTotal = replymsgCancel.getTotal();
					cancelResMed = replymsgCancel.getMed();
					cancelResAvg = replymsgCancel.getAvg();
					cancelResFreq = replymsgCancel.getFreq();
					
					MessageGetDetailsForActivityReport msgDisabled = new MessageGetDetailsForActivityReport(7, dateChooser.getDate(), 10);
					client.send(msgDisabled);
					MessageGetDetailsForActivityReportReply replyDisabled = (MessageGetDetailsForActivityReportReply)client.getMessage();
					disableTotal = replyDisabled.getTotal();
					disableMed = replyDisabled.getMed();
					disableAvg = replyDisabled.getAvg();
					disableFreq = replyDisabled.getFreq();
					
					PrintTable();
				}
			}
			
		});
		btnWeekly.setBounds(275, 43, 87, 23);
		add(btnWeekly);
		
		JButton btnMonthly = new JButton("Monthly");
		btnMonthly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MessageGetDetailsForActivityReport msgUsedRes = new MessageGetDetailsForActivityReport(30, dateChooser.getDate(), 3);
				client.send(msgUsedRes);
				MessageGetDetailsForActivityReportReply replyUsedRes = (MessageGetDetailsForActivityReportReply)client.getMessage();
				if(replyUsedRes.isOk())
				{
					usedResTotal = replyUsedRes.getTotal();
					usedResMed = replyUsedRes.getMed();
					usedResAvg = replyUsedRes.getAvg();
					usedResFreq = replyUsedRes.getFreq();
					
					MessageGetDetailsForActivityReport msgCancelRes = new MessageGetDetailsForActivityReport(30, dateChooser.getDate(), 4);
					client.send(msgCancelRes);
					MessageGetDetailsForActivityReportReply replymsgCancel = (MessageGetDetailsForActivityReportReply)client.getMessage();
					cancelResTotal = replymsgCancel.getTotal();
					cancelResMed = replymsgCancel.getMed();
					cancelResAvg = replymsgCancel.getAvg();
					cancelResFreq = replymsgCancel.getFreq();
					
					MessageGetDetailsForActivityReport msgDisabled = new MessageGetDetailsForActivityReport(30, dateChooser.getDate(), 10);
					client.send(msgDisabled);
					MessageGetDetailsForActivityReportReply replyDisabled = (MessageGetDetailsForActivityReportReply)client.getMessage();
					disableTotal = replyDisabled.getTotal();
					disableMed = replyDisabled.getMed();
					disableAvg = replyDisabled.getAvg();
					disableFreq = replyDisabled.getFreq();
					
					PrintTable();
				}
			}
			
		});
		btnMonthly.setBounds(367, 43, 87, 23);
		add(btnMonthly);
		
		JButton btnQuarterly = new JButton("Quarterly");
		btnQuarterly.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MessageGetDetailsForActivityReport msgUsedRes = new MessageGetDetailsForActivityReport(90, dateChooser.getDate(), 3);
				client.send(msgUsedRes);
				MessageGetDetailsForActivityReportReply replyUsedRes = (MessageGetDetailsForActivityReportReply)client.getMessage();
				if(replyUsedRes.isOk())
				{
					usedResTotal = replyUsedRes.getTotal();
					usedResMed = replyUsedRes.getMed();
					usedResAvg = replyUsedRes.getAvg();
					usedResFreq = replyUsedRes.getFreq();
					
					MessageGetDetailsForActivityReport msgCancelRes = new MessageGetDetailsForActivityReport(90, dateChooser.getDate(), 4);
					client.send(msgCancelRes);
					MessageGetDetailsForActivityReportReply replymsgCancel = (MessageGetDetailsForActivityReportReply)client.getMessage();
					cancelResTotal = replymsgCancel.getTotal();
					cancelResMed = replymsgCancel.getMed();
					cancelResAvg = replymsgCancel.getAvg();
					cancelResFreq = replymsgCancel.getFreq();
					
					MessageGetDetailsForActivityReport msgDisabled = new MessageGetDetailsForActivityReport(90, dateChooser.getDate(), 10);
					client.send(msgDisabled);
					MessageGetDetailsForActivityReportReply replyDisabled = (MessageGetDetailsForActivityReportReply)client.getMessage();
					disableTotal = replyDisabled.getTotal();
					disableMed = replyDisabled.getMed();
					disableAvg = replyDisabled.getAvg();
					disableFreq = replyDisabled.getFreq();
					
					PrintTable();
				}
			}
			
		});
		btnQuarterly.setBounds(459, 43, 87, 23);
		add(btnQuarterly);
	
		
		
		
	}
	/**
	 * print table with report data
	 */
private void PrintTable() {
		
		
		Object[][] data = new Object[4][14];
		int i=0;
		
		data[i][0] = "";
		data[i][1] = "Total";
		data[i][2] = "Avg";
		data[i][3] = "Med";
		data[i][4] = "1";
		data[i][5] = "2";
		data[i][6] = "3";
		data[i][7] = "4";
		data[i][8] = "5";
		data[i][9] = "6";
		data[i][10] = "7";
		data[i][11] = "8";
		data[i][12] = "9";
		data[i][13] = "10";
		
		data[1][0] = "Reservation Used";
		data[2][0] = "Reservation Canceled";
		data[3][0] = "disabled Parking Space";

		
		i++;
		
		data[i][1] = String.valueOf(usedResTotal);
		data[i][2] = String.valueOf(usedResAvg);
		data[i][3] = String.valueOf(usedResMed);
		data[i][4] = String.valueOf(usedResFreq[0]);
		data[i][5] = String.valueOf(usedResFreq[1]);
		data[i][6] = String.valueOf(usedResFreq[2]);
		data[i][7] = String.valueOf(usedResFreq[3]);
		data[i][8] = String.valueOf(usedResFreq[4]);
		data[i][9] = String.valueOf(usedResFreq[5]);
		data[i][10] = String.valueOf(usedResFreq[6]);
		data[i][11] = String.valueOf(usedResFreq[7]);
		data[i][12] = String.valueOf(usedResFreq[8]);
		data[i][13] = String.valueOf(usedResFreq[9]);
			
		i++;
		
		data[i][1] = String.valueOf(cancelResTotal);
		data[i][2] = String.valueOf(cancelResAvg);
		data[i][3] = String.valueOf(cancelResMed);
		data[i][4] = String.valueOf(cancelResFreq[0]);
		data[i][5] = String.valueOf(cancelResFreq[1]);
		data[i][6] = String.valueOf(cancelResFreq[2]);
		data[i][7] = String.valueOf(cancelResFreq[3]);
		data[i][8] = String.valueOf(cancelResFreq[4]);
		data[i][9] = String.valueOf(cancelResFreq[5]);
		data[i][10] = String.valueOf(cancelResFreq[6]);
		data[i][11] = String.valueOf(cancelResFreq[7]);
		data[i][12] = String.valueOf(cancelResFreq[8]);
		data[i][13] = String.valueOf(cancelResFreq[9]);
			
		i++;
		
		data[i][1] = String.valueOf(disableTotal);
		data[i][2] = String.valueOf(disableAvg);
		data[i][3] = String.valueOf(disableMed);
		data[i][4] = String.valueOf(disableFreq[0]);
		data[i][5] = String.valueOf(disableFreq[1]);
		data[i][6] = String.valueOf(disableFreq[2]);
		data[i][7] = String.valueOf(disableFreq[3]);
		data[i][8] = String.valueOf(disableFreq[4]);
		data[i][9] = String.valueOf(disableFreq[5]);
		data[i][10] = String.valueOf(disableFreq[6]);
		data[i][11] = String.valueOf(disableFreq[7]);
		data[i][12] = String.valueOf(disableFreq[8]);
		data[i][13] = String.valueOf(disableFreq[9]);
			
		i++;
		
		String[] columnNames = {"",
                "Total",
                "Avg",
                "Med",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10"};
		
		JTable activityTable = new JTable(data , columnNames);
		activityTable.setBounds(new Rectangle(10, 100, 620, i*23));
		activityTable.getColumnModel().getColumn(0).setPreferredWidth(400);
	//	complaintTable.setRowHeight(23);
		

		activityTable.setVisible(true);
		
		this.add(activityTable,null);
		
		this.revalidate();
		this.repaint();
	}

}
