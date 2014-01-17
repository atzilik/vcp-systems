package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import DataObjects.Worker;
import Messages.MessageGetDisabledParkingSpace;
import Messages.MessageGetDisabledParkingSpaceReply;
import Messages.MessageGetMembersNum;
import Messages.MessageGetMembersNumReply;
/**
 * performance report window
 * @author Gal
 *
 */
public class PerformenceReportMenu extends AbstractGUIComponent {
	
	Worker wkr;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param worker 
	 */
	public PerformenceReportMenu(final IGUINavigator navigator, final Worker wkr) {
		super();	
		setLayout(null);
		this.wkr = wkr;
		
		MessageGetMembersNum msg = new MessageGetMembersNum();
		client.send(msg);
		MessageGetMembersNumReply reply = (MessageGetMembersNumReply)client.getMessage();
		
		JLabel lblTotalMembrs = new JLabel("Total Members: "+ String.valueOf(reply.getNumOfMembers()));
		lblTotalMembrs.setBounds(456, 43, 250, 23);
		add(lblTotalMembrs);
		
		JLabel lblMembersWithManyCars = new JLabel("Total Members With Many Cars: "+ String.valueOf(reply.getNumOfMembersWithManyCars()));
		lblMembersWithManyCars.setBounds(456, 71, 250, 23);
		add(lblMembersWithManyCars);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(158, 249, 89, 23);
		btnGoBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				navigator.goToWorkerMenu(wkr);
			}
		});
		add(btnGoBack);
	}

}
