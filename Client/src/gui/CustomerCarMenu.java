package gui;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import DataObjects.Customer;
import Messages.MessageGetCstDetails;
import Messages.MessageGetCstDetailsReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CustomerCarMenu extends AbstractGUIComponent {
	private String customerID;
	private List<String> list;
	public CustomerCarMenu(final IGUINavigator navigator, final String customerID, List<String> ls) {
		this.customerID = customerID;
		list = ls;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Available cars:");
		lblNewLabel.setBounds(83, 75, 93, 14);
		add(lblNewLabel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(212, 72, 107, 20);
		for (int i = 0;i < list.size();i++)
		{
			comboBox.addItem(list.get(i));
		}
		add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.send(new MessageGetCstDetails(customerID, (String)comboBox.getSelectedItem()));
				MessageGetCstDetailsReply gcdr = (MessageGetCstDetailsReply)client.getMessage();
				navigator.goToCustomerMenu(gcdr.getCustomer());
			}
		});
		btnSubmit.setBounds(167, 190, 107, 41);
		add(btnSubmit);
	}
}
