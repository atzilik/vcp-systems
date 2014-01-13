package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

import DataObjects.DisabledParkingSpace;
import DataObjects.Worker;
import Messages.MessageGetDisabledParkingSpace;
import Messages.MessageGetDisabledParkingSpaceReply;

public class DisabledParkingSpaceReport extends AbstractGUIComponent {
	
	private Worker wkr;
	
	public DisabledParkingSpaceReport(final IGUINavigator navigator, final Worker wkr, final Worker workerToBack)
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
		
		JLabel lblComplaintReportPlease = new JLabel("Disabled Parking Space Report, Please Choose date range:");
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
				MessageGetDisabledParkingSpace msg = new MessageGetDisabledParkingSpace();
				client.send(msg);
				MessageGetDisabledParkingSpaceReply replay = (MessageGetDisabledParkingSpaceReply)client.getMessage();
				if (replay.getDisabledArray() != null)
					PrintTable(replay.getDisabledArray(),dateChooser.getDate(),dateChooser_1.getDate());
				
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
	
private void PrintTable(ArrayList<DisabledParkingSpace> disabled, Date from, Date to) {
		
		int length = disabled.size();
		Object[][] data = new Object[length+1][8];
		int i=0;
		
		data[i][0] = "ParkingLotID";
		data[i][1] = "floor";
		data[i][2] = "row";
		data[i][3] = "depth";
		data[i][4] = "disabledDate";
		data[i][5] = "disabled_time";
		data[i][6] = "enabledDate";
		data[i][7] = "enabled_time";
		
		i++;
		
		for(DisabledParkingSpace dis: disabled)
		{
			if((dis.getDisabledDate().after(from) && dis.getDisabledDate().before(to)) || dis.getDisabledDate().compareTo(from) == 0 || dis.getDisabledDate().compareTo(to) == 0 )	
				if(dis.getParkingLotID() == (wkr.getParkingLotID()))
				{
					data[i][0] = dis.getParkingLotID();
					data[i][1] = dis.getFloor();
					data[i][2] = dis.getRow();
					data[i][3] = dis.getDepth();
					data[i][4] = dis.getDisabledDate();
					data[i][5] = dis.getDisabled_time();
					data[i][6] = dis.getEnabledDate();
					data[i][7] = dis.getEnabled_time();
					
					i++;
				}
		}
		
		JLabel lblDisableTotal = new JLabel("Total Disabled Parking Spac: "+ String.valueOf(i-1));
		lblDisableTotal.setBounds(456, 43, 250, 23);
		add(lblDisableTotal);
		
		String[] columnNames = {"ParkingLotID",
                "floor",
                "row",
                "depth",
                "disabledDate",
                "disabled_time",
                "enabledDate",
                "enabled_time"};
		
		JTable disabledTable = new JTable(data , columnNames);
		disabledTable.setBounds(new Rectangle(10, 100, 620, i*23));
	//	complaintTable.getColumnModel().getColumn(1).setPreferredWidth(35);
	//	complaintTable.setRowHeight(23);
		

		disabledTable.setVisible(true);
		
		this.add(disabledTable,null);
		
		this.revalidate();
		this.repaint();
	}
}



