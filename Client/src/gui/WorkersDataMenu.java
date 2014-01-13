package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;

import DataObjects.Worker;
import Messages.MessageGetWorkersData;
import Messages.MessageGetWorkersDataReply;

/**
 * 
 * @author omri
 *This class is responsible for generate a worker report.
 */

public class WorkersDataMenu extends AbstractGUIComponent {
	
	private Worker wkr;
	/**
	 * 
	 * @param navigator navigate between panels
	 * @param worker
	 */
	public WorkersDataMenu(final IGUINavigator navigator,  Worker worker) {
		super();	
		setLayout(null);
		wkr = worker;
		
		MessageGetWorkersData msg = new MessageGetWorkersData();
		client.send(msg);
		MessageGetWorkersDataReply replay = (MessageGetWorkersDataReply)client.getMessage();
		if (replay.getWorkerArray() != null) {
			
			PrintTable(navigator,replay.getWorkerArray());
					
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
	/**
	 * 
	 * @param navigator navigate between panels
	 * @param workers
	 * array list contains all of the workers data
	 */
	private void PrintTable(final IGUINavigator navigator,ArrayList<Worker> workers) {
		
		
		int length = workers.size();
		Object[][] data = new Object[length+1][5];
		int i=0;
		
		data[i][0] = "ID";
		data[i][1] = "First Name";
		data[i][2] = "Last Name";
		data[i][3] = "E-Mail";
		data[i][4] = "Parking Lot ID";
		
		i++;
		
		for(Worker worker: workers)
		{
			data[i][0] = worker.getId();
			data[i][1] = worker.getfName();
			data[i][2] = worker.getlName();
			data[i][3] = worker.getEmail();
			data[i][4] = worker.getParkingLotID();
			

			i++;
		}
		
		String[] columnNames = {"ID",
                "First Name",
                "Last Name",
                "E-Mail",
                "Parking Lot ID"};
		
		JTable workersTable = new JTable(data , columnNames);
		workersTable.setBounds(new Rectangle(10, 10, 500, i*23));
		workersTable.setRowHeight(23);
		

		workersTable.setVisible(true);
	
		
		this.add(workersTable,null);
	}	

	
}
