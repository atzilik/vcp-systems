package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DataObjects.DataObjectMessageToUser;
import DataObjects.RateRequest;
import DataObjects.Worker;
import Messages.MessageAprroveRateRequest;
import Messages.MessageAprroveRateRequestReply;
import Messages.MessageSendMessage;
/**
 * Controls the rates requests between the CEO and parking lot managers
 * @author Alon
 *
 */
public class RateActionListener extends AbstractGUIComponent implements ActionListener {
/**
 * the rate request
 */
	private RateRequest req;
	/**
	 * requests approved or not
	 */
	boolean isAprroved;
	/**
	 * the msg between ceo and parking lot manager
	 */
	private DataObjectMessageToUser msg;
	/**
	 * navigating between the panels
	 */
	private IGUINavigator navigator;
	private Worker wkr;

	/**
	 * loads the data
	 * @param navigating between the panels
	 * @param wkr worker details
	 * @param req the requests
	 * @param isAprroved approved or not
	 * @param msg the message between the entities
	 */
	public RateActionListener(IGUINavigator navigator,Worker wkr, RateRequest req, boolean isAprroved,DataObjectMessageToUser msg)
	{
		this.req = req;
		this.isAprroved = isAprroved;
		this.msg = msg;
		this.navigator = navigator;
		this.wkr = wkr;
	}
	/**
	 * if request was approved by the ceo then send a message to the parking lot manager and the opposite
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MessageAprroveRateRequest msg1 = new MessageAprroveRateRequest(req,isAprroved);
		client.send(msg1);
		MessageAprroveRateRequestReply replay = (MessageAprroveRateRequestReply)client.getMessage();
		replay.doAction();
		if(isAprroved)
		{
			this.msg.setMsg("Your request approved");
			MessageSendMessage msgToManager = new MessageSendMessage(msg);
			client.send(msgToManager);
		}
		else
		{
			this.msg.setMsg("Your request rejected");
			MessageSendMessage msgToManager = new MessageSendMessage(msg);
			client.send(msgToManager);
		}	
		navigator.goToChangeRates(wkr);
	}

}
