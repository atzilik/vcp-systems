package gui;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DataObjects.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;

public class CheckOutMenu extends AbstractGUIComponent {
	private Customer cst;
	private Map<String,Integer> parkingLots;
	public CheckOutMenu(final IGUINavigator navigator, Customer cst, Map<String,Integer> mp) {
		this.cst = cst;
		this.parkingLots = mp;
		setLayout(null);
		
		JButton btnSubmit = new JButton("OK");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(51, 165, 115, 29);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(216, 165, 115, 29);
		add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Check in with car " + cst.getCarId() + " ?");
		lblNewLabel.setBounds(117, 95, 249, 29);
		add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(133, 53, 138, 20);
		Set<String> keys = parkingLots.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();)
		{
			comboBox.addItem(i.next());
		}
		add(comboBox);
	}

}
