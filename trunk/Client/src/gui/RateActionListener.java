package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DataObjects.DataObjectMessageToUser;
import DataObjects.RateRequest;
import DataObjects.Worker;
import Messages.MessageAprroveRateRequest;
import Messages.MessageAprroveRateRequestReply;
import Messages.MessageSendMessage;

public class RateActionListener extends AbstractGUIComponent implements ActionListener {

	private RateRequest req;
	boolean isAprroved;
	private DataObjectMessageToUser msg;
	private IGUINavigator navigator;
	private Worker wkr;

	public RateActionListener(IGUINavigator navigator,Worker wkr, RateRequest req, boolean isAprroved,DataObjectMessageToUser msg)
	{
		this.req = req;
		this.isAprroved = isAprroved;
		this.msg = msg;
		this.navigator = navigator;
		this.wkr = wkr;
	}
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