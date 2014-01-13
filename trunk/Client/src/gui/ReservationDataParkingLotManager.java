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
 * @author omri
 *This class is responsible for generate report for pl manager of the reservation.
 */
public class ReservationDataParkingLotManager extends AbstractGUIComponent {

	private Worker wkr;
	private ArrayList<Reservation> reservationsArray;
/**
 * 
 * @param navigator navigate between panels
 * @param wkr
 */
	public ReservationDataParkingLotManager(final IGUINavigator navigator, final Worker wkr, final Worker workerToBack)
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
		
		JLabel lblComplaintReportPlease = new JLabel("Reservation Report, Please Choose date range:");
		lblComplaintReportPlease.setBounds(28, 27, 500, 14);
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
				
				MessageGetReservationData msg = new MessageGetReservationData();
				client.send(msg);
				MessageGetReservationDataReply replay = (MessageGetReservationDataReply)client.getMessage();
				reservationsArray = replay.getReservationsArray();
				
				JButton btnReset = new JButton("Reset");
				btnReset.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						navigator.goToReservationDataMenuParkingLot(wkr,workerToBack);
						
					}
				});
				
				btnReset.setBounds(367, 43, 89, 23);
				add(btnReset);
				
				JButton btnAll = new JButton("All");
				btnAll.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (reservationsArray != null)
							PrintTableALL(reservationsArray,dateChooser.getDate(),dateChooser_1.getDate());
					}
				});
				btnAll.setBounds(275, 71, 87, 23);
				add(btnAll);
				
				JButton btnCasual = new JButton("Casual");
				btnCasual.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (reservationsArray != null)
							PrintTableCasual(reservationsArray,dateChooser.getDate(),dateChooser_1.getDate());
					}
				});
				btnCasual.setBounds(367, 71, 87, 23);
				add(btnCasual);
				
				JButton btnInAdvance = new JButton("In Advance");
				btnInAdvance.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (reservationsArray != null)
							PrintTableInAdvance(reservationsArray,dateChooser.getDate(),dateChooser_1.getDate());
					}
				});
				btnInAdvance.setBounds(459, 71, 87, 23);
				add(btnInAdvance);
				
				validate();
				repaint();
			}

		});
		btnGenerate.setBounds(275, 43, 87, 23);
		add(btnGenerate);
	
		
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
 * @param reservations
 * @param from from date
 * @param to to date
 * 
 * array list is all the data of the reservation
 */
	private void PrintTableALL(ArrayList<Reservation> reservations, Date from, Date to) {
		
		int length = reservations.size();
		Object[][] data = new Object[length+1][12];
		int i=0;
		
		data[i][0] = "ReservationID";
		data[i][1] = "CarID";
		data[i][2] = "CustomerID";
		data[i][3] = "Parking LotID";
		data[i][4] = "est Cin Date";
		data[i][5] = "est Cin Hour";
		data[i][6] = "est Cot Date";
		data[i][7] = "est Cot Hour";
		data[i][8] = "inAdvance";
		data[i][9] = "estBill";
		data[i][10] = "used";	
		data[i][11] = "ResDate";
		
		i++;
		
		for(Reservation res: reservations)
		{
			if((res.getReservationDate().after(from) && res.getReservationDate().before(to)) || (res.getReservationDate().compareTo(from)) == 0 || (res.getReservationDate().compareTo(to)) == 0 )	
				if(res.getPl().equals(String.valueOf(wkr.getParkingLotID())))
				{
					data[i][0] = res.getRid();
					data[i][1] = res.getCarId();
					data[i][2] = res.getCid();
					data[i][3] = res.getPl();
					data[i][4] = res.getEstCinDate();
					data[i][5] = res.getEstCinHour();
					data[i][6] = res.getEstCoutDate();
					data[i][7] = res.getEstCoutHour();
					data[i][8] = res.isInAdvance();
					data[i][9] = res.getEstBill();
					data[i][10] = res.isUsed();
					data[i][11] = res.getReservationDate();
					i++;
				}
		}
		
		String[] columnNames = {"ReservationID",
                "CarID",
                "CustomerID",
                "ParkingLotID",
                "est Cin Date",
                "est Cin Hour",
                "est Cot Date",
                "est Cot Hour",
                "inAdvance",
                "estBill",
                "used",
                "ResDate"
                };
		
		JTable reservationsTable = new JTable(data , columnNames);
		reservationsTable.setBounds(new Rectangle(10, 99, 1300, i*23));
		reservationsTable.getColumnModel().getColumn(1).setPreferredWidth(35);
		reservationsTable.setRowHeight(23);
		

		reservationsTable.setVisible(true);
	
		
		this.add(reservationsTable,null);
		this.revalidate();
		this.repaint();
	}
	/**
	 * 
	 * @param reservations  /same as above
	 * @param from			/
	 * @param to			/
	 */
	private void PrintTableInAdvance(ArrayList<Reservation> reservations, Date from, Date to) {
		
		
		int length = reservations.size();
		Object[][] data = new Object[length+1][12];
		int i=0;
		
		data[i][0] = "ReservationID";
		data[i][1] = "CarID";
		data[i][2] = "CustomerID";
		data[i][3] = "Parking LotID";
		data[i][4] = "est Cin Date";
		data[i][5] = "est Cin Hour";
		data[i][6] = "est Cot Date";
		data[i][7] = "est Cot Hour";
		data[i][8] = "inAdvance";
		data[i][9] = "estBill";
		data[i][10] = "used";	
		data[i][11] = "ResDate";
		
		i++;
		
		for(Reservation res: reservations)
		{
			if((res.getReservationDate().after(from) && res.getReservationDate().before(to)) || (res.getReservationDate().compareTo(from)) == 0 || (res.getReservationDate().compareTo(to)) == 0 )	
				if(res.getPl().equals(String.valueOf(wkr.getParkingLotID())))
					if(res.isInAdvance())
					{
						data[i][0] = res.getRid();
						data[i][1] = res.getCarId();
						data[i][2] = res.getCid();
						data[i][3] = res.getPl();
						data[i][4] = res.getEstCinDate();
						data[i][5] = res.getEstCinHour();
						data[i][6] = res.getEstCoutDate();
						data[i][7] = res.getEstCoutHour();
						data[i][8] = res.isInAdvance();
						data[i][9] = res.getEstBill();
						data[i][10] = res.isUsed();
						data[i][11] = res.getReservationDate();
						i++;
					}
		}
		
		String[] columnNames = {"ReservationID",
                "CarID",
                "CustomerID",
                "ParkingLotID",
                "est Cin Date",
                "est Cin Hour",
                "est Cot Date",
                "est Cot Hour",
                "inAdvance",
                "estBill",
                "used",
                "ResDate"
                };
		
		JTable reservationsTable = new JTable(data , columnNames);
		reservationsTable.setBounds(new Rectangle(10, 99, 1300, i*23));
		reservationsTable.getColumnModel().getColumn(1).setPreferredWidth(35);
		reservationsTable.setRowHeight(23);
		

		reservationsTable.setVisible(true);
	
		
		this.add(reservationsTable,null);
		this.revalidate();
		this.repaint();
	}
	/**
	 * 
	 * @param reservations	/ same as above
	 * @param from			/
	 * @param to			/
	 */
	private void PrintTableCasual(ArrayList<Reservation> reservations, Date from, Date to) {
		
		
		int length = reservations.size();
		Object[][] data = new Object[length+1][12];
		int i=0;
		
		data[i][0] = "ReservationID";
		data[i][1] = "CarID";
		data[i][2] = "CustomerID";
		data[i][3] = "Parking LotID";
		data[i][4] = "est Cin Date";
		data[i][5] = "est Cin Hour";
		data[i][6] = "est Cot Date";
		data[i][7] = "est Cot Hour";
		data[i][8] = "inAdvance";
		data[i][9] = "estBill";
		data[i][10] = "used";	
		data[i][11] = "ResDate";
		
		i++;
		
		for(Reservation res: reservations)
		{
			if((res.getReservationDate().after(from) && res.getReservationDate().before(to)) || (res.getReservationDate().compareTo(from)) == 0 || (res.getReservationDate().compareTo(to)) == 0 )	
				if(res.getPl().equals(String.valueOf(wkr.getParkingLotID())))
					if(!res.isInAdvance())
					{
						data[i][0] = res.getRid();
						data[i][1] = res.getCarId();
						data[i][2] = res.getCid();
						data[i][3] = res.getPl();
						data[i][4] = res.getEstCinDate();
						data[i][5] = res.getEstCinHour();
						data[i][6] = res.getEstCoutDate();
						data[i][7] = res.getEstCoutHour();
						data[i][8] = res.isInAdvance();
						data[i][9] = res.getEstBill();
						data[i][10] = res.isUsed();
						data[i][11] = res.getReservationDate();
						i++;
					}
		}
		
		String[] columnNames = {"ReservationID",
                "CarID",
                "CustomerID",
                "ParkingLotID",
                "est Cin Date",
                "est Cin Hour",
                "est Cot Date",
                "est Cot Hour",
                "inAdvance",
                "estBill",
                "used",
                "ResDate"
                };
		
		JTable reservationsTable = new JTable(data , columnNames);
		reservationsTable.setBounds(new Rectangle(10, 99, 1300, i*23));
		reservationsTable.getColumnModel().getColumn(1).setPreferredWidth(35);
		reservationsTable.setRowHeight(23);
		

		reservationsTable.setVisible(true);
	
		
		this.add(reservationsTable,null);
		this.revalidate();
		this.repaint();
	}
}
