package gui;


import javax.swing.JButton;
import javax.swing.JTable;


import DataObjects.*;
import Messages.MessageAprroveRateRequest;
import Messages.MessageAprroveRateRequestReply;
import Messages.MessageGetRateRequest;
import Messages.MessageGetRateRequestReply;
import Messages.MessageWorkerLogin;
import Messages.MessageWorkerLoginReply;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RateRequests extends AbstractGUIComponent {
	
	Worker wkr;
	
	public RateRequests(final IGUINavigator navigator,  Worker worker) {
		super();	
		
		wkr = worker;
		MessageGetRateRequest try1 = new MessageGetRateRequest();
		client.send(try1);
		MessageGetRateRequestReply replay = (MessageGetRateRequestReply)client.getMessage();
		if (replay.getRateArray() != null) {
			
			PrintTable(navigator,replay.getRateArray());
			
			JButton btnNewButton = new JButton("Accept Request");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			setLayout(null);
			btnNewButton.setBounds(114, 250, 109, 23);
			add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Reject Request");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			setLayout(null);
			btnNewButton_1.setBounds(228, 250, 107, 23);
			add(btnNewButton_1);
			
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
			navigator.goToWorkerMenu(replay.getWkr());
	}
	
	private void PrintTable(final IGUINavigator navigator,ArrayList<RateRequest> requests) {
		
		ArrayList<JButton> Buttons = new ArrayList<>();
		Buttons.add(0, new JButton("empty"));

		
		int length = requests.size();
		Object[][] data = new Object[length+1][5];
		int i=0;
		
		data[i][0] = "Parking Lot ID";
		data[i][1] = "Occasional Rate";
		data[i][2] = "Reserved Rate";
		data[i][3] = "Standard Rate";
		data[i][4] = "Full Rate";
		
		i++;
		
		for(RateRequest req: requests)
		{
			data[i][0] = req.getParkingLotId();
			data[i][1] = req.getOccasional();
			data[i][2] = req.getReserved();
			data[i][3] = req.getStandard();
			data[i][4] = req.getFull();
			
			MessageToUser msg = new MessageToUser(wkr.getId(), req.getMangerId(), null);

			Buttons.add(i,new JButton("V"));
			Buttons.get(i).addActionListener(new rateActionListener(navigator,wkr,req,true,msg));
			setLayout(null);
			Buttons.get(i).setBounds(515, 100 + (i*23), 50, 23);
			add(Buttons.get(i));
			
			
			Buttons.add(i,new JButton("X"));
			Buttons.get(i).addActionListener(new rateActionListener(navigator,wkr,req,false,msg));
			setLayout(null);
			Buttons.get(i).setBounds(570, 100 + (i*23), 50, 23);
			add(Buttons.get(i));

			i++;
		}
		
		String[] columnNames = {"Parking Lot ID",
                "Occasional Rate",
                "Reserved Rate",
                "Standard Rate",
                "Full Rate"};
		
		JTable ratesTable = new JTable(data , columnNames);
		ratesTable.setBounds(new Rectangle(10, 100, 500, i*23));
		ratesTable.setRowHeight(23);
		

		ratesTable.setVisible(true);
	
		
		this.add(ratesTable,null);
	}	

}