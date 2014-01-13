package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;

import DataObjects.Reservation;
import DataObjects.Worker;
import Messages.MessageGetReservationData;
import Messages.MessageGetReservationDataReply;
import Messages.MessageGetWorkersData;
import Messages.MessageGetWorkersDataReply;

public class ReservationDataMenu extends AbstractGUIComponent {
	
	private Worker wkr;
	
	public ReservationDataMenu(final IGUINavigator navigator,  Worker worker) {
		super();	
		setLayout(null);
		wkr = worker;
		
		MessageGetReservationData msg = new MessageGetReservationData();
		client.send(msg);
		MessageGetReservationDataReply replay = (MessageGetReservationDataReply)client.getMessage();
		if (replay.getReservationsArray() != null) {
			
			PrintTable(navigator,replay.getReservationsArray());
					
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					navigator.goToWorkerMenu(wkr);
				}
			});
			btnCancel.setBounds(178, 313, 89, 23);
			add(btnCancel);
		}
			
		else
			navigator.goToWorkerMenu(wkr);
	}
	
	private void PrintTable(final IGUINavigator navigator,ArrayList<Reservation> reservations) {
		
		
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
		reservationsTable.setBounds(new Rectangle(10, 10, 1300, i*23));
		reservationsTable.getColumnModel().getColumn(1).setPreferredWidth(35);
		reservationsTable.setRowHeight(23);
		

		reservationsTable.setVisible(true);
	
		
		this.add(reservationsTable,null);
	}	

	
}
