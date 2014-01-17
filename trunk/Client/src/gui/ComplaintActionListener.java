package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import DataObjects.Complaint;
import DataObjects.DataObjectMessageToUser;
import DataObjects.Worker;
import Messages.MessageAnswerComplaint;
import Messages.MessageAnswerComplaintReply;
import Messages.MessageSendMessage;
/**
 * listens to customer service send.
 * @author Gal
 *
 */
public class ComplaintActionListener extends AbstractGUIComponent implements ActionListener {

	private IGUINavigator navigator;
	private Worker wkr;
	private Complaint compliant;
	private JTextArea refund;
	private JTextArea answer;
	private boolean isRefundFloat;
	
	public ComplaintActionListener(IGUINavigator navigator,Worker wkr, Complaint comp,JTextArea refund,JTextArea answer) {
		this.navigator = navigator;
		this.wkr = wkr;
		this.compliant = comp;
		this.refund = refund;
		this.answer = answer;
		this.isRefundFloat=true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			Float.parseFloat(refund.getText());
		}
		catch(NumberFormatException e3) {
			this.isRefundFloat=false;
		}
		
		if(answer.getText().length()<1000)
			if(this.isRefundFloat)
			{
				MessageAnswerComplaint msg = new MessageAnswerComplaint(compliant, refund.getText(), answer.getText(), wkr);
				try {
					client.sendToServer(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MessageAnswerComplaintReply reply = (MessageAnswerComplaintReply) client.getMessage();
				if(reply.isDone())
				{
					JOptionPane.showMessageDialog(null, "Done");
					DataObjectMessageToUser dataoMessageToUser = new DataObjectMessageToUser(wkr.getId(), compliant.getCustomerID(), "Your Refund is:"+refund.getText()+"\nAnswer From CS:"+answer.getText());
					client.send(new MessageSendMessage(dataoMessageToUser));
				}
				else
					JOptionPane.showMessageDialog(null, "Error");
			}
			else
				JOptionPane.showMessageDialog(null, "Error - invalid Refund");
		else
			JOptionPane.showMessageDialog(null, "Answer to long");
			
		navigator.goToComplaintMenu(wkr);
		
	}

}
