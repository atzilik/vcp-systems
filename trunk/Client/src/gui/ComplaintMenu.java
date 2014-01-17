package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DataObjects.Complaint;
import DataObjects.DataObjectMessageToUser;
import DataObjects.Worker;
import Messages.MessageGetComplaint;
import Messages.MessageGetComplaintReply;
/**
 * show customer's complaint window
 * @author Gal
 *
 */
public class ComplaintMenu extends AbstractGUIComponent {
	
	/**
	 *  worker instance
	 */
	Worker wkr;
	/**
	 * complaints
	 */
	private ArrayList<Complaint> complaintsArray;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param worker customer service instance
	 */
	public ComplaintMenu(final IGUINavigator navigator,  Worker worker) {
		super();	
		wkr = worker;
		setLayout(null);
		MessageGetComplaint try1 = new MessageGetComplaint();
		client.send(try1);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToWorkerMenu(wkr);
			}
		});
		btnCancel.setBounds(400, 400, 89, 23);
		this.add(btnCancel);
		
		MessageGetComplaintReply reply = (MessageGetComplaintReply)client.getMessage();
		complaintsArray = reply.getComplaintsArray();
		if (complaintsArray != null) {
			PrintTable(navigator,reply.getComplaintsArray());
		}
	
	}

	private void PrintTable(final IGUINavigator navigator,ArrayList<Complaint> complaints) {
			
		int length = complaints.size();
		Object[][] data = new Object[length+1][5];
		int i=0;
		
		ArrayList<JLabel> refundLabels = new ArrayList<>();
		refundLabels.add(0, new JLabel("empty"));
		
		ArrayList<JTextArea> refundTexts = new ArrayList<>();
		refundTexts.add(0, new JTextArea());
		
		ArrayList<JLabel> answerLabels = new ArrayList<>();
		answerLabels.add(0, new JLabel("empty"));
		
		ArrayList<JTextArea> answerTexts = new ArrayList<>();
		answerTexts.add(0, new JTextArea());
		
		ArrayList<JButton> sendButtons = new ArrayList<>();
		sendButtons.add(0, new JButton());
		
		data[i][0] = "ComplaintID";
		data[i][1] = "CustomerID";
		data[i][2] = "Details";
		data[i][3] = "Date";
		data[i][4] = "ParkingLotID";
	
		
		i++;
		
		for(Complaint complaint: complaints)
		{
			data[i][0] = complaint.getComplaintID();
			data[i][1] = complaint.getCustomerID();
			data[i][2] = complaint.getDetails();
			data[i][3] = complaint.getDate();
			data[i][4] = complaint.getParkingLotID();
			
			answerLabels.add(i,new JLabel("Answer:"));
			answerLabels.get(i).setBounds(450, 25+(i-1)*100, 80, 14);
			this.add(answerLabels.get(i));
			
			answerTexts.add(i,new JTextArea());
			answerTexts.get(i).setBounds(450, 25+14+(i-1)*100, 80, 86);
			answerTexts.get(i).setWrapStyleWord(true);
			answerTexts.get(i).setLineWrap(true);
			this.add(answerTexts.get(i));
			
			refundLabels.add(i,new JLabel("Refund:"));
			refundLabels.get(i).setBounds(540, 25+(i-1)*100, 80, 14);
			this.add(refundLabels.get(i));
			
			refundTexts.add(i,new JTextArea());
			refundTexts.get(i).setBounds(540, 25+14+(i-1)*100, 80, 15);
			refundTexts.get(i).setWrapStyleWord(true);
			refundTexts.get(i).setLineWrap(true);
			this.add(refundTexts.get(i));
			
			sendButtons.add(i,new JButton("Send"));
			sendButtons.get(i).addActionListener(new ComplaintActionListener(navigator, wkr, complaint, refundTexts.get(i), answerTexts.get(i)));
			sendButtons.get(i).setBounds(540, 25+14+15 + (i-1)*100, 80, 71);
			this.add(sendButtons.get(i));
			
			i++;
		}
		
		String[] columnNames = {"ComplaintID",
                "CustomerID",
                "Details",
                "Date",
                "ParkingLotID"};
		
		JTable complaintsTable = new JTable(data , columnNames);
		complaintsTable.setBounds(new Rectangle(0, 0, 440, i*100));
		complaintsTable.setRowHeight(100);
		complaintsTable.getColumnModel().getColumn(2).setPreferredWidth(250);
		complaintsTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		complaintsTable.setRowHeight(0, 25);
		

		complaintsTable.setVisible(true);
	
		
		this.add(complaintsTable,null);
		
	}

	public ArrayList<Complaint> getComplaintsArray() {
		return complaintsArray;
	}

	public void setComplaintsArray(ArrayList<Complaint> complaintsArray) {
		this.complaintsArray = complaintsArray;
	}	

}
